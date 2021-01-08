package com.noteoline.v2.service;

import com.noteoline.v2.entity.SecurityTable;
import com.noteoline.v2.repositroy.SecurityTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SecurityTableRepository securityTableRepository;

    @Override
    public UserDetails loadUserByUsername(String uesrname) throws UsernameNotFoundException {

        List<SecurityTable> list= securityTableRepository.findAllByNameUserList(uesrname);
        SecurityTable user= list.get(0);

        if (user==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }


        List<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
        if(user.getRole().contains("admin"))
        {
            auths= AuthorityUtils.commaSeparatedStringToAuthorityList("admins");
        }
        else
            {
                auths= AuthorityUtils.commaSeparatedStringToAuthorityList("common");
        }
        return  new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }
}
