package io.github.bxo.myoauth.dao;

import io.github.bxo.myoauth.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface OauthClientDetailsDao extends JpaRepository<OauthClientDetails,String>{
}
