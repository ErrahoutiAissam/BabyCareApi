package com.errahouti.BabyCareApi.controller.auth;

import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.User;
import com.errahouti.BabyCareApi.repository.UserRepo;
import com.errahouti.BabyCareApi.security.PasswordEncoder;
import com.errahouti.BabyCareApi.service.JwtService;
import com.errahouti.BabyCareApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;


    public AuthenticationResponse register(RegisterRequest request) throws NotFoundException {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encoder().encode(request.getPassword()))
                .build();



        if(userRepo.findByEmail(request.getEmail()).isPresent()){
            throw new NotFoundException("this email already exists");
        }
        userRepo.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        if (passwordEncoder.encoder().matches(request.getPassword(), userDetails.getPassword())) {
            String token = jwtService.generateToken(userDetails);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } else {
            throw new AuthenticationException("Invalid credentials");
        }
    }
}
