package io.github.bxo.myoauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MeController {

    @GetMapping("/me")
    public Principal user(Principal user) {
        return user;
    }

    @ResponseBody
    @GetMapping("/me/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
