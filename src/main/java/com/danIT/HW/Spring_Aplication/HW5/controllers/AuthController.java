package com.danIT.HW.Spring_Aplication.HW5.controllers;

import com.danIT.HW.Spring_Aplication.HW5.domain.JwtRequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.JwtResponse;
import com.danIT.HW.Spring_Aplication.HW5.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

}
