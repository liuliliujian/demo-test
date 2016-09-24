package com.danson.demo.miscs;

/**
 * Created by dansonliu on 16/8/10.
 */
public enum Gender {
    Male(1, "男"), Female(2, "女"), Unknown(0, "未知");

    private int    code;

    private String label;

    Gender(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

}
