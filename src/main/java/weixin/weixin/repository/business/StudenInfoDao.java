package weixin.weixin.repository.business;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import weixin.weixin.entity.business.StudenInfo;
import weixin.weixin.repository.GenericDao;

public interface StudenInfoDao extends GenericDao<StudenInfo>{
	@Query("from StudenInfo where inspectNum=?1 and icdNum=?2")
	List<StudenInfo> findInspectNumAndIcdNum(String key,String icdNum);

}
