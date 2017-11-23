package com.croccio.gesty.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.croccio.gesty.R;
import com.croccio.gesty.view.base.Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.drawerConteinerLayout)
    RelativeLayout drawerConteinerLayout;

    @BindView(R.id.contentConteinerLayout)
    RelativeLayout contentConteinerLayout;

    @Nullable
    @BindView(R.id.fakeActionBar)
    RelativeLayout fakeActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadFragment(DrawerFragment.newInstance(), getDrawerView());

    }

    @Override
    public View getDrawerView() {
        return drawerConteinerLayout;
    }

    @Override
    public View getContentView() {
        return contentConteinerLayout;
    }
}
