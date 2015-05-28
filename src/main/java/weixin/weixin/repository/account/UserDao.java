/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package weixin.weixin.repository.account;

import weixin.weixin.entity.account.User;
import weixin.weixin.repository.GenericDao;

public interface UserDao extends GenericDao<User> {
    User findByLoginName(String loginName);
}
