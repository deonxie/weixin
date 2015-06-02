package weixin.weixin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import weixin.weixin.entity.IndexItem;

public interface IndexItemDao extends GenericDao<IndexItem> {

	@Query("from IndexItem where type=?1 order by index asc")
	List<IndexItem> findByTypeOrderIndex(int typeItemimg);

}
