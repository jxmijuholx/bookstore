package hh.soft03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.soft03.bookstore.domain.User;
import hh.soft03.bookstore.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService { 
    private final UserRepository userRepository; 
    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currUser = userRepository.findByUsername(username); 
       UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPassword(),
    		   AuthorityUtils.createAuthorityList(currUser.getRole()));
       return user;
    }
}
