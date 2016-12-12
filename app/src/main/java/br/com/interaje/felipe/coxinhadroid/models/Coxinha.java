package br.com.interaje.felipe.coxinhadroid.models;

import com.orm.SugarRecord;

/**
 * Created by felipe on 12/12/16.
 */

public class Coxinha extends SugarRecord {

    private String flavor;
    private String description;
    private Double price;

    public Coxinha() {
    }

    public Coxinha(String flavor, String desc, Double price) {
        this.flavor = flavor;
        this.description = desc;
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
