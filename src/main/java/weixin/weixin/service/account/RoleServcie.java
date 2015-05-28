package weixin.weixin.service.account;

import weixin.weixin.entity.account.Role;
import weixin.weixin.repository.account.RoleDao;
import weixin.weixin.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenling on 14-3-26.
 */
@Service
@Transactional(readOnly = true)
public class RoleServcie extends GenericService<Role, RoleDao> {
    @Autowired
    private RoleDao roleDao;

    public Role findRoleByName(String name) {
        return roleDao.findByName(name);
    }

    @Transactional(readOnly = false)
    public String saveRole(Role role) {
        if (role.getId() == 0) {
            Role oldRole = findRoleByName(role.getName());
            if (null != oldRole) {
                return "角色名已经存在！";
            }
        }

        roleDao.save(role);
        return "添加角色成功！";
    }

}
