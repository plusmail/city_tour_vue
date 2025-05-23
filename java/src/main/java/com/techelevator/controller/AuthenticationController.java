package com.techelevator.controller;

import jakarta.validation.Valid;

import com.techelevator.exception.DaoException;
import com.techelevator.model.account.LoginDto;
import com.techelevator.model.account.LoginResponseDto;
import com.techelevator.model.account.RegisterUserDto;
import com.techelevator.model.account.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        System.out.println("loginDto: " + loginDto);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        System.out.println("authenticationToken: " + authenticationToken);
        String jwt= null;
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            System.out.println("authentication: " + authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = tokenProvider.createToken(authentication, false);
            System.out.println("jwt-> "+ jwt);
            // 나머지 처리
        } catch (Exception e) {
            e.printStackTrace(); // 어디서 실패하는지 정확히 확인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
        }


        User user;
        try {
            user = userDao.getUserByUsername(loginDto.getUsername());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            System.out.println(newUser.toString());
            User user = userDao.createUser(newUser);
            System.out.println(user.getUsername());
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration failed.");
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }

}

