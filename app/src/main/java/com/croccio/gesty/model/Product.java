package com.croccio.gesty.model;

import java.util.List;

import fr.xebia.android.freezer.annotations.Model;

/**
 * Created by antonioscardigno on 24/11/17.
 */

@Model
public class Product {

    long priority;
    float price;
    List<Condiment> condiments;
    ProductCategory category;

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Condiment> getCondiments() {
        return condiments;
    }

    public void setCondiments(List<Condiment> condiments) {
        this.condiments = condiments;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

}
