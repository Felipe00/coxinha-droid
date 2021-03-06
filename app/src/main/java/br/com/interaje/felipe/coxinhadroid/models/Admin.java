package br.com.interaje.felipe.coxinhadroid.models;

import com.orm.SugarRecord;

/**
 * Created by felipe on 30/11/16.
 */

public class Admin extends SugarRecord {

    private String name;
    private String email;
    private String password;
    private String phone;

    public Admin() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
