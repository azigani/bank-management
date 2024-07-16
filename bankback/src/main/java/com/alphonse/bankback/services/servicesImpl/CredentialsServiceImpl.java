package com.alphonse.bankback.services.servicesImpl;

import java.util.Optional;

import com.alphonse.bankback.dao.CredentialsRepository;
import com.alphonse.bankback.entities.Credentials;
import com.alphonse.bankback.services.ICredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsServiceImpl implements ICredentialsService {
	@Autowired
	private CredentialsRepository credentialsDao;
	@Override
	public Credentials save(Credentials credentials) {
		return credentialsDao.save(credentials);
	}

	@Override
	public Optional<Credentials> findById(Long id) {
		return credentialsDao.findById(id);
	}

	@Override
	public Optional<Credentials> findByLogin(String login) {
		return credentialsDao.findByLogin(login);
	}

}
