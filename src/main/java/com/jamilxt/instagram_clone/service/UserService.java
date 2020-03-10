package com.jamilxt.instagram_clone.service;

import com.jamilxt.instagram_clone.dtos.UserDto;
import com.jamilxt.instagram_clone.exceptions.ResourceAlreadyExistsException;
import com.jamilxt.instagram_clone.exceptions.ResourceNotFoundException;
import com.jamilxt.instagram_clone.model.Authority;
import com.jamilxt.instagram_clone.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.jamilxt.instagram_clone.model.User userEntity = userRepository.findByUsername(s)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with this username"));

//        Set<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList()); // jamilxt
//        var authorities = userEntity.getAuthorities();
        // mainul35
        // after adding multiple roles - these are not working properly
        // I think this is important to grant roles
//        authorities.add((GrantedAuthority) () -> userFromDb.getRole().name()); // mainul35

        return userEntity; // This User class is from  Spring Security. Since we did not implement UserDetails interface from SpringSecurity, therefore we are creating an instance of Spring Security provided User class.
    }

    public List<com.jamilxt.instagram_clone.model.User> showAll() {
        return userRepository.findAllByActiveIsTrue();
    }

    public void addUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) {

            var userEntity = new com.jamilxt.instagram_clone.model.User();
            BeanUtils.copyProperties(userDto, userEntity);
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

            Set<Authority> authorities = new HashSet<Authority>();
            for (var authorityName : userDto.getAuthorityNames()) {
                var authority = authorityService.findByRoleName(authorityName);
                authorities.add(authority);
            }
            userEntity.setAuthorities(authorities);

            userRepository.save(userEntity);

        } else {
            throw new ResourceAlreadyExistsException("Username is unavailable");
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId); // will be converted to soft delete
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public List<UserDto> findUser(String query) {
        var users = this.userRepository.getUsersByQueryString(query);
        var userDtos = new ArrayList<UserDto>();

        for (var user : users) {
            var userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }

        return userDtos;
    }

}