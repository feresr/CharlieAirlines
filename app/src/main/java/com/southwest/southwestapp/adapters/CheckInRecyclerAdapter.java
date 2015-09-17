package com.southwest.southwestapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.southwest.southwestapp.LabeledText;
import com.southwest.southwestapp.R;
import com.southwest.southwestapp.utils.CheckInViewHolder;
import com.southwest.southwestapp.vo.CheckInVO;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zadtanikus on 15/09/15.
 */
public class CheckInRecyclerAdapter extends RecyclerView.Adapter<CheckInViewHolder> {

    private CheckInVO[] mDataSet;

    public CheckInRecyclerAdapter(CheckInVO[] mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public CheckInViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkin_detail,parent,false);
        CheckInViewHolder checkInViewHolder = new CheckInViewHolder(holderView);
        return checkInViewHolder;
    }

    @Override
    public void onBindViewHolder(CheckInViewHolder holder, int position) {
        Context cardContext = holder.mLinearWrapper.getContext();
        holder.mLabeledFlight.setMainText(mDataSet[position].getFlightNumber());
        holder.mLabeledTime.setMainText(mDataSet[position].getTravelTime());
        holder.mLabeledGate.setMainText(mDataSet[position].getGate());
        holder.mLabeledPassenger.setMainText(mDataSet[position].getPassengers()[0].getName());
        holder.mLabeledConfirmation.setMainText(mDataSet[position].getConfirmationNumber());



        for (int i = 0; i < mDataSet[position].getPassengers().length;i++){


            LinearLayout mLinearPassenger = new LinearLayout(cardContext);
            LinearLayout borderGray = new LinearLayout(cardContext);

            TextView pasajero = new TextView(cardContext);
            TextView labelBoarding = new TextView(cardContext);
            TextView boardingGroup = new TextView(cardContext);
            TextView labelPos = new TextView(cardContext);
            TextView pos = new TextView(cardContext);

            mLinearPassenger.setOrientation(LinearLayout.VERTICAL);
            borderGray.setOrientation(LinearLayout.HORIZONTAL);
            borderGray.setPadding(0, 30, 0, 0);

            LinearLayout.LayoutParams paramll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            paramll.setMargins(0, 0, 0, 30);
            borderGray.setLayoutParams(paramll);
            borderGray.setBackgroundDrawable((cardContext.getResources().getDrawable(R.drawable.rectangle_white_rounded, null)));


            if( i >= 1 || mDataSet.length > 1 ){
                pasajero.setTextColor(cardContext.getResources().getColor(R.color.dark_blue));
                pasajero.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
                pasajero.setTypeface(null, Typeface.BOLD);
                pasajero.setText(mDataSet[position].getPassengers()[i].getName());

                holder.mButton.setVisibility(Button.GONE);
                holder.linearText.setVisibility(LinearLayout.VISIBLE);

            }

            if(position >= 1){
                holder.linearLabel.setVisibility(LinearLayout.VISIBLE);
            }

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);
            param.gravity = Gravity.CENTER_VERTICAL;
            labelBoarding.setLayoutParams(param);
            labelBoarding.setText(R.string.check_in_boarding);

            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.25f);
            param2.gravity = Gravity.CENTER_VERTICAL;
            boardingGroup.setLayoutParams(param2);
            boardingGroup.setTextColor(cardContext.getResources().getColor(R.color.green));
            boardingGroup.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            boardingGroup.setTypeface(null, Typeface.BOLD);
            boardingGroup.setText(mDataSet[position].getPassengers()[i].getGroup());



            param.gravity = Gravity.CENTER_VERTICAL;
            labelBoarding.setLayoutParams(param);

            labelPos.setLayoutParams(param);
            labelPos.setText(R.string.check_in_position);

            pos.setLayoutParams(param2);
            pos.setLayoutParams(param2);
            pos.setTextColor(cardContext.getResources().getColor(R.color.green));
            pos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            pos.setTypeface(null, Typeface.BOLD);
            pos.setPadding(10,0,0,0);
            pos.setText(""+mDataSet[position].getPassengers()[i].getPosition());


            mLinearPassenger.addView(pasajero);
            borderGray.addView(labelBoarding);
            borderGray.addView(boardingGroup);
            borderGray.addView(labelPos);
            borderGray.addView(pos);


            holder.mLinearWrapper.addView(mLinearPassenger);
            holder.mLinearWrapper.addView(borderGray);

        }

    }

    @Override
    public int getItemCount() {
        if(mDataSet != null){
            return mDataSet.length;
        }else{
            return 0;
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
