package com.msuaitp.orgnized.webapp.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.msuaitp.orgnized.webapp.dao.PersonDao;
import com.msuaitp.orgnized.webapp.domain.Person;

@Service("authService")
public class UserDetailServiceImpl implements UserDetailsService {

	PersonDao personDao = new PersonDao();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personDao.findOneByUsername(username);
		if (person == null) {
			throw new UsernameNotFoundException("username " + username + " not found");
		}

		System.out.println("---------------------> FOUND ------------->" + person.getEmail());

		return person;
	}
}
