package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName sys_user
 */
@Data
@Builder
@TableName("sys_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private Integer userId;
    /**
     * 登陆名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 是否启动账户0禁用 1启用
     */
    private Integer enabled;
    /**
     * 账户是否没有过期0已过期 1 正常
     */
    private Integer accountNoExpired;
    /**
     * 密码是否没有过期0已过期 1 正常
     */
    private Integer credentialsNoExpired;
    /**
     * 账户是否没有锁定0已锁定 1 正常
     */
    private Integer accountNoLocked;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
                && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
                && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
                && (this.getAccountNoExpired() == null ? other.getAccountNoExpired() == null : this.getAccountNoExpired().equals(other.getAccountNoExpired()))
                && (this.getCredentialsNoExpired() == null ? other.getCredentialsNoExpired() == null : this.getCredentialsNoExpired().equals(other.getCredentialsNoExpired()))
                && (this.getAccountNoLocked() == null ? other.getAccountNoLocked() == null : this.getAccountNoLocked().equals(other.getAccountNoLocked()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getAccountNoExpired() == null) ? 0 : getAccountNoExpired().hashCode());
        result = prime * result + ((getCredentialsNoExpired() == null) ? 0 : getCredentialsNoExpired().hashCode());
        result = prime * result + ((getAccountNoLocked() == null) ? 0 : getAccountNoLocked().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", userId=" + userId +
                ", username=" + username +
                ", password=" + password +
                ", sex=" + sex +
                ", address=" + address +
                ", enabled=" + enabled +
                ", accountNoExpired=" + accountNoExpired +
                ", credentialsNoExpired=" + credentialsNoExpired +
                ", accountNoLocked=" + accountNoLocked +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}