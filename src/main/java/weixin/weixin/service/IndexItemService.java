package weixin.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weixin.entity.IndexItem;
import weixin.weixin.repository.IndexItemDao;

@Service
@Transactional(readOnly=true)
public class IndexItemService extends GenericService<IndexItem, IndexItemDao> {
	@Autowired
	IndexItemDao dao;

	public List<IndexItem> findByType(int typeItemimg) {
		return dao.findByTypeOrderIndex(typeItemimg);
	}
}
