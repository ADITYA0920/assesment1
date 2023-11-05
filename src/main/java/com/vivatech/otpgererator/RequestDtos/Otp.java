package com.vivatech.otpgererator.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Otp {

    private Integer userId;

    private String otp;

}
