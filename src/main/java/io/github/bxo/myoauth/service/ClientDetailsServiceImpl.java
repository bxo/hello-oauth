package io.github.bxo.myoauth.service;

import io.github.bxo.myoauth.dao.OauthClientDetailsDao;
import io.github.bxo.myoauth.entity.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;

    @Override
    public void save(OauthClientDetails clientDetails) {
        oauthClientDetailsDao.save(clientDetails);
    }
}
