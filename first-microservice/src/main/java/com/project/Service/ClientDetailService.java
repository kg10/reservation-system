package com.project.Service;

import com.project.Model.Client;
import com.project.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClientDetailService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            Client client = clientRepository.findByLogin(login);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(client.getRole()));

            if (client == null)
                throw new UsernameNotFoundException("No user in database with login " + login);
            else if (!client.getActive())
                throw new UsernameNotFoundException("No active user with login " + login);
            else {
                return new org.springframework.security.core.userdetails.User(client.getLogin(), client.getPassword(),true,true,true,true, grantedAuthorities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


