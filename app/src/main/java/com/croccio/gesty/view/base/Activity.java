package com.croccio.gesty.view.base;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by antonioscardigno on 23/11/17.
 */

public abstract class Activity extends AppCompatActivity {

    private void addFragment(android.support.v4.app.Fragment fragment, boolean replace, View rootView) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        if (replace) {
            fragmentTransaction.replace(rootView.getId(), fragment);
        } else {
            fragmentTransaction.add(rootView.getId(), fragment);
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void addFragment(Fragment fragment, View conteiner) {
        addFragment(fragment, false, conteiner);
    }

    private void replaceFragment(Fragment fragment, View conteiner) {
        addFragment(fragment, true, conteiner);
    }

    public void addContentFragment(Fragment fragment) {
        addFragment(fragment, getContentView());
    }

    public void addDrawerFragment(Fragment fragment) {
        addFragment(fragment, getDrawerView());
    }

    public void replaceContentFragment(Fragment fragment) {
        replaceFragment(fragment, getContentView());
    }

    public void replaceDrawerFragment(Fragment fragment) {
        replaceFragment(fragment, getDrawerView());
    }

    public abstract View getDrawerView();

    public abstract View getContentView();

}
