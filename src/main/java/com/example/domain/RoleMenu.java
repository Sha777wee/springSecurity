package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName sys_role_menu
 */
@Data
@Builder
@TableName("sys_role_menu")
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 菜单表的编号
     */
    private Integer mid;
    /**
     * 角色表的编号
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
        RoleMenu other = (RoleMenu) that;
        return (this.getMid() == null ? other.getMid() == null : this.getMid().equals(other.getMid()))
                && (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMid() == null) ? 0 : getMid().hashCode());
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", mid=" + mid +
                ", rid=" + rid +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}