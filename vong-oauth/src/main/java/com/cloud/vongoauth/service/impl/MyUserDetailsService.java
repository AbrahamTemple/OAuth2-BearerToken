package com.cloud.vongoauth.service.impl;

import com.cloud.vongoauth.entity.vPermission;
import com.cloud.vongoauth.entity.vUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private VUserServiceImpl vUserService;

    @Resource
    private VPermissionServiceImpl vPermissionService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        vUser loginUser = vUserService.getByUserName(name);
        if(loginUser==null) {
            throw new UsernameNotFoundException(name + "用户名不存在");
        }
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        List<vPermission> vPermissions = vPermissionService.selectByUserId(loginUser.getId());
        vPermissions.forEach( vp ->{
            System.out.println(vp.getEnableName());
            roles.add(new SimpleGrantedAuthority(vp.getEnableName()));
        });
        return new User(loginUser.getUsername(),loginUser.getPassword(),roles);
    }
    /*if( !name.equals("admin") )
            throw new UsernameNotFoundException(name + "用户名不存在");
    // 资源权限，之后可以通过这里赋予的权限控制接口访问。
    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority( "ROLE_ADMIN"));
    //roles.add(new SimpleGrantedAuthority( "ROLE_Normal"));
    String pass = new BCryptPasswordEncoder().encode("admin");
        return new User(name,pass,roles);*/
}
