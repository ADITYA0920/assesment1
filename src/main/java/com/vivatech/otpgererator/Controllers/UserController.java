package com.vivatech.otpgererator.Controllers;

import com.vivatech.otpgererator.Models.User;
import com.vivatech.otpgererator.Repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo ;

    @PostMapping("/addUser")
    public ResponseEntity<Boolean> addUser(@RequestBody User userReqDto){
        User user = new User() ;
        user.setName(userReqDto.getName());
        user.setEmail(userReqDto.getEmail());
        user.setAddress(userReqDto.getAddress());
        user.setPhoneNumber(userReqDto.getPhoneNumber());

        User savedUser = userRepo.save(user) ;
        return new ResponseEntity<>(true,HttpStatus.CREATED) ;
    }
}
