package weixin.weixin.repository.account;

import weixin.weixin.entity.account.Role;
import weixin.weixin.repository.GenericDao;

/**
 * Created by chenling on 14-3-24.
 */
public interface RoleDao extends GenericDao<Role>{
    Role findByName(String name);
}
