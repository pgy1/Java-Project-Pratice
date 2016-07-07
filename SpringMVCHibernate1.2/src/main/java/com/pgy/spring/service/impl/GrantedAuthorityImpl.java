package com.pgy.spring.service.impl;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by jackson on 16/3/20.
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(){}

    public GrantedAuthorityImpl(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
