package weixin.weixin.entity.account;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Resource Base Access Control中的资源定义.
 * 
 * @author fly
 */
public enum Permission {
	// 系统设置
	USER_VIEW("user:view", "查看用户", "系统管理"), 
	USER_EDIT("user:edit", "操作用户","系统管理"), 
	ROLE_VIEW("role:view", "查看角色", "系统管理"), 
	ROLE_EDIT("role:edit", "操作角色", "系统管理"), 
	DRUID_MONITOR("druid:monitor","数据库监控", "系统管理"),
	WEIXINCON_VIEW("wxconfig:view","查看微信配置","微信配置管理"),
	WEIXINCON_EDIT("wxconfig:edit","修改微信配置","微信配置管理"),
	WEIXINMENU_VIEW("wxmenu:view","查看微信菜单","微信配置管理"),
	WEIXINMENU_EDIT("wxmenu:edit","修改微信菜单","微信配置管理"),
	WEIXINMSG_VIEW("wxmsg:view","查看微信消息","微信配置管理"),
	WEIXINMSG_EDIT("wxmsg:edit","发送微信消息","微信配置管理"),
	STUDEND_VIEW("studen:view","查看考生信息","考生信息管理"),
	STUDEND_EDIT("studen:edit","操作考生信息","考生信息管理"),
	;
	
	public String value;
	public String displayName;
	public String type;
	private static Map<String, Permission> valueMap = Maps.newHashMap();
	private static Map<String, List<Permission>> listMap = Maps.newHashMap();

	static {
		for (Permission permission : Permission.values()) {
			valueMap.put(permission.value, permission);
			List<Permission> permissionList = listMap.get(permission.type);
			if (permissionList == null) {
				permissionList = Lists.newArrayList();
			}
			permissionList.add(permission);
			listMap.put(permission.type, permissionList);
		}
	}

	Permission(String value, String displayName, String type) {
		this.value = value;
		this.displayName = displayName;
		this.type = type;
	}

	public static Permission parse(String value) {
		return valueMap.get(value);
	}

	public static List<Permission> parseList(String type) {
		return listMap.get(type);
	}

	/**
	 * 按照分类拿去所有的Permission
	 * 
	 * @return
	 */
	public static Map<String, List<Permission>> getListMap() {
		return listMap;
	}

	/**
	 * 按照value拿去所有的Permission
	 * 
	 * @return
	 */
	public static Map<String, Permission> getValueMap() {
		return valueMap;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
