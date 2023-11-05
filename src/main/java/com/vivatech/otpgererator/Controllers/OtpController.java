package com.vivatech.otpgererator.Controllers;

import com.vivatech.otpgererator.Components.JwtTokenProvider;
import com.vivatech.otpgererator.Models.User;
import com.vivatech.otpgererator.Repositories.UserRepo;
import com.vivatech.otpgererator.RequestDtos.LoginRequest;
import com.vivatech.otpgererator.RequestDtos.Otp;
import com.vivatech.otpgererator.RequestDtos.UserReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OtpController {


    @Autowired
    private UserRepo userRepo ;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private JavaMailSender emailSender ;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest request) {
        String userEmail = request.getEmail() ;
        String userPassword = request.getPassword() ;
        User user ;
        try {
            user = userRepo.findByEmail(userEmail);
        }
        catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<User> addUser(@RequestBody UserReqDto userReqDto) {
        //creating user
        User user = new User() ;
        user.setName(userReqDto.getName());
        user.setEmail(userReqDto.getEmail());


        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        user.setLatestOtp((otp));

        User savedUser = userRepo.save(user) ;

        String token = tokenProvider.generateToken(user.getEmail());

        String text = "validate your otp, your otp is"+otp;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("**********"); // put yout server email
        message.setTo(user.getEmail());
        message.setSubject("Validate OTP");
        message.setText(text);
        emailSender.send(message);

        return ResponseEntity.ok(user);
    }


    @PostMapping("/validate-otp")
    public ResponseEntity<Boolean> validateOtp(@RequestBody Otp request) {
        // Implement logic to validate the OTP

        User user = userRepo.findById(request.getUserId()).get() ;
        String userLatestOtp = user.getLatestOtp() ;

        String latestOTP = request.getOtp() ;

        if (latestOTP.equals(userLatestOtp)) { // Simplified OTP validation
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
