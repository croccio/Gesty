package com.croccio.gesty.view.handleProducts.condiment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.croccio.gesty.R;
import com.croccio.gesty.controller.BaseController;
import com.croccio.gesty.controller.CategoryCondimentController;
import com.croccio.gesty.controller.CondimentController;
import com.croccio.gesty.model.Condiment;
import com.croccio.gesty.model.CondimentCategory;
import com.croccio.gesty.view.base.ClickToSelectEditText;
import com.croccio.gesty.view.base.DialogFragment;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CondimentDialog extends DialogFragment implements BaseController.RetrievedCallback<CondimentCategory> {

    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @NotEmpty(trim = true, messageResId = R.string.condimentNameHint)
    @BindView(R.id.nameEditText)
    TextInputEditText nameEditText;
    @NotEmpty(trim = true, messageResId = R.string.categoryCondimentHint)
    @BindView(R.id.selectCategoryEditText)
    ClickToSelectEditText<CondimentCategory> selectCategoryEditText;
    @BindView(R.id.priceToAddEditText)
    TextInputEditText priceToAddEditText;
    @BindView(R.id.priceToRemoveEditText)
    TextInputEditText priceToRemoveEditText;

    Unbinder unbinder;
    private Condiment condiment;
    private Callback callback;
    private CategoryCondimentController categoryCondimentController = new CategoryCondimentController();
    private CondimentController condimentController = new CondimentController();

    private CondimentCategory condimentCategorySelected;

    public static CondimentDialog newInstance(Callback callback) {
        CondimentDialog condimentDialog = new CondimentDialog();
        condimentDialog.setCallback(callback);
        return condimentDialog;
    }

    public static CondimentDialog newInstance(Condiment condiment, Callback callback) {
        CondimentDialog condimentDialog = new CondimentDialog();
        condimentDialog.setCondiment(condiment);
        condimentDialog.setCallback(callback);
        return condimentDialog;
    }

    public void setCondiment(Condiment condiment) {
        this.condiment = condiment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_condiment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadCondimentCategory();

        if (condiment == null) {
            titleTextView.setText(R.string.addNewCondiment);
        } else {
            titleTextView.setText(R.string.editCondiment);
            nameEditText.setText(condiment.getName());
            selectCategoryEditText.setText(condiment.getCategory().getName());
            priceToAddEditText.setText(getString(R.string.priceToAdd, condiment.getPriceToAdd()));
            priceToRemoveEditText.setText(getString(R.string.priceToRemove, condiment.getPriceToRemove()));
        }

    }

    private void loadCondimentCategory() {
        categoryCondimentController.retrieve(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.cancelButton)
    public void onCancelButtonClicked() {
        dismiss();
    }

    @OnClick(R.id.okButton)
    public void onOkButtonClicked() {
        validator.validate(true);
    }

    @Override
    public void onRetrieved(List<CondimentCategory> retrieved) {
        selectCategoryEditText.setItems(retrieved);
        selectCategoryEditText.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<CondimentCategory>() {
            @Override
            public void onItemSelectedListener(CondimentCategory item, int selectedIndex) {
                condimentCategorySelected = item;
            }
        });
    }

    @Override
    public void onCannotRetrieve() {
        Snackbar.make(getView(), "Impossibile caricare le categorie dei prodotti.", Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.tryAgain), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCondimentCategory();
            }
        });
    }

    @Override
    public void onValidateSuccess() {
        super.onValidateSuccess();
        boolean newCondiment = false;
        if (condiment == null) {
            condiment = new Condiment();
            newCondiment = true;
        }

        condiment.setName(nameEditText.getText().toString());
        condiment.setCategory(condimentCategorySelected);
        condiment.setPriceToAdd(priceToAddEditText.getText().toString().length() == 0 ? 0.0f : Float.parseFloat(priceToAddEditText.getText().toString()));
        condiment.setPriceToRemove(priceToRemoveEditText.getText().toString().length() == 0 ? 0.0f : Float.parseFloat(priceToRemoveEditText.getText().toString()));

        if (newCondiment) {
            condimentController.save(condiment, new BaseController.SaveCallback<Condiment>() {
                @Override
                public void onSaved(Condiment saved) {
                    dismiss();
                    Snackbar.make(getView(), getString(R.string.condimentSaved), Snackbar.LENGTH_SHORT).show();

                    callback.onCondimentSaved();
                }

                @Override
                public void onNotSaved(Condiment notSaved) {
                    Snackbar.make(getView(), R.string.condimentNotSaved, Snackbar.LENGTH_SHORT).show();

                }
            });
        } else {
            condimentController.update(condiment, new BaseController.UpdateCallback<Condiment>() {
                @Override
                public void onUpdated(Condiment updated) {
                    dismiss();
                    Snackbar.make(getView(), getString(R.string.condimentSaved), Snackbar.LENGTH_SHORT).show();

                    callback.onCondimentSaved();
                }

                @Override
                public void onNotUpdated(Condiment notUpdated) {
                    Snackbar.make(getView(), R.string.condimentNotSaved, Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @OnClick(R.id.addCategoryButton)
    public void addNewCategory() {
        CategoryCondimentDialog.newInstance(new CategoryCondimentDialog.Callback() {
            @Override
            public void onCategoryCondimentSaved() {
                loadCondimentCategory();
            }
        }).show(getFragmentManager(), CategoryCondimentDialog.class.toString());
    }

    interface Callback {
        void onCondimentSaved();
    }

}