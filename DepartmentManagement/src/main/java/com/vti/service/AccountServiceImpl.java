package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = accountRepository.findByUsername(username);
		
//		Employee employee = repository.fiundByUsername(username);
		// Error 403
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(
				account.getUsername(),
				account.getPassword(),
				AuthorityUtils.createAuthorityList(account.getRole()));
	}
	
	@Override
	public Account getAccountByUserName(String username) {
		return accountRepository.findByUsername(username);
	}
	
}
