package org.jason.beans;

import org.jason.common.Gender;
import lombok.Data;

@Data
public class Student {
    private String Name;
    private Integer Age;
    private Gender gender;
}
