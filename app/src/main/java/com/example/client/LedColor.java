package com.example.client;

import android.os.Parcel;
import android.os.Parcelable;

public enum LedColor implements Parcelable {
    BLACK,
    WHITE,
    RED,
    GREEN,
    BLUE,
    AMBER,
    SU_AMBER,
    OSLO_BLUE,
    OSLO_AMBER;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public static final Creator<LedColor> CREATOR = new Creator<LedColor>() {
        @Override
        public LedColor createFromParcel(Parcel in) {
            return LedColor.values()[in.readInt()];
        }

        @Override
        public LedColor[] newArray(int size) {
            return new LedColor[size];
        }
    };

    public String getName(){
        return this.name().toUpperCase();
    }
}
