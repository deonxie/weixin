package weixin.weixin.entity.account;

import cn.gd.thinkjoy.modules.utils.Collections3;
import weixin.weixin.entity.IdEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "weixin_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {
    private String loginName;
    private String name;
    private String plainPassword;
    private String password;
    private String salt;
    private Date registerDate;
    private List<Role> roleList = Lists.newArrayList(); // 有序的关联对象集合



    @Column(unique = true, nullable = false)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(nullable =  false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 不持久化到数据库，也不显示在Restful接口的属性.
    @Transient
    @JsonIgnore
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @ManyToMany
    // 中间表定义,表名采用默认命名规则
    @JoinTable(name = "think_user_role", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    public List<Role> getRoleList() {
        return roleList;
    }


    /**
     * 用户拥有的角色id字符串, 多个角色id用','分隔.
     */
    //非持久化属性.
    @Transient
    @JsonIgnore
    public List<Long> getRoleIds() {
        return Collections3.extractToList(roleList, "id");
    }

    @Transient
    @JsonIgnore
    public String getRoleNames() {
        return Collections3.extractToString(roleList, "name", ", ");
    }

    // 设定JSON序列化时的日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}