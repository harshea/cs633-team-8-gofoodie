package com.cs673.gofoodie.services;

import com.cs673.gofoodie.models.Role;
import com.cs673.gofoodie.repositories.RoleRepository;
import com.cs673.gofoodie.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cs673.gofoodie.models.User;

/**
 * This is custome user details class implemented to create custom user by providing addional
 * securities.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

 // @Autowired
  //private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * This method is used to retrieve user by email.
   */
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  /**
   * This method is used to create new user in database.
   */
  public void saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setEnabled(true);
    //Role userRole = roleRepository.findByRole("ADMIN");
   // user.setRoles(new HashSet<>(Arrays.asList(userRole)));
    userRepository.save(user);
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user != null) {
      List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
      return buildUserForAuthentication(user, authorities);
    } else {
      throw new UsernameNotFoundException("username not found");
    }
  }

  private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
    Set<GrantedAuthority> roles = new HashSet<>();
    userRoles.forEach((role) -> {
      roles.add(new SimpleGrantedAuthority(role.getRole()));
    });

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
    return grantedAuthorities;
  }

  private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), authorities);
  }
}
