package com.croccio.gesty.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.croccio.gesty.R;
import com.croccio.gesty.view.base.Activity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.drawerConteinerLayout)
    ViewGroup drawerConteinerLayout;

    @BindView(R.id.contentConteinerLayout)
    ViewGroup contentConteinerLayout;

    @Nullable
    @BindView(R.id.fakeActionBar)
    ViewGroup fakeActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        replaceDrawerFragment(DrawerFragment.newInstance());

        replaceContentFragment(ProductsListFragment.newInstance());

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
