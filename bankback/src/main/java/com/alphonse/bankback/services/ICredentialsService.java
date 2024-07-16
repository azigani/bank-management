package com.alphonse.bankback.services;

import com.alphonse.bankback.entities.Credentials;

import java.util.Optional;


public interface ICredentialsService {
	Credentials save(Credentials credentials);
	Optional<Credentials> findById(Long id);
	Optional<Credentials> findByLogin(String code);
}
