package com.croccio.gesty.view.base;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

public class ValidationListener implements Validator.ValidationListener {

    private DialogFragment dialogFragment;
    private Context context;
    private Fragment fragment;

    public ValidationListener(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public ValidationListener(Context context, DialogFragment dialogFragment) {
        this.context = context;
        this.dialogFragment = dialogFragment;
    }

    @Override
    public void onValidationSucceeded() {
        if (fragment != null) {
            fragment.onValidateSuccess();
        } else if (dialogFragment != null) {
            dialogFragment.onValidateSuccess();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        boolean first = true;
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(context);
            if (first) {
                view.requestFocus();
            }

            if (view.getParent().getParent() instanceof TextInputLayout) {
                TextInputLayout inputLayout = ((TextInputLayout) view.getParent().getParent());
                inputLayout.setErrorEnabled(true);
                inputLayout.setError(message);
            } else if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
            first = false;
        }
    }

}