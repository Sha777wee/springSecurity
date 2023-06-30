package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName sys_menu
 */
@Data
@Builder
@TableName("sys_menu")
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 父级编号
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 0代表菜单1权限2 url
     */
    private Integer type;
    /**
     * 0代表未删除，1 代表已删除
     */
    private Integer deleteFlag;

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
        Menu other = (Menu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", pid=" + pid +
                ", name=" + name +
                ", code=" + code +
                ", type=" + type +
                ", deleteFlag=" + deleteFlag +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}