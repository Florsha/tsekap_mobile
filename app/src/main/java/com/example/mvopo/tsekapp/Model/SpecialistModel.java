package com.example.mvopo.tsekapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SpecialistModel implements Parcelable {
    public String id, username, fname, mname, lname;

    public SpecialistModel(String id, String username, String fname, String mname, String lname){
        this.id = id;
        this.username = username;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
    }
    protected SpecialistModel(Parcel in) {
        id = in.readString();
        username = in.readString();
        fname = in.readString();
        mname = in.readString();
        lname = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(fname);
        dest.writeString(mname);
        dest.writeString(lname);
    }

    public static final Creator<SpecialistModel> CREATOR = new Creator<SpecialistModel>() {
        @Override
        public SpecialistModel createFromParcel(Parcel in) {
            return new SpecialistModel(in);
        }

        @Override
        public SpecialistModel[] newArray(int size) {
            return new SpecialistModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
