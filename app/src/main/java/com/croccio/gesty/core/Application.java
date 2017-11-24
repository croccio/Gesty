package com.croccio.gesty.core;

import fr.xebia.android.freezer.Freezer;

/**
 * Created by antonioscardigno on 24/11/17.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Freezer.onCreate(this);
    }
}
