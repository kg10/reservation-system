package com.project.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Model.Client;
import com.project.Repository.ClientRepository;

@Service("clientDetailService")
public class ClientDetailService implements UserDetailsService {
	private final ClientRepository clientRepository;

	@Autowired
	public ClientDetailService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Client client = clientRepository.findByLogin(login);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(client.getRole()));

		if (client == null)
			throw new UsernameNotFoundException("No user in database with login " + login);
		else if (!client.getActive())
			throw new UsernameNotFoundException("No active user with login " + login);
		else {
			//System.out.println(grantedAuthorities);
			return new User(client.getLogin(), client.getPassword(), grantedAuthorities);
		}
	}

}
