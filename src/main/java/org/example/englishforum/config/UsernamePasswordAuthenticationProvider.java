package org.example.englishforum.config;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

//@RequiredArgsConstructor
//@Component
public class UsernamePasswordAuthenticationProvider
//        implements AuthenticationProvider
{

//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        List<User> users = userRepository.findByUsername(name).get();
//        if (CollectionUtils.isEmpty(users))
//            throw new BadCredentialsException("No user registered with username" + users);
//        else {
//            if (passwordEncoder.matches(password, users.get(0).getPassword())) {
//                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
////                grantedAuthorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
//                return new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
//            } else {
//                throw new BadCredentialsException("Invalid password for username =" + name);
//            }
//
//        }
//
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
}
