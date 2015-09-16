package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.AppHelper;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.adapters.EmergencyContactAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by luisalfonsobejaranosanchez on 9/15/15.
 */
public class EmergencyContactListFragment extends EmergencyBase {

    private RecyclerView mContactList;
    private EmergencyContactAdapter mContactListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emergency_contact_list, container, false);

        mContactList = (RecyclerView)rootView.findViewById(R.id.emergencyContacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mContactList.setLayoutManager(layoutManager);
        mContactList.setHasFixedSize(true);
        mContactListAdapter = new EmergencyContactAdapter(AppHelper.contacts);
        mContactList.setAdapter(mContactListAdapter);
        init(rootView);

        return rootView;

    }

    @Override
    protected void addContactAction() {
        AppHelper.screenManager.showEmergencyContact(getActivity());
    }

    @Override
    protected void continueAction() {
        AppHelper.screenManager.showCheckInConfirmationScreen(getActivity());
    }

    public String getToolBarTitle() {
        return getResources().getString(R.string.emergency_contact_select_toolbar_title);
    }

}
