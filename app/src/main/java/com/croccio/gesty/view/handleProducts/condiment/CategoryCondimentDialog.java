package com.croccio.gesty.view.handleProducts.condiment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.croccio.gesty.R;
import com.croccio.gesty.controller.BaseController;
import com.croccio.gesty.controller.CategoryCondimentController;
import com.croccio.gesty.model.Condiment;
import com.croccio.gesty.model.CondimentCategory;
import com.croccio.gesty.view.base.DialogFragment;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CategoryCondimentDialog extends DialogFragment {

    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @NotEmpty(trim = true, messageResId = R.string.condimentNameHint)
    @BindView(R.id.nameEditText)
    TextInputEditText nameEditText;
    @NotEmpty(trim = true, messageResId = R.string.priceToAddCategoryCondimentHint)
    @BindView(R.id.priceToAddEditText)
    TextInputEditText priceToAddEditText;
    @NotEmpty(trim = true, messageResId = R.string.priceToRemoveCategoryCondimentHint)
    @BindView(R.id.priceToRemoveEditText)
    TextInputEditText priceToRemoveEditText;

    Unbinder unbinder;
    private CondimentCategory categoryCondiment;
    private Callback callback;
    private CategoryCondimentController categoryCondimentController = new CategoryCondimentController();

    public static CategoryCondimentDialog newInstance(Callback callback) {
        CategoryCondimentDialog categoryCondimentDialog = new CategoryCondimentDialog();
        categoryCondimentDialog.setCallback(callback);
        return categoryCondimentDialog;
    }

    public static CategoryCondimentDialog newInstance(CondimentCategory condimentCategory, Callback callback) {
        CategoryCondimentDialog categoryCondimentDialog = new CategoryCondimentDialog();
        categoryCondimentDialog.setCategoryCondiment(condimentCategory);
        categoryCondimentDialog.setCallback(callback);
        return categoryCondimentDialog;
    }


    public void setCategoryCondiment(CondimentCategory categoryCondiment) {
        this.categoryCondiment = categoryCondiment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_condiment_category, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (categoryCondiment == null) {
            titleTextView.setText(R.string.addNewCondiment);
        } else {
            titleTextView.setText(R.string.editCondiment);
            nameEditText.setText(categoryCondiment.getName());
            priceToAddEditText.setText(getString(R.string.priceToAdd, categoryCondiment.getPriceToAdd()));
            priceToRemoveEditText.setText(getString(R.string.priceToRemove, categoryCondiment.getPriceToRemove()));
        }

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
    public void onValidateSuccess() {
        super.onValidateSuccess();
        boolean newCondimentCategory = false;
        if (categoryCondiment == null) {
            categoryCondiment = new CondimentCategory();
            newCondimentCategory = true;
        }

        categoryCondiment.setName(nameEditText.getText().toString());
        categoryCondiment.setPriceToAdd(Float.parseFloat(priceToAddEditText.getText().toString()));
        categoryCondiment.setPriceToRemove(Float.parseFloat(priceToRemoveEditText.getText().toString()));

        if (newCondimentCategory) {
            categoryCondimentController.save(categoryCondiment, new BaseController.SaveCallback<CondimentCategory>() {
                @Override
                public void onSaved(CondimentCategory saved) {
                    dismiss();
                    Snackbar.make(getView(), getString(R.string.condimentSaved), Snackbar.LENGTH_SHORT).show();

                    callback.onCategoryCondimentSaved();
                }

                @Override
                public void onNotSaved(CondimentCategory notSaved) {
                    Snackbar.make(getView(), R.string.condimentNotSaved, Snackbar.LENGTH_SHORT).show();

                }
            });
        } else {
            categoryCondimentController.update(categoryCondiment, new BaseController.UpdateCallback<CondimentCategory>() {
                @Override
                public void onUpdated(CondimentCategory updated) {
                    dismiss();
                    Snackbar.make(getView(), getString(R.string.condimentSaved), Snackbar.LENGTH_SHORT).show();

                    callback.onCategoryCondimentSaved();
                }

                @Override
                public void onNotUpdated(CondimentCategory notUpdated) {
                    Snackbar.make(getView(), R.string.condimentNotSaved, Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    interface Callback {
        void onCategoryCondimentSaved();
    }

}