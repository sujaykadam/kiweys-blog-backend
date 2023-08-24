package com.kiweysblog.backend.security;

import com.kiweysblog.backend.exceptions.ResourceNotFoundException;
import com.kiweysblog.backend.models.User;
import com.kiweysblog.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("User", "username:"+username, 0));
        return user;
    }
}
