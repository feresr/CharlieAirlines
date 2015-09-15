package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.models.Contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class EmergencyContactFragment extends EmergencyBase implements TextWatcher {

    private EditText mEditNewContactName;
    private EditText mEditNewContactPhone;
    private EditText mEditContactArea;

    private boolean validAddNewContact = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_emergency_contact, container, false);
        mEditNewContactName = (EditText)rootView.findViewById(R.id.emergencyContactName);
        mEditContactArea = (EditText)rootView.findViewById(R.id.emergencyContactArea);
        mEditNewContactPhone = (EditText)rootView.findViewById(R.id.emergencyContactNumber);
        init(rootView);
        mBtnContactAdd.setEnabled(false);

        mEditNewContactName.addTextChangedListener(this);
        mEditContactArea.addTextChangedListener(this);
        mEditNewContactPhone.addTextChangedListener(this);

        return rootView;

    }

    @Override
    protected void addContactAction() {

        if (validAddNewContact) {
            AppHelper.contacts.add(new Contact(mEditNewContactName.getText().toString(),
                                               mEditNewContactPhone.getText().toString(),
                                               mEditContactArea.getText().toString()));
            AppHelper.dialogManager.showToast(getContext(), getResources().getString(R.string.emergency_contact_new_contact_success));
            clearUI();
        } else {
            AppHelper.dialogManager.showToast(getContext(), getResources().getString(R.string.emergency_contact_new_contact_error));
        }

    }

    private boolean validateField() {

        if (mEditNewContactName.getText().toString().length() > 0 && mEditNewContactPhone.getText().toString().length() > 0
                && mEditContactArea.getText().toString().length() > 0) {
            return true;
        }

        return false;
    }

    private void clearUI() {
        mEditNewContactName.setText("");
        mEditNewContactPhone.setText("");
        mEditContactArea.setText("");
        AppHelper.screenManager.hideSoftKeyboard(getActivity());
    }

    @Override
    protected void continueAction() {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        validAddNewContact = validateField();
        mBtnContactAdd.setEnabled(validAddNewContact);
    }

}
