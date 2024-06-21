package com.aoop.uber.controller.passenger;

import com.aoop.uber.model.passenger.JwtTokenBlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class LogoutController {



    @Autowired
    private JwtTokenBlackList tokenBlackList;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);

        tokenBlackList.addToBlacklist(jwtToken);

        return ResponseEntity.ok("Logged out successfully");
    }
}
