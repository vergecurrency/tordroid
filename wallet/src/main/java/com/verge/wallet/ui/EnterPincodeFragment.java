package com.vergepay.wallet.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vergepay.wallet.R;


public class EnterPincodeFragment extends DialogFragment {

    private EditText pinCode1;
    private EditText pinCode2;
    private EditText pinCode3;
    private EditText pinCode4;
    private Callback callback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_pincode, container, false);

        pinCode1 = ((EditText) view.findViewById(R.id.pincode_1));
        pinCode2 = ((EditText) view.findViewById(R.id.pincode_2));
        pinCode3 = ((EditText) view.findViewById(R.id.pincode_3));
        pinCode4 = ((EditText) view.findViewById(R.id.pincode_4));

        pinCode1.addTextChangedListener(new ViewSwitcher(pinCode2));
        pinCode2.addTextChangedListener(new ViewSwitcher(pinCode3));
        pinCode3.addTextChangedListener(new ViewSwitcher(pinCode4));
        pinCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (pinCode1.getText().length() > 0 && pinCode2.getText().length() > 0 &&
                        pinCode3.getText().length() > 0 && pinCode4.getText().length() > 0) {
                    if (callback != null) {
                        callback.onResult(getPinCode());
                        if (getShowsDialog()) {
                            dismiss();
                        }
                    }
                }
            }
        });

        return view;
    }

    public void setResultCallback(Callback callback) {
        this.callback = callback;
    }

    private String getPinCode() {
        return pinCode1.getText().toString() + pinCode2.getText().toString() + pinCode3.getText().toString()
                + pinCode4.getText().toString();
    }

    public interface Callback {
        void onResult(String pincode);
    }

    private static final class ViewSwitcher implements TextWatcher {

        private final EditText nextView;

        public ViewSwitcher(EditText nextView) {
            this.nextView = nextView;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                if (nextView == null) {

                } else {
                    nextView.requestFocus();
                }
            }
        }
    }
}