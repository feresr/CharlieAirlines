package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


/**
 * Created by luisalfonsobejaranosanchez on 9/15/15.
 */
public abstract class EmergencyBase extends BaseFragment implements View.OnClickListener {

    protected Button mBtnContactAdd;
    protected Button mConfirmationButton;
    protected TextView mPassName;
    protected TextView mFooterInformation;
    protected Switch mSwitch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void init(View rootView) {
        mPassName = (TextView)rootView.findViewById(R.id.emergencyContactNamePass);
        mFooterInformation = (TextView)rootView.findViewById(R.id.confirmationFooterInformation);
        mConfirmationButton = (Button)rootView.findViewById(R.id.confirmationButton);
        mBtnContactAdd = (Button)rootView.findViewById(R.id.emergencyContactAdd);
        mSwitch = (Switch)rootView.findViewById(R.id.emergencyContactSwitch);
        mBtnContactAdd.setOnClickListener(this);
        mConfirmationButton.setOnClickListener(this);

        mFooterInformation.setText(Html.fromHtml(getResources().getString(R.string.emergency_contact_info)));
    }

    protected abstract void addContactAction();

    protected abstract void continueAction();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.emergencyContactAdd:
                addContactAction();
                break;

            case R.id.confirmationButton:
                continueAction();
                break;

        }
    }

}
