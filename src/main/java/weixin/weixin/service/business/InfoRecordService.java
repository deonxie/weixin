package weixin.weixin.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.weixin.entity.business.InfoRecord;
import weixin.weixin.repository.business.InfoRecordDao;
import weixin.weixin.service.GenericService;

@Service
@Transactional(readOnly=true)
public class InfoRecordService extends GenericService<InfoRecord, InfoRecordDao> {

	@Autowired
	InfoRecordDao dao;
}
