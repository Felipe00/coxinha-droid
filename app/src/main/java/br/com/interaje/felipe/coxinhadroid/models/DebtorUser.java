package br.com.interaje.felipe.coxinhadroid.models;

import com.orm.SugarRecord;

/**
 * Created by felipe on 30/11/16.
 */

public class DebtorUser extends SugarRecord {

    private String name;
    private String phone;
    private String email;

    public DebtorUser(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
