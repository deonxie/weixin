package weixin.weixin.service.account;

import cn.gd.thinkjoy.modules.security.utils.Digests;
import cn.gd.thinkjoy.modules.utils.Clock;
import cn.gd.thinkjoy.modules.utils.Encodes;
import weixin.weixin.entity.account.Role;
import weixin.weixin.entity.account.User;
import weixin.weixin.repository.account.UserDao;
import weixin.weixin.service.GenericService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenling on 14-3-25.
 */
@Component
@Transactional(readOnly = true)
public class UserService extends GenericService<User, UserDao> {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    private Clock clock = Clock.DEFAULT;
    @Autowired
    private UserDao userDao;


    public User findUserByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    @Transactional(readOnly = false)
    public String registerUser(User user, List<Long> checkedRoleList) {
        if (user.getId() == 0) {
            user.setRegisterDate(clock.getCurrentDate());
            User oldUser = findUserByLoginName(user.getLoginName());
            if (null != oldUser) {
                return "用户名已经存在!";
            }
        }
        user.getRoleList().clear();
        for (Long roleId : checkedRoleList) {
            Role role = new Role();
            role.setId(roleId);
            user.getRoleList().add(role);
        }
        if (StringUtils.isNotBlank(user.getPlainPassword())) {
            entryptPassword(user);
        }

        userDao.save(user);

        return "添加用户成功！";
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
