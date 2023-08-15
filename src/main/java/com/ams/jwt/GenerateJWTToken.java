/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ams.jwt;

import com.ams.controller.UserBean;
import com.ams.model.Staff;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author avni
 */

public class GenerateJWTToken {

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String generateToken(Staff staff) {

        String jwtToken = Jwts.builder()
                .setSubject(staff.getUsername())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return jwtToken;
    }
}
