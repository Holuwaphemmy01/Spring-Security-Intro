package com.example.SpringSecu.service;

import com.example.SpringSecu.model.UserPrincipal;
import com.example.SpringSecu.model.Users;
import com.example.SpringSecu.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }


        return new UserPrincipal(user);
    }
}
