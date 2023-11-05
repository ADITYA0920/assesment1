package com.vivatech.otpgererator.RequestDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {

    private String name;

    private String email;

    private  String password ;


}
