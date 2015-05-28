package weixin.weixin.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import weixin.weixin.entity.WeixinUploadMsg;
import weixin.weixin.repository.WeixinUploadMsgDao;
import weixin.weixin.util.WeixinUtil;

/**
 * 上传图片文本消息到微信服务器
 * 
 * @author jlusoft
 *
 */
@Service
@Transactional(readOnly = true)
public class WeixinUploadMsgService extends
		GenericService<WeixinUploadMsg, WeixinUploadMsgDao> {
	@Autowired
	private WeixinUploadMsgDao dao;

	@Transactional(readOnly = false)
	public boolean pushMsgToWeixin(String path, Long[] ids) {
		List<WeixinUploadMsg> entitys = dao.findByIds(Lists.newArrayList(ids));
		if (entitys == null)
			return false;
		Map<Long, WeixinUploadMsg> map = Maps.newHashMap();
		for (WeixinUploadMsg tmp : entitys)
			map.put(tmp.getId(), tmp);

		Map<String, Object> msg = Maps.newHashMap();
		msg.put("articles", getMsgJsonMap(ids, map, path));
		dao.save(entitys);
		
		try {
			String mediaid = WeixinUtil.uploadPictureMsg(msg);
			logger.info("mediaId:{}",mediaid);
			
			msg.clear();
			Map<String, Object> filter = Maps.newHashMap();
			filter.put("is_to_all", false);
			filter.put("group_id", 105);
			msg.put("filter", filter);
			Map<String, Object> mpnews = Maps.newHashMap();
			mpnews.put("media_id", mediaid);
			msg.put("mpnews", mpnews);
			msg.put("msgtype", "mpnews");
			String msgid = WeixinUtil.pushPublicMsg(msg);
			if(StringUtils.isNotBlank(msgid))
				return true;
		} catch (JSONException e) {
			logger.error("发送公共消息异常，",e);
		}
		return false;
	}
	/**
	 * 组建微信图文消息
	 * @param ids
	 * @param map
	 * @param path
	 * @return
	 */
	private List<Map<String, Object>> getMsgJsonMap(Long[] ids,
			Map<Long, WeixinUploadMsg> map, String path) {
		List<Map<String, Object>> articles = Lists.newArrayList();
		Map<String, Object> tmp;
		WeixinUploadMsg entity;
		for (Long id : ids) {
			entity = map.get(id);
			if (entity == null)
				continue;
			getMediaId(path, entity);
			tmp = Maps.newHashMap();
			tmp.put("title", entity.getTitle());
			tmp.put("thumb_media_id", entity.getThumb_media_id());
			tmp.put("author", entity.getAuthor());
			if (ids.length > 1)// 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
				tmp.put("digest", "");
			else
				tmp.put("digest", entity.getDigest());
			if (id == ids[0])// 是否显示封面，0为false，即不显示，1为true，即显示
				tmp.put("show_cover_pic", 1);
			else
				tmp.put("show_cover_pic", 0);
			tmp.put("content", entity.getContent());// 必须少于2万字符，小于1M，且此处会去除JS
			tmp.put("content_source_url", entity.getContent_source_url());
			articles.add(tmp);
		}
		return articles;
	}
	/**
	 * 检查消息中的图片是否上传到微信
	 * @param root
	 * @param em
	 */
	private void getMediaId(String root, WeixinUploadMsg em) {
		if (StringUtils.isBlank(em.getThumb_media_id())) {
			em.setThumb_media_id(WeixinUtil.uploadImg(root + em.getPicPath()));
		}
	}
}
