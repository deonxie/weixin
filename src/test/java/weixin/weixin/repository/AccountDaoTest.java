/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package weixin.weixin.repository;

import cn.gd.thinkjoy.modules.test.spring.SpringTransactionalTestCase;
import weixin.weixin.entity.account.Permission;
import weixin.weixin.entity.account.Role;
import weixin.weixin.entity.account.User;
import weixin.weixin.service.account.RoleServcie;
import weixin.weixin.service.account.UserService;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class AccountDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private UserService userService;
    @Autowired
    private RoleServcie roleService;

    @Test
    @Rollback(false)
    public void addRole() {
        Role role = new Role();
        role.setName("c管理员");
        role.setPermissions("role:view,role:edit,user:view,user:edit");
       
        roleService.save(role);

    }

    @Test
    @Rollback(false)
    public void registerUser(){

        List<Role> roleList = roleService.getAll();

        List<Long> roleIds = Lists.newArrayList();

        for(Role role : roleList){
            roleIds.add(role.getId());
        }

        User user = new User();
        user.setId(0L);
        user.setLoginName("admin");
        user.setName("admin");
        user.setPlainPassword("123");
        System.out.println(userService.registerUser(user, roleIds));
    }
    public static void main(String[] args) {
    	 for(String tmp :Permission.getValueMap().keySet())
    		 System.out.print(tmp+",");
	}
}
