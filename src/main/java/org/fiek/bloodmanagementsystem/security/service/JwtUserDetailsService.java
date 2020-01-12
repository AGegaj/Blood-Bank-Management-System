package org.fiek.bloodmanagementsystem.security.service;

import org.fiek.bloodmanagementsystem.entity.Role;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.repository.UserRepository;
import org.fiek.bloodmanagementsystem.security.model.JwtUserDetails;
import org.fiek.bloodmanagementsystem.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(username).get();

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        List<Role> roles = new ArrayList<>();
        Boolean isEnable;
        if(user.getStatus().equals(Status.ACTIVE)){
            isEnable=Boolean.TRUE;
        }else{
            isEnable=Boolean.FALSE;
        }
        roles.add(user.getRole());

        return new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                roles.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList()),
                isEnable
        );
    }

}