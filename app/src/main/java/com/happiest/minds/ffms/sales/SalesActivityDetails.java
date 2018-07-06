package com.happiest.minds.ffms.sales;

import android.os.Parcel;
import android.os.Parcelable;

public class SalesActivityDetails implements Parcelable {


    private String name;

    public SalesActivityDetails(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SalesActivityDetails(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SalesActivityDetails> CREATOR = new Creator<SalesActivityDetails>() {
        @Override
        public SalesActivityDetails createFromParcel(Parcel in) {
            return new SalesActivityDetails(in);
        }

        @Override
        public SalesActivityDetails[] newArray(int size) {
            return new SalesActivityDetails[size];
        }
    };
}
