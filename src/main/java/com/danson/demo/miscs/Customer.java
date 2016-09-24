package com.danson.demo.miscs;

import com.danson.api.remote.annotation.ApiParam;
import com.danson.api.remote.definition.AbstractEntity;

/**
 * Created by dansonliu on 16/8/8.
 */
public class Customer extends AbstractEntity {

    @ApiParam(name = "name", desc = "名称")
    private String name;

    @ApiParam(name = "gender", desc = "性别")
    private Gender gender;

    @ApiParam(name = "age", desc = "年龄")
    private int    age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
