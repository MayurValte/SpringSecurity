package com.security.services;

import com.security.model.CustomUserDetail;
import com.security.model.User;
import com.security.repo.UserDetailsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserDetailsDao userDetailsDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("In loadUserByUsername usernmane is : "+username);
        User user = this.userDetailsDao.findByUsername(username);
        if(user==null){
            logger.info("No user Found with username : "+username);
            throw new UsernameNotFoundException("NO USER");
        }
        return new CustomUserDetail(user);
    }
}
