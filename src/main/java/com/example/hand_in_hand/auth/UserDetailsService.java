package com.example.hand_in_hand.auth;


import com.example.hand_in_hand.dao.contracts.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserDAO userRepository;

    @Autowired
    public UserDetailsService(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.hand_in_hand.entities.models.User appUser=userRepository.findByUsername(username);
        // Veritabanından kullanıcıyı username ile bul
        if(appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        // Spring Security UserDetails nesnesini döndür
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.emptyList(
                ) // Kullanıcının rolleri
        );
    }
}
