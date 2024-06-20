package com.danIT.HW.Spring_Aplication.HW5.services;

import com.danIT.HW.Spring_Aplication.HW5.domain.JwtRequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.JwtResponse;
import com.danIT.HW.Spring_Aplication.HW5.domain.User;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final Map<String, List<String>> accessStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    PasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public List<String> getTokensByUser(String userName) {
        List<String> accessTokens = accessStorage.get(userName);
        return accessTokens;
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User not found"));
        if (passwordEncoder.matches(authRequest.getPassword(), user.getEncryptedPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUserName(), refreshToken);
            List<String> accessTokens = accessStorage.computeIfAbsent(user.getUserName(), k -> new ArrayList<>());
            accessTokens.add(accessToken);
            accessStorage.put(user.getUserName(), accessTokens);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }
}

