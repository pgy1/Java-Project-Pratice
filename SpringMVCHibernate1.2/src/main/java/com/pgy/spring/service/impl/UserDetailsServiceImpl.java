package com.pgy.spring.service.impl;

/**
 * Created by jackson on 16/4/11.
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: xingshukui.
 * date:2016/4/5.
 */
public class UserDetailsServiceImpl extends JdbcDaoImpl implements UserDetailsService{

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        /*Md5PasswordEncoder encoder = new Md5PasswordEncoder();*/
        Map<String,Object> map = this.getJdbcTemplate().queryForMap("SELECT * FROM users WHERE username = ?",new Object[]{s},new int[]{Types.VARCHAR});
        if (!map.isEmpty()){
            userDetails = new User(s,map.get("password").toString(),true,true,true,true, getAuthorities(s));
        }
        return userDetails;
    }

    /**
     * »ñµÃ·ÃÎÊ½ÇÉ«È¨ÏÞ
     * @return
     */
    public List<GrantedAuthority> getAuthorities(String name) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        Map<String,Object> map = this.getJdbcTemplate().queryForMap("SELECT authority FROM authorities  where username = '"+name+"'");
        GrantedAuthority authority = new GrantedAuthorityImpl(this.getRolePrefix()+map.get("authority").toString().toUpperCase());
        authList.add(authority);

        return authList;
    }
}


