package com.users.webapi.services.security;

import com.users.usersdata.model.User;
import com.users.usersdata.repositories.IRepositoriesCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Implementacja UserDetailsService do pobierania danych użytkownika
 */
@Service
@RequiredArgsConstructor
public class RandomUserDetailsService implements UserDetailsService {


    private final IRepositoriesCatalog db;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = db.getUsers().findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie znaleziony: " + username));
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());

        return new RandomUserDetails(user.getId(), user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

