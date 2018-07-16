package org.jason.beans;

import lombok.Data;
import org.beetl.sql.core.annotatoin.AssignID;

@Data
public class Party  {
    @AssignID
    private Integer id1 ;
    @AssignID
    private Integer id2 ;
    private String name ;

    @Override
    public String toString() {
        return "Party{" +
                "id1=" + id1 +
                ", id2=" + id2 +
                ", name='" + name + '\'' +
                '}';
    }
}