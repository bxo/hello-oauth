package io.github.bxo.myoauth.service;

import io.github.bxo.myoauth.entity.OauthClientDetails;

public interface ClientDetailsService {
    void save(OauthClientDetails clientDetails);
}
