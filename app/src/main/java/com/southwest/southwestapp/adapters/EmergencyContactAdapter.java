package com.southwest.southwestapp.adapters;

import com.southwest.southwestapp.R;
import com.southwest.southwestapp.models.Contact;

import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by luisalfonsobejaranosanchez on 9/14/15.
 */
public class EmergencyContactAdapter extends RecyclerView.Adapter<EmergencyContactAdapter.ContactViewHolder> {

    private List<Contact> mContacts;

    public EmergencyContactAdapter(List<Contact> mContacts){
        this.mContacts = mContacts;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.emergency_new_contact_item, viewGroup, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int index) {
        contactViewHolder.contactName.setText(mContacts.get(index).getName());
        contactViewHolder.contactArea.setText("("+mContacts.get(index).getArea()+")");
        contactViewHolder.contactPhone.setText(mContacts.get(index).getPhone());
        contactViewHolder.contactChecked.setChecked(false);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void add(Contact item) {
        mContacts.add(item);
        notifyItemInserted(mContacts.size() - 1);
    }

    private void remove(int position) {
        mContacts.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        TextView contactArea;
        TextView contactPhone;
        CheckBox contactChecked;

        ContactViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView)itemView.findViewById(R.id.emergencyItemName);
            contactArea = (TextView)itemView.findViewById(R.id.emergencyItemArea);
            contactPhone = (TextView)itemView.findViewById(R.id.emergencyItemPhone);
            contactChecked = (CheckBox)itemView.findViewById(R.id.emergencyItemChecked);
        }

    }

}
