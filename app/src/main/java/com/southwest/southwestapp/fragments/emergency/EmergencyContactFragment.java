package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.models.Contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class EmergencyContactFragment extends EmergencyBase {

    private EditText mEditNewContactName;
    private EditText mEditNewContactPhone;
    private EditText mEditContactArea;

    public EmergencyContactFragment() {
    }

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

        return rootView;
    }

    @Override
    protected void addContactAction() {

        String name  = mEditNewContactName.getText().toString();
        String phone = mEditNewContactPhone.getText().toString();
        String area  = mEditContactArea.getText().toString();

        if(!TextUtils.isEmpty(name)
           && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(area) ){
            AppHelper.contacts.add(new Contact(name, phone, area));
            AppHelper.dialogManager.showToast(getContext(), getResources().getString(R.string.emergency_contact_new_contact_success));
            clearUI();
        }else{
            AppHelper.dialogManager.showToast(getContext(), getResources().getString(R.string.emergency_contact_new_contact_error));
        }

    }

    private void clearUI(){
        mEditNewContactName.setText("");
        mEditNewContactPhone.setText("");
        mEditContactArea.setText("");
    }

    @Override
    protected void continueAction() {
    }

}
