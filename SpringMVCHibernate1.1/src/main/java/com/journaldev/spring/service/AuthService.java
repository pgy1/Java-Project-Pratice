package com.journaldev.spring.service;

import com.journaldev.spring.dao.JdbcService;
import com.journaldev.spring.service.impl.GrantedAuthorityImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by jackson on 16/3/19.
 */
public class AuthService implements UserDetailsService{

    public Logger logger = Logger.getLogger("AuthService");
    @Resource(name = "jdbcService")
    JdbcService jdbcService;
    /**
     * 获得访问角色权限
     *
     * @param access
     * @return
     */
    public Collection<GrantedAuthority> getAuthorities(int access) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        // 所有的用户默认拥有ROLE_USER权限
        authList.add(new GrantedAuthorityImpl("ROLE_USER"));

        // 如果参数access为1.则拥有ROLE_ADMIN权限
        if (access == 1) {
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }

        return authList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = null;
        System.out.println(username);
        try{
            Map dbuser = jdbcService.queryForSingle("" +
                    "select username,pass,enabled from users where username = ?",new Object[]{username},new int[]{Types.VARCHAR});
            user = new User(dbuser.get("username").toString(),dbuser.get("pass").toString(),getAuthorities(Integer.parseInt(dbuser.get("enabled").toString())));
            logger.info("User login");
        }catch (Exception e){
            throw new RuntimeException("User does not exist!");
        }

        return user;
    }
}
