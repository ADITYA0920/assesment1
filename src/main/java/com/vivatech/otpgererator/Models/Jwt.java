package com.vivatech.otpgererator.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jwt {

    private String token;

    private Date expirationDate;
}
