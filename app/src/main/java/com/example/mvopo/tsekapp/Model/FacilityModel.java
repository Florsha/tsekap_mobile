package com.example.mvopo.tsekapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by romaine on 04/29/2022.
 */

public class FacilityModel implements Parcelable {

    public String id, facilityCode, facilityName, facilityAbbr, provinceId, municipalityId, barangayId, address,
            contact, email, ChiefName, serviceCapability, ownership, hospitalStatus, phicAccreditation, vaccineUsed,
    triCity, referralUsed, transport, latitude, longitude;

    public String schedDayFrom, schedDayTo, schedTimeFrom, schedTimeTo, schedNotes;

    public String laboratoryServices, DentalServices;

    public FacilityModel(String Id, String facilityCode, String facilityName, String facilityAbbr, String provinceId, String municipalityId, String barangayId, String address,
                         String contact, String email, String ChiefName, String serviceCapability, String ownership, String hospitalStatus, String phicAccreditation, String vaccineUsed,
                         String triCity, String referralUsed, String transport, String latitude, String longitude, String schedDayFrom, String schedDayTo, String schedTimeFrom, String schedTimeTo, String schedNotes){
        this.id=Id;
        this.facilityCode = facilityCode;
        this.facilityName = facilityName;
        this.facilityAbbr = facilityAbbr;
        this.provinceId = provinceId;
        this.municipalityId = municipalityId;
        this.barangayId = barangayId;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.ChiefName = ChiefName;
        this.serviceCapability = serviceCapability;
        this.ownership = ownership;
        this.hospitalStatus = hospitalStatus;
        this.phicAccreditation = phicAccreditation;
        this.vaccineUsed = vaccineUsed;
        this.triCity = triCity;
        this.referralUsed = referralUsed;
        this.transport = transport;
        this.latitude = latitude;
        this.longitude = longitude;
        this.schedDayFrom = schedDayFrom;
        this.schedDayTo = schedDayTo;
        this.schedTimeFrom = schedTimeFrom;
        this.schedTimeTo = schedTimeTo;
        this.schedNotes = schedNotes;
    }


    protected FacilityModel(Parcel in) {
        id = in.readString();
        facilityCode = in.readString();
        facilityName = in.readString();
        facilityAbbr = in.readString();
        provinceId = in.readString();
        municipalityId = in.readString();
        barangayId = in.readString();
        address = in.readString();

        contact = in.readString();
        email = in.readString();
        ChiefName = in.readString();
        serviceCapability = in.readString();
        ownership = in.readString();
        hospitalStatus = in.readString();
        phicAccreditation = in.readString();
        vaccineUsed = in.readString();
        triCity = in.readString();
        referralUsed = in.readString();
        transport = in.readString();
        latitude = in.readString();
        longitude = in.readString();

        schedDayFrom = in.readString();
        schedDayTo = in.readString();
        schedTimeFrom = in.readString();
        schedTimeTo = in.readString();
        schedNotes = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(facilityCode);
        dest.writeString(facilityName);
        dest.writeString(facilityAbbr);
        dest.writeString(provinceId);
        dest.writeString(municipalityId);
        dest.writeString(barangayId);
        dest.writeString(address);
        dest.writeString(contact);
        dest.writeString(email);
        dest.writeString(ChiefName);
        dest.writeString(serviceCapability);
        dest.writeString(ownership);
        dest.writeString(hospitalStatus);
        dest.writeString(phicAccreditation);
        dest.writeString(vaccineUsed);
        dest.writeString(triCity);
        dest.writeString(referralUsed);
        dest.writeString(transport);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(schedDayFrom);
        dest.writeString(schedDayTo);
        dest.writeString(schedTimeFrom);
        dest.writeString(schedTimeTo);
        dest.writeString(schedNotes);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FacilityModel> CREATOR = new Creator<FacilityModel>() {
        @Override
        public FacilityModel createFromParcel(Parcel in) {
            return new FacilityModel(in);
        }

        @Override
        public FacilityModel[] newArray(int size) {
            return new FacilityModel[size];
        }
    };
}
