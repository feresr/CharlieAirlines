package com.southwest.southwestapp.utils;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.southwest.southwestapp.LabeledText;
import com.southwest.southwestapp.R;

/**
 * Created by zadtanikus on 15/09/15.
 */
public class CheckInViewHolder  extends RecyclerView.ViewHolder{

    public CardView mCardView;
    public LabeledText mLabeledFlight;
    public LabeledText mLabeledTime;
    public LabeledText mLabeledGate;
    public LabeledText mLabeledPassenger;
    public LabeledText mLabeledConfirmation;
    public LinearLayout mLinearWrapper;
    public LinearLayout linearText;
    public LinearLayout linearLabel;
    public Button mButton;

    public CheckInViewHolder(View itemView) {
        super(itemView);

        mCardView            = (CardView) itemView.findViewById(R.id.card_view);
        mLabeledFlight       = (LabeledText) itemView.findViewById(R.id.flight);
        mLabeledTime         = (LabeledText) itemView.findViewById(R.id.travel_time);
        mLabeledGate         = (LabeledText) itemView.findViewById(R.id.gate);
        mLinearWrapper       = (LinearLayout) itemView.findViewById(R.id.passengerWrapper);
        mLabeledPassenger    = (LabeledText) itemView.findViewById(R.id.passenger);
        mLabeledConfirmation = (LabeledText) itemView.findViewById(R.id.confirmation);
        mButton              = (Button) itemView.findViewById(R.id.btn_boarding_pass);
        linearText           = (LinearLayout) itemView.findViewById(R.id.alertWrapper);
        linearLabel          = (LinearLayout) itemView.findViewById(R.id.planesLabel);
    }


}
