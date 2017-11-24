package com.croccio.gesty.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.croccio.gesty.R;
import com.mobsandgeeks.saripaar.Validator;

import butterknife.BindBool;

/**
 * Created by antonioscardigno on 23/11/17.
 */

public class DialogFragment extends android.support.v4.app.DialogFragment {

    protected Validator validator;

    @BindBool(R.bool.isTablet)
    public boolean isTablet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        validator = new Validator(this);
        validator.setValidationListener(new ValidationListener(getContext(), this));
    }

    public void onValidateSuccess() {

    }

}
