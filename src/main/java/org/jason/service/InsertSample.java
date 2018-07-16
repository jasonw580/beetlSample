package org.jason.service;

import org.beetl.sql.core.SQLManager;
import org.jason.beans.User;

public class InsertSample {
    private SQLManager sqlManager;
    public InsertSample(SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public void insert(){
        //使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
        User user = new User();
        user.setAge(19);
        user.setName("xiandafu2");

        sqlManager.insert(user);
    }
}
