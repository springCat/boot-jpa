package org.springcat.entity;

import javax.persistence.*;

/**
 * Created by springcat on 16/9/28.
 */
@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String loginName;
    private String password;
    private String salt;
    private Boolean archived;
    private Integer version;
    private Long createTime;
    private Long lastLoginTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "archived")
    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Basic
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_login_time")
    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User users = (User) o;

        if (id != null ? !id.equals(users.id) : users.id != null) return false;
        if (loginName != null ? !loginName.equals(users.loginName) : users.loginName != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (salt != null ? !salt.equals(users.salt) : users.salt != null) return false;
        if (archived != null ? !archived.equals(users.archived) : users.archived != null) return false;
        if (version != null ? !version.equals(users.version) : users.version != null) return false;
        if (createTime != null ? !createTime.equals(users.createTime) : users.createTime != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(users.lastLoginTime) : users.lastLoginTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (archived != null ? archived.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        return result;
    }
}
