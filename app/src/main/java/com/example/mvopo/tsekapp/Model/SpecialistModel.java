package com.example.mvopo.tsekapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SpecialistModel implements Parcelable {
    public String id, code, name, affiliatedFacility, specialization;

    public SpecialistModel(String id, String code, String name, String affiliatedFacility, String specialization){
        this.id = id;
        this.code = code;
        this.name = name;
        this.affiliatedFacility = affiliatedFacility;
        this.specialization = specialization;
    }
    protected SpecialistModel(Parcel in) {
        id = in.readString();
        code = in.readString();
        name = in.readString();
        affiliatedFacility = in.readString();
        specialization = in.readString();

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(affiliatedFacility);
        dest.writeString(specialization);
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
