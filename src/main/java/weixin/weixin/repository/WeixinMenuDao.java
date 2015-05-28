package weixin.weixin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import weixin.weixin.entity.WeixinMenu;

/**
 * 微信自定义按钮
 * @author jlusoft
 *
 */
public interface WeixinMenuDao extends GenericDao<WeixinMenu>{
	@Query("from WeixinMenu where parent is null order by index")
	List<WeixinMenu> findFirstLevelMenu();
	
}
