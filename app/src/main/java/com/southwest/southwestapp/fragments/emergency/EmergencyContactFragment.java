package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    }

    @Override
    protected void continueAction() {
    }

}
