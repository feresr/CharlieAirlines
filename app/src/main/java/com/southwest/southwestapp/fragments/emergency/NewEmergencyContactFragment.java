package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class NewEmergencyContactFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mContactList;

    private TextView mPassName;
    private EditText mEditNewContactName;
    private EditText mEditNewContactPhone;
    private EditText mEditContactArea;

    private Button mBtnContactAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_emergency_contact, container, false);

        mPassName = (TextView)view.findViewById(R.id.emergencyContactNamePass);
        mEditNewContactName = (EditText)view.findViewById(R.id.emergencyContactName);
        mEditContactArea = (EditText)view.findViewById(R.id.emergencyContactArea);
        mEditNewContactPhone = (EditText)view.findViewById(R.id.emergencyContactNumber);
        mBtnContactAdd = (Button)view.findViewById(R.id.emergencyContactAdd);

        return view;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case  R.id.emergencyContactAdd:

                break;

        }

    }



}
