package com.croccio.gesty.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.croccio.gesty.R;
import com.croccio.gesty.view.base.Fragment;
import com.croccio.gesty.view.handleProducts.condiment.HandleCondimentsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DrawerFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    @BindView(R.id.drawerToolbar)
    Toolbar drawerToolbar;

    @BindView(R.id.categoryRecyclerView)
    RecyclerView categoryRecyclerView;

    @BindView(R.id.menuView)
    ImageView menuView;

    Unbinder unbinder;

    private PopupMenu popup;

    public DrawerFragment() {
    }

    public static DrawerFragment newInstance() {
        DrawerFragment fragment = new DrawerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popup = new PopupMenu(getActivity(), menuView);
        MenuInflater inflate = popup.getMenuInflater();
        inflate.inflate(R.menu.menu_drawer, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.handleProductMenuItem:
                getAppActivity().addContentFragment(HandleCondimentsFragment.newInstance());
                return true;
            case R.id.settingsMenuItem:
                return true;
        }
        return false;
    }

    @OnClick(R.id.menuView)
    public void openMenu() {
        popup.show();
    }
}
