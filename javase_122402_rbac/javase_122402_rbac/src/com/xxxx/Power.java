package com.xxxx;

import java.io.Serializable;

/**
 * 权限信息
 */
public class Power implements Serializable {
    //权限ID
    private String pid;
    //权限名称：例如 增加 删除  修改  查询 ...
    private String powername;

}
