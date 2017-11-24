package com.croccio.gesty.view.handleProducts.condiment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croccio.gesty.R;
import com.croccio.gesty.controller.BaseController;
import com.croccio.gesty.controller.CondimentController;
import com.croccio.gesty.model.Condiment;
import com.croccio.gesty.view.base.DialogFragment;
import com.croccio.gesty.view.base.Fragment;
import com.croccio.gesty.view.base.GridAutofitLayoutManager;
import com.croccio.gesty.view.handleProducts.adapter.RecyclerCondimentAdapter;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HandleCondimentsFragment extends Fragment implements BaseController.RetrievedCallback<Condiment> {

    @BindDimen(R.dimen.tabletCellWidth)
    int tabletCellWidth;

    @BindView(R.id.condimentsRecyclerView)
    RecyclerView condimentsRecyclerView;

    @BindView(R.id.noCondimentTextView)
    TextView noCondimentTextView;

    Unbinder unbinder;

    private CondimentController controller = new CondimentController();
    private RecyclerCondimentAdapter adapter;

    public HandleCondimentsFragment() {
    }

    public static HandleCondimentsFragment newInstance() {
        HandleCondimentsFragment fragment = new HandleCondimentsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handle_condiments, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        if (isTablet) {
            layoutManager = new GridAutofitLayoutManager(getContext(), tabletCellWidth);
        }

        adapter = new RecyclerCondimentAdapter(getContext());
        condimentsRecyclerView.setLayoutManager(layoutManager);
        condimentsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        condimentsRecyclerView.setAdapter(adapter);

        loadCondiment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.addCondimentButton)
    public void addCondiment() {
        CondimentDialog.newInstance(new CondimentDialog.Callback() {
            @Override
            public void onCondimentSaved() {
                loadCondiment();
            }
        }).show(getFragmentManager(), CondimentDialog.class.toString());
    }

    @Override
    public void onRetrieved(List<Condiment> retrieved) {
        if (retrieved.size() == 0) {
            noCondiment(R.string.noCondiment);
        } else {
            updateRecyclerView(retrieved);
        }
    }

    @Override
    public void onCannotRetrieve() {
        noCondiment(R.string.cannotLoadCondiment);
    }

    private void noCondiment(@StringRes int message) {
        condimentsRecyclerView.setVisibility(View.GONE);
        noCondimentTextView.setText(message);
        noCondimentTextView.setVisibility(View.VISIBLE);
    }

    private void updateRecyclerView(List<Condiment> condiments) {
        adapter.add(condiments);
    }

    @OnClick(R.id.noCondimentTextView)
    void loadCondiment() {
        controller.retrieve(this);
    }

}
