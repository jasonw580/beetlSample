package org.jason.dao;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.jason.beans.User;

import java.util.List;

@SqlResource("userdao")
public interface UserDao extends BaseMapper<User> {
    List<User> select(String name);
    int getCount();
}
