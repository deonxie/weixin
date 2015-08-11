package weixin.weixin.repository.business;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import weixin.weixin.entity.business.StudenInfo;
import weixin.weixin.repository.GenericDao;

public interface StudenInfoDao extends GenericDao<StudenInfo>{
	@Query("from StudenInfo where inspectNum=?1 and icdNum=?2")
	List<StudenInfo> findInspectNumAndIcdNum(String key,String icdNum);

	@Modifying
	@Query("delete from StudenInfo where id in (?1)")
	void deleteIds(List<Long> ids);
	
	@Modifying
	@Query("delete from StudenInfo where isupdate=?1 and status != 0")
	void deleteAllPass(int isupdate);

	@Query("from StudenInfo where status=?1")
	Iterable<StudenInfo> findAllByStatus(int status);

}
