package com.example.mvopo.tsekapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class AffiliatedFacilitiesModel implements Parcelable {
    public String id, username, facility_id, specialization, contact, email, schedule, fee;

    public AffiliatedFacilitiesModel(String id, String username, String facility_id, String specialization, String contact, String email, String schedule, String fee){
        this.id = id;
        this.username = username;
        this.facility_id = facility_id;
        this.specialization = specialization;
        this.contact = contact;
        this.email = email;
        this.schedule = schedule;
        this.fee = fee;
    }


    protected AffiliatedFacilitiesModel(Parcel in) {
        id = in.readString();
        username = in.readString();
        facility_id = in.readString();
        specialization = in.readString();
        contact = in.readString();
        email = in.readString();
        schedule = in.readString();
        fee = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(facility_id);
        dest.writeString(specialization);
        dest.writeString(contact);
        dest.writeString(email);
        dest.writeString(schedule);
        dest.writeString(fee);
    }

    public static final Creator<AffiliatedFacilitiesModel> CREATOR = new Creator<AffiliatedFacilitiesModel>() {
        @Override
        public AffiliatedFacilitiesModel createFromParcel(Parcel in) {
            return new AffiliatedFacilitiesModel(in);
        }

        @Override
        public AffiliatedFacilitiesModel[] newArray(int size) {
            return new AffiliatedFacilitiesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
