package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName sys_role_user
 */
@Data
@Builder
@TableName("sys_role_user")
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 角色编号
     */
    private Integer rid;

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
        RoleUser other = (RoleUser) that;
        return (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", uid=" + uid +
                ", rid=" + rid +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}