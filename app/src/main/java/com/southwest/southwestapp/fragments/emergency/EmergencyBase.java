package com.southwest.southwestapp.fragments.emergency;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.fragments.BaseFragment;
import com.southwest.southwestapp.views.URLSpanNoUnderline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


/**
 * Created by luisalfonsobejaranosanchez on 9/15/15.
 */
public abstract class EmergencyBase extends BaseFragment implements View.OnClickListener {

    protected View contactContainer;
    protected Toolbar mToolbar;

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
        contactContainer = rootView.findViewById(R.id.emergencyContactContainer);
        mToolbar = (Toolbar)rootView.findViewById(R.id.toolbarGeneral);
        mPassName = (TextView)rootView.findViewById(R.id.emergencyContactNamePass);
        mFooterInformation = (TextView)rootView.findViewById(R.id.confirmationFooterInformation);
        mConfirmationButton = (Button)rootView.findViewById(R.id.confirmationButton);
        mBtnContactAdd = (Button)rootView.findViewById(R.id.emergencyContactAdd);
        mSwitch = (Switch)rootView.findViewById(R.id.emergencyContactSwitch);

        mFooterInformation.setText(Html.fromHtml(getResources().getString(R.string.emergency_contact_info)));
        removeUnderlines((Spannable)mFooterInformation.getText());

        contactContainer.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_bottom));
        setUpToolBar();

        mBtnContactAdd.setOnClickListener(this);
        mConfirmationButton.setOnClickListener(this);

        
    }

    private void setUpToolBar() {
        if (mToolbar != null) {
            mToolbar.setTitle(getToolBarTitle());
            if (mToolbar.getSubtitle() != null) {
                mToolbar.setSubtitle(null);
            }
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


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


    private void removeUnderlines(Spannable spannable) {
        URLSpan[] spans = spannable.getSpans(0, spannable.length(), URLSpan.class);

        for(URLSpan span:spans) {
            int start = spannable.getSpanStart(span);
            int end = spannable.getSpanEnd(span);
            spannable.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            spannable.setSpan(span, start, end, 0);
        }
    }

    protected abstract String getToolBarTitle();

    protected abstract void addContactAction();

    protected abstract void continueAction();

}
