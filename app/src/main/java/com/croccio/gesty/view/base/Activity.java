package com.croccio.gesty.view.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by antonioscardigno on 23/11/17.
 */

public abstract class Activity extends AppCompatActivity {

    public void loadFragment(Fragment fragment, View conteiner) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.support.v7.appcompat.R.anim.abc_grow_fade_in_from_bottom, android.support.v7.appcompat.R.anim.abc_shrink_fade_out_from_bottom)
                .replace(conteiner.getId(), fragment)
                .commitAllowingStateLoss();
    }

    public abstract View getDrawerView();

    public abstract View getContentView();

}
