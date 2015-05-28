/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package weixin.weixin.data;

import java.util.Map;

import weixin.weixin.entity.account.User;
import weixin.weixin.util.PoiReadExcelUtil;
import cn.gd.thinkjoy.modules.test.data.RandomData;

public class UserData {

	public static User randomNewUser() {
		User user = new User();
		user.setLoginName(RandomData.randomName("user"));
		user.setName(RandomData.randomName("User"));
		user.setPlainPassword(RandomData.randomName("password"));

		return user;
	}
	public static void main(String[] args) {
//		Map map = PoiReadExcelUtil.readExcelMap("/Users/jlusoft/Documents/workspace/weixin/src"
//				+ "/main/webapp/tmp/1a6b18d7-2736-4646-8296-6ecd0514f425.xlsx");
//		if(map!= null)
//			System.out.println(map.size());
		String reguar = "ddbdd";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(reguar+".?");
		System.out.println(p.matcher("s"+reguar+"d/=0514f425.xlsx").find());
	}
}
