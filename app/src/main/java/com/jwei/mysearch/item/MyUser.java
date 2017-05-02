package com.jwei.mysearch.item;

import cn.bmob.v3.BmobUser;

/**
 * Created by sh on 2017/5/2.
 */

public class MyUser extends BmobUser {
    private Boolean sex;
    private String nick;
    private Integer age;
//    private String Country;

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

//    public String getCountry() {
//        return Country;
//    }

//    public void setCountry(String country) {
//        Country = country;
//    }


}
