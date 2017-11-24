package com.croccio.gesty.model;

import fr.xebia.android.freezer.annotations.Model;

/**
 * Created by antonioscardigno on 24/11/17.
 */

@Model
public class ProductCategory {

    int priority;
    String name;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
