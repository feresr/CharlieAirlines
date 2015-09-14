package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.EmergencyContactAdapter;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.models.Contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class EmergencyContactFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mContactList;
    private EmergencyContactAdapter mContactListAdapter;

    private TextView mPassName;
    private EditText mEditNewContactName;
    private EditText mEditNewContactPhone;
    private EditText mEditContactArea;

    private Button mBtnContactAdd;

    public EmergencyContactFragment() {
    }

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

        mContactList = (RecyclerView) view.findViewById(R.id.emergencyContacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mContactList.setLayoutManager(layoutManager);
        mContactList.setHasFixedSize(true);

        mContactListAdapter = new EmergencyContactAdapter();
        mContactList.setAdapter(mContactListAdapter);

        return view;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case  R.id.emergencyContactAdd:
                String name  = mEditNewContactName.getText().toString();
                String phone = mEditNewContactPhone.getText().toString();
                String area  = mEditContactArea.getText().toString();

                if(!TextUtils.isEmpty(name) &&
                    TextUtils.isEmpty(phone) && TextUtils.isEmpty(area)){
                    mContactListAdapter.add(new Contact(name,phone,area));
                }else{

                }

                break;

        }

    }


}
