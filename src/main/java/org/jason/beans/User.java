package org.jason.beans;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private Integer age;
    //用户角色
    private Integer roleId;
    private String name;
    //用户名称
    private String userName;
    private Date createDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
