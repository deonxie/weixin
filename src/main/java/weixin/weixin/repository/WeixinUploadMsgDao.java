package weixin.weixin.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import weixin.weixin.entity.WeixinUploadMsg;

/**
 * 上传图片文本消息到微信服务器
 * @author jlusoft
 *
 */
public interface WeixinUploadMsgDao extends GenericDao<WeixinUploadMsg>{

	@Query("from WeixinUploadMsg where id in(?1)")
	List<WeixinUploadMsg> findByIds(List<Long> list);
	 
}
