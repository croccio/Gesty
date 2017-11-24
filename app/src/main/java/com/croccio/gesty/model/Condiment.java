package com.croccio.gesty.model;

import fr.xebia.android.freezer.annotations.Model;

/**
 * Created by antonioscardigno on 24/11/17.
 */

@Model
public class Condiment {

    long priority;
    CondimentCategory category;
    String name;
    float priceToAdd;
    float priceToRemove;

    public CondimentCategory getCategory() {
        return category;
    }

    public void setCategory(CondimentCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriceToAdd() {
        return priceToAdd;
    }

    public void setPriceToAdd(float priceToAdd) {
        this.priceToAdd = priceToAdd;
    }

    public float getPriceToRemove() {
        return priceToRemove;
    }

    public void setPriceToRemove(float priceToRemove) {
        this.priceToRemove = priceToRemove;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }
}
