package com.southwest.southwestapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zadtanikus on 15/09/15.
 */
public class PassengerVO implements Parcelable{

    private String name;
    private String group;
    private int position;

    public PassengerVO(String name, String group, int position) {
        this.name = name;
        this.group = group;
        this.position = position;
    }

    public PassengerVO(Parcel in){
        this.name = in.readString();
        this.group = in.readString();
        this.position = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.group);
        dest.writeInt(this.position);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public PassengerVO createFromParcel(Parcel in) {
                    return new PassengerVO(in);
                }

                public PassengerVO[] newArray(int size) {
                    return new PassengerVO[size];
                }
            };
}
