package com.example.administrator.mycheckbox;

/**
 * Created by Administrator on 2016/11/14.
 */
public class CheckBean {
    private boolean isChecked;
    private String test;

    public CheckBean(boolean isChecked, String test) {
        this.isChecked = isChecked;
        this.test = test;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
