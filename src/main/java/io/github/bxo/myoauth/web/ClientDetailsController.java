package io.github.bxo.myoauth.web;

import io.github.bxo.myoauth.entity.OauthClientDetails;
import io.github.bxo.myoauth.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client")
public class ClientDetailsController {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @GetMapping("/add")
    public String addClientUi() {
        return "client";
    }

    @PostMapping("/save")
    public String addClient(OauthClientDetails client) {
        clientDetailsService.save(client);
        return "client";
    }

}
