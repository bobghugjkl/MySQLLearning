package com.xxxx;

import java.io.Serializable;

/**
 * 角色信息
 * <p>
 * 表关系：一个角色可以有多种权限  ，一个权限可以被多个角色所拥有
 * <p>
 * 例如：
 * 普通用户 查
 * 管理员 查增
 * 超级管理员  增删改查】
 */
public class Role implements Serializable {
    //角色ID
    private String rid;
    //角色名称
    private String rolename;

}
