package com.alphonse.bankback.dao;

import com.alphonse.bankback.entities.Credentials;
import com.alphonse.bankback.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByLogin(String login);

}
