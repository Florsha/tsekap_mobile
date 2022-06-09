package com.example.mvopo.tsekapp.Fragments;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.mvopo.tsekapp.Model.Constants;
import com.example.mvopo.tsekapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class ManageFacilityFragment extends Fragment implements View.OnClickListener {
    View view;
    ScrollView optionHolder;
    Button optionBtn, updateBtn;
    EditText txtServiceCapability, txtOwnership, txtFacilityStatus, txtVaccine, txtReferralStatus, txtTransport, txtPHIC,txtLicenseStatus;
    EditText txtDayFrom, txtDayTo, txtTimeFrom, txtTimeTo, txtSchedNote;

    EditText   txtOtherServices;
    TableLayout tblOtherServices, tblConsult, tblTbdots, tblAbtc, tblDental, tblLaboratory, tblFamPlan;

    EditText txtBirthing, txtDialysis;
    TextInputLayout tilBirthing, tilDialysis;

    EditText txtConsult, txtConsultPrivate, txtConsultPublic;
    TextInputLayout tilConsultPrivate, tilConsultPublic;

    EditText txtTbdots, txtTbdots1, txtTbdots2;
    TextInputLayout tilTbdots1, tilTbdots2;

    EditText txtAbtc, txtAbtc1, txtAbtc2, txtAbtc3;
    TextInputLayout tilAbtc1, tilAbtc2, tilAbtc3;

    EditText txtDentalServices, txtDentalExtract, txtDentalTempFill, txtDentalPermFill, txtDentalClean, txtDentalOrtho;
    TextInputLayout tilDentalExtract, tilDentalTempFill, tilDentalPermFill, tilDentalClean, tilDentalOrtho;

    EditText txtLaboratoryServices, txtLabXray, txtLabCBC, txtLabCreatine, txtLabECG, txtLabFBS, txtLabFecal, txtLabFOB, txtLabHbAIC, txtLabLipid, txtLabOGT, txtLabPap, txtLabSputum, txtLabUrine;
    TextInputLayout                 tilLabXray, tilLabCBC, tilLabCreatine, tilLabECG, tilLabFBS, tilLabFecal, tilLabFOB, tilLabHbAIC, tilLabLipid, tilLabOGT, tilLabPap, tilLabSputum, tilLabUrine;


    EditText txtFamPlan, txtFamNSV, txtFamBTL, txtFamCondom, txtFamLAM, txtFamProgesterone, txtFamImplant, txtFamPOP, txtFamCOC, txtFamPIC, txtFamCIC, txtFamInternal, txtFamPostpartum;
    TextInputLayout    tilFamNSV, tilFamBTL, tilFamCondom, tilFamLAM, tilFamProgesterone, tilFamImplant, tilFamPOP, tilFamCOC, tilFamPIC, tilFamCIC, tilFamInternal, tilFamPostpartum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_facility, container, false);
        findViewByIds();

        updateBtn.setOnClickListener(this);
        txtServiceCapability.setOnClickListener(this);
        txtOwnership.setOnClickListener(this);
        txtFacilityStatus.setOnClickListener(this);
        txtVaccine.setOnClickListener(this);
        txtReferralStatus.setOnClickListener(this);
        txtTransport.setOnClickListener(this);
        txtPHIC.setOnClickListener(this);
        txtLicenseStatus.setOnClickListener(this);

        txtDayFrom.setOnClickListener(this);
        txtDayTo.setOnClickListener(this);
        txtTimeFrom.setOnClickListener(this);
        txtTimeTo.setOnClickListener(this);

        txtConsult.setOnClickListener(this);
        txtTbdots.setOnClickListener(this);
        txtAbtc.setOnClickListener(this);
        txtOtherServices.setOnClickListener(this);
        txtDentalServices.setOnClickListener(this);
        txtLaboratoryServices.setOnClickListener(this);
        txtFamPlan.setOnClickListener(this);

        /**Other Services*/
        Constants.setMoneyTextWatcher(getContext(), txtBirthing);
        Constants.setMoneyTextWatcher(getContext(), txtDialysis);
        /**Consult*/
        Constants.setMoneyTextWatcher(getContext(), txtConsultPublic);
        Constants.setMoneyTextWatcher(getContext(), txtConsultPrivate);
        /**TBDOTS*/
        Constants.setMoneyTextWatcher(getContext(), txtTbdots1);
        Constants.setMoneyTextWatcher(getContext(), txtTbdots2);
        /**ABTC*/
        Constants.setMoneyTextWatcher(getContext(), txtAbtc1);
        Constants.setMoneyTextWatcher(getContext(), txtAbtc2);
        Constants.setMoneyTextWatcher(getContext(), txtAbtc3);
        /**Dental*/
        Constants.setMoneyTextWatcher(getContext(), txtDentalExtract);
        Constants.setMoneyTextWatcher(getContext(), txtDentalTempFill);
        Constants.setMoneyTextWatcher(getContext(), txtDentalPermFill);
        Constants.setMoneyTextWatcher(getContext(), txtDentalClean);
        Constants.setMoneyTextWatcher(getContext(), txtDentalOrtho);
        /**Lab*/
        Constants.setMoneyTextWatcher(getContext(), txtLabXray);
        Constants.setMoneyTextWatcher(getContext(), txtLabCBC);
        Constants.setMoneyTextWatcher(getContext(), txtLabCreatine);
        Constants.setMoneyTextWatcher(getContext(), txtLabECG);
        Constants.setMoneyTextWatcher(getContext(), txtLabFBS);
        Constants.setMoneyTextWatcher(getContext(), txtLabFecal);
        Constants.setMoneyTextWatcher(getContext(), txtLabFOB);
        Constants.setMoneyTextWatcher(getContext(), txtLabHbAIC);
        Constants.setMoneyTextWatcher(getContext(), txtLabLipid);
        Constants.setMoneyTextWatcher(getContext(), txtLabOGT);
        Constants.setMoneyTextWatcher(getContext(), txtLabPap);
        Constants.setMoneyTextWatcher(getContext(), txtLabSputum);
        Constants.setMoneyTextWatcher(getContext(), txtLabUrine);
        /**family Planning*/
        Constants.setMoneyTextWatcher(getContext(), txtFamNSV);
        Constants.setMoneyTextWatcher(getContext(), txtFamBTL);
        Constants.setMoneyTextWatcher(getContext(), txtFamCondom);
        Constants.setMoneyTextWatcher(getContext(), txtFamLAM);
        Constants.setMoneyTextWatcher(getContext(), txtFamProgesterone);
        Constants.setMoneyTextWatcher(getContext(), txtFamImplant);
        Constants.setMoneyTextWatcher(getContext(), txtFamPOP);
        Constants.setMoneyTextWatcher(getContext(), txtFamCOC);
        Constants.setMoneyTextWatcher(getContext(), txtFamPIC);
        Constants.setMoneyTextWatcher(getContext(), txtFamCIC);
        Constants.setMoneyTextWatcher(getContext(), txtFamInternal);
        Constants.setMoneyTextWatcher(getContext(), txtFamPostpartum);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.facility_serviceCapability:
                showOptionDialog(R.array.service_capability, null,txtServiceCapability);
                break;
            case R.id.facility_ownership:
                showOptionDialog(R.array.ownership, null,txtOwnership);
                break;
            case R.id.facility_hospitalStatus:
                showOptionDialog(R.array.facility_status, null, txtFacilityStatus);
                break;
            case R.id.facility_vaccine:
                showOptionDialog(R.array.yes_no, null,txtVaccine);
                break;
            case R.id.facility_referralStatus:
                showOptionDialog(R.array.referral_status, null, txtReferralStatus);
                break;
            case R.id.facility_transport:
                showOptionDialog(R.array.transport_type, null,txtTransport);
                break;
            case R.id.facility_phicStatus:
                showOptionDialog(R.array.accreditation_status, null, txtPHIC);
                break;

            case R.id.facility_licensingStatus:
                showOptionDialog(R.array.license_status, null, txtLicenseStatus);
                break;

            /**SCHEDULE*/
            case R.id.facility_dayFrom:
                showOptionDialog(R.array.weekdays, null,txtDayFrom);
                break;
            case R.id.facility_dayTo:
                showOptionDialog(R.array.weekdays, null,txtDayTo);
                break;
            case R.id.facility_timeFrom:
                showTimePicker(txtTimeFrom);
                break;
            case R.id.facility_timeTo:
                showTimePicker(txtTimeTo);
                break;
            /**Services Available*/
            case R.id.facility_otherServices:
                showCheckboxDialog(R.array.other_services, txtOtherServices);
                break;
            case R.id.facility_consultServices:
                showCheckboxDialog(R.array.consultation_services, txtConsult);
                break;
            case R.id.facility_tbdotsServices:
                showCheckboxDialog(R.array.tbdots_services, txtTbdots);
                break;
            case R.id.facility_abtcServices:
                showCheckboxDialog(R.array.abtc_services, txtAbtc);
                break;
            case R.id.facility_dentalServices:
                showCheckboxDialog(R.array.dental_services, txtDentalServices);
                break;
            case R.id.facility_labServices:
                showCheckboxDialog(R.array.laboratory_services, txtLaboratoryServices);
                break;
            case R.id.facility_famPlanServices:
                showCheckboxDialog(R.array.familyPlanning_services, txtFamPlan);
                break;

            case R.id.facilityBtn:
                Log.e("facility", tilBirthing.getEditText().getText().toString());
                break;
        }
    }

    public void  showOptionDialog(final int id, final List<String> list, final EditText txtView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.options_dialog, null);
        optionHolder = view.findViewById(R.id.option_holder);
        optionBtn = view.findViewById(R.id.optionBtn);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        String[] labels;

        if (id != 0) labels = getResources().getStringArray(id);
        else labels = list.toArray(new String[0]) ;

        final RadioGroup radioGroup = new RadioGroup(getContext());

        LinearLayout.LayoutParams rbParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rbParam.bottomMargin = 5;
        rbParam.topMargin = 5;

        for (int i = 0; i < labels.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setLayoutParams(rbParam);
            radioButton.setText(labels[i]);

            View lineView = new View(getContext());
            lineView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            lineView.setLayoutParams(params);


            radioGroup.addView(radioButton);
            radioGroup.addView(lineView);
        }

        optionHolder.addView(radioGroup);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        final AlertDialog optionDialog = builder.create();
        optionDialog.show();

        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton != null) {
                    txtView.setText(radioButton.getText());
                    txtView.setTag(radioButton.getId());
                } else {
                    txtView.setText("");
                    txtView.setTag("");
                }
                optionDialog.dismiss();
            }
        });
    }

    public void showTimePicker(EditText editText){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                editText.setText(  Constants.twoDigitFormat(selectedHour) + ":" + Constants.twoDigitFormat(selectedMinute) + ":00");
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.show();
    }

    String selectedCheckbox = ""; String[] labels = {};
    public void showCheckboxDialog(final int id, final EditText txtView) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.options_dialog, null);
        optionHolder = view.findViewById(R.id.option_holder);
        optionBtn = view.findViewById(R.id.optionBtn);
        selectedCheckbox = "";

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        final RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        if (id != 0) labels = getResources().getStringArray(id);

        final LinearLayout.LayoutParams rbParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rbParam.bottomMargin = 5;
        rbParam.topMargin = 5;

        final LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        final CheckBox[] checkBoxes= new CheckBox[labels.length];
        for (int i = 0; i < labels.length; i++) {

            CheckBox cb = new CheckBox(getContext());
            cb.setLayoutParams(rbParam);
            cb.setText(labels[i]);

            checkBoxes[i]=cb;

            View lineView = new View(getContext());
            lineView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            lineView.setLayoutParams(params);

            ll.addView(cb);
            ll.addView(lineView);
        }
        optionHolder.addView(ll);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        final AlertDialog optionDialog = builder.create();
        optionDialog.show();

        optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < labels.length; i++) {
                    if(checkBoxes[i].isChecked()){
                        selectedCheckbox +=(checkBoxes[i].getText().toString().trim() + ", ");
                    }
                }
                txtView.setText(selectedCheckbox);
                String[] split = selectedCheckbox.trim().split(",");

                if(txtView.getId() == R.id.facility_otherServices){
                    disableOtherServices();
                    if(!selectedCheckbox.trim().isEmpty()){
                        if(split.length==1 && split[0].trim().equalsIgnoreCase("Pharmacy"))
                            tblOtherServices.setVisibility(View.GONE);
                        else tblOtherServices.setVisibility(View.VISIBLE);

                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case "BIRTHING": enableCostOfService(tilBirthing);
                                    break;
                                case "DIALYSIS CENTER": enableCostOfService(tilDialysis);
                                    break;
                            }
                        }
                    }else tblOtherServices.setVisibility(View.GONE);
                }
                else if(txtView.getId() == R.id.facility_consultServices) {
                    disableConsult();
                    if (!selectedCheckbox.trim().isEmpty()) {
                        tblConsult.setVisibility(View.VISIBLE);
                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case "PRIVATE CLINIC": enableCostOfService(tilConsultPrivate);
                                    break;
                                case "PUBLIC CLINIC": enableCostOfService(tilConsultPublic);
                                    break;
                            }
                        }
                    }else tblConsult.setVisibility(View.GONE);
                }
                else if(txtView.getId() == R.id.facility_tbdotsServices){
                    disableTbdots();
                    if(!selectedCheckbox.trim().isEmpty()){
                        tblTbdots.setVisibility(View.VISIBLE);
                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case "CATEGORY 1": enableCostOfService(tilTbdots1);
                                    break;
                                case "CATEGORY 2": enableCostOfService(tilTbdots2);
                                    break;
                            }
                        }
                    } else tblTbdots.setVisibility(View.GONE);
                }
                else if(txtView.getId() == R.id.facility_abtcServices) {
                    disableAbtc();
                    if(!selectedCheckbox.trim().isEmpty()){
                        tblAbtc.setVisibility(View.VISIBLE);
                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case  "CATEGORY 1": enableCostOfService(tilAbtc1);
                                    break;
                                case  "CATEGORY 2": enableCostOfService(tilAbtc2);
                                    break;
                                case  "CATEGORY 3": enableCostOfService(tilAbtc3);
                                    break;
                            }
                        }
                    } else  tblAbtc.setVisibility(View.GONE);
                }
                else if(txtView.getId() == R.id.facility_dentalServices) {
                    disableDental();
                    if(!selectedCheckbox.trim().isEmpty()){
                        tblDental.setVisibility(View.VISIBLE);
                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case "EXTRACTION": enableCostOfService(tilDentalExtract);
                                    break;
                                case "TEMPORARY FILLING": enableCostOfService(tilDentalTempFill);
                                    break;
                                case "PERMANENT FILLING": enableCostOfService(tilDentalPermFill);
                                    break;
                                case "ORTHODONTICS": enableCostOfService(tilDentalOrtho);
                                    break;
                                case "ORAL PROPHYLAXIS (CLEANING)": enableCostOfService(tilDentalClean);
                                    break;
                            }
                        }
                    } else tblDental.setVisibility(View.GONE);
                }
                else if(txtView.getId() == R.id.facility_labServices) {
                    disableLaboratory();
                    if(!selectedCheckbox.trim().isEmpty()){
                        tblLaboratory.setVisibility(View.VISIBLE);
                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case "CHEST X-RAY": enableCostOfService(tilLabXray);
                                    break;
                                case "COMPLETE BLOOD COUNT": enableCostOfService(tilLabCBC);
                                    break;
                                case "CREATININE": enableCostOfService(tilLabCreatine);
                                    break;
                                case "ECG": enableCostOfService(tilLabECG);
                                    break;
                                case "FBS": enableCostOfService(tilLabFBS);
                                    break;
                                case "FECALYSIS": enableCostOfService(tilLabFecal);
                                    break;
                                case "FECAL OCCULT BLOOD": enableCostOfService(tilLabFOB);
                                    break;
                                case "HBAIC": enableCostOfService(tilLabHbAIC);
                                    break;
                                case "LIPID PROFILE": enableCostOfService(tilLabLipid);
                                    break;
                                case "ORAL GLUCOSE TOLERANCE": enableCostOfService(tilLabOGT);
                                    break;
                                case "PAP SMEAR": enableCostOfService(tilLabPap);
                                    break;
                                case "SPUTUM MICROSCOPY": enableCostOfService(tilLabSputum);
                                    break;
                                case "URINALYSIS": enableCostOfService(tilLabUrine);
                                    break;
                            }
                        }
                    } else  tblLaboratory.setVisibility(View.GONE);
                }
                else if(txtView.getId() == R.id.facility_famPlanServices){
                    disableFamPlan();
                    if(!selectedCheckbox.trim().isEmpty()){
                        tblFamPlan.setVisibility(View.VISIBLE);
                        for (String value : split) {
                            switch (value.trim().toUpperCase()) {
                                case "NSV": enableCostOfService(tilFamNSV);
                                    break;
                                case "BTL":enableCostOfService(tilFamBTL);
                                    break;
                                case "CONDOM":enableCostOfService(tilFamCondom);
                                    break;
                                case "LAM":enableCostOfService(tilFamLAM);
                                    break;
                                case "PROGESTERONE":enableCostOfService(tilFamProgesterone);
                                    break;
                                case "IMPLANT":enableCostOfService(tilFamImplant);
                                    break;
                                case "(ORAL PILLS) PROGESTERONE ONLY PILLS": enableCostOfService(tilFamPOP);
                                    break;
                                case "(ORAL PILLS) COMBINE ORAL CONTRACEPTIVE":enableCostOfService(tilFamCOC);
                                    break;
                                case "(DMPA) PURE INJECT CONTRACEPTIVE":enableCostOfService(tilFamPIC);
                                    break;
                                case "(DMPA) COMBINE INJECT CONTRACEPTIVE":enableCostOfService(tilFamCIC);
                                    break;
                                case "(IUD) INTERNAL":enableCostOfService(tilFamInternal);
                                    break;
                                case "(IUD) POSTPARTUM":enableCostOfService(tilFamPostpartum);
                                    break;
                            }
                        }
                    } else tblFamPlan.setVisibility(View.GONE);
                }

                optionDialog.dismiss();
            }
        });

    }

    public void setVisibilityAvailableServices(String s, String service){
        String[] split = s.split(",");

        switch (service){
            case  "others":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case  "BIRTHING": enableCostOfService(tilBirthing);
                            break;
                        case  "DIALYSIS CENTER": enableCostOfService(tilDialysis);
                            break;
                    }
                }
               break;
            case  "consult":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case  "PRIVATE CLINIC": enableCostOfService(tilConsultPrivate);
                            break;
                        case  "PUBLIC CLINIC": enableCostOfService(tilConsultPublic);
                            break;
                    }
                }
                break;

            case  "tbdots":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case  "CATEGORY 1": enableCostOfService(tilTbdots1);
                            break;
                        case  "CATEGORY 2": enableCostOfService(tilTbdots2);
                            break;
                    }
                }
                break;

            case  "abtc":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case  "CATEGORY 1": enableCostOfService(tilAbtc1);
                            break;
                        case  "CATEGORY 2": enableCostOfService(tilAbtc2);
                            break;
                        case  "CATEGORY 3": enableCostOfService(tilAbtc3);
                            break;
                    }
                }
                break;

            case  "dental":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case "EXTRACTION": enableCostOfService(tilDentalExtract);
                            break;
                        case "TEMPORARY FILLING": enableCostOfService(tilDentalTempFill);
                            break;
                        case "PERMANENT FILLING": enableCostOfService(tilDentalPermFill);
                            break;
                        case "ORTHODONTICS": enableCostOfService(tilDentalOrtho);
                            break;
                        case "ORAL PROPHYLAXIS (CLEANING)": enableCostOfService(tilDentalClean);
                            break;
                    }
                }
                break;

            case  "lab":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case "CHEST X-RAY": enableCostOfService(tilLabXray);
                            break;
                        case "COMPLETE BLOOD COUNT": enableCostOfService(tilLabCBC);
                            break;
                        case "CREATININE": enableCostOfService(tilLabCreatine);
                            break;
                        case "ECG": enableCostOfService(tilLabECG);
                            break;
                        case "FBS": enableCostOfService(tilLabFBS);
                            break;
                        case "FECALYSIS": enableCostOfService(tilLabFecal);
                            break;
                        case "FECAL OCCULT BLOOD": enableCostOfService(tilLabFOB);
                            break;
                        case "HBAIC": enableCostOfService(tilLabHbAIC);
                            break;
                        case "LIPID PROFILE": enableCostOfService(tilLabLipid);
                            break;
                        case "ORAL GLUCOSE TOLERANCE": enableCostOfService(tilLabOGT);
                            break;
                        case "PAP SMEAR": enableCostOfService(tilLabPap);
                            break;
                        case "SPUTUM MICROSCOPY": enableCostOfService(tilLabSputum);
                            break;
                        case "URINALYSIS": enableCostOfService(tilLabUrine);
                            break;
                    }
                }
                break;

            case  "famPlan":
                for (String value : split) {
                    switch (value.trim().toUpperCase()) {
                        case "NSV": enableCostOfService(tilFamNSV);
                            break;
                        case "BTL":enableCostOfService(tilFamBTL);
                            break;
                        case "CONDOM":enableCostOfService(tilFamCondom);
                            break;
                        case "LAM":enableCostOfService(tilFamLAM);
                            break;
                        case "PROGESTERONE":enableCostOfService(tilFamProgesterone);
                            break;
                        case "IMPLANT":enableCostOfService(tilFamImplant);
                            break;
                        case "(ORAL PILLS) PROGESTERONE ONLY PILLS": enableCostOfService(tilFamPOP);
                            break;
                        case "(ORAL PILLS) COMBINE ORAL CONTRACEPTIVE":enableCostOfService(tilFamCOC);
                            break;
                        case "(DMPA) PURE INJECT CONTRACEPTIVE":enableCostOfService(tilFamPIC);
                            break;
                        case "(DMPA) COMBINE INJECT CONTRACEPTIVE":enableCostOfService(tilFamCIC);
                            break;
                        case "(IUD) INTERNAL":enableCostOfService(tilFamInternal);
                            break;
                        case "(IUD) POSTPARTUM":enableCostOfService(tilFamPostpartum);
                            break;
                    }
                }
                break;
        }

    }
    public void findViewByIds(){
        updateBtn = view.findViewById(R.id.facilityBtn);
        txtServiceCapability = view.findViewById(R.id.facility_serviceCapability);
        txtOwnership = view.findViewById(R.id.facility_ownership);
        txtFacilityStatus = view.findViewById(R.id.facility_hospitalStatus);
        txtVaccine = view.findViewById(R.id.facility_vaccine);
        txtReferralStatus = view.findViewById(R.id.facility_referralStatus);
        txtTransport = view.findViewById(R.id.facility_transport);
        txtPHIC = view.findViewById(R.id.facility_phicStatus);
        txtLicenseStatus = view.findViewById(R.id.facility_licensingStatus);
        //Schedule
        txtDayFrom = view.findViewById(R.id.facility_dayFrom);
        txtDayTo = view.findViewById(R.id.facility_dayTo);
        txtTimeFrom = view.findViewById(R.id.facility_timeFrom);
        txtTimeTo = view.findViewById(R.id.facility_timeTo);
        txtSchedNote = view.findViewById(R.id.facility_schedNote);

        /**Services And costs*/
        tblOtherServices = view.findViewById(R.id.table_otherServices);
        txtOtherServices = view.findViewById(R.id.facility_otherServices);
        txtBirthing = view.findViewById(R.id.facility_birthing);
        txtDialysis = view.findViewById(R.id.facility_dialysis);

        tilBirthing = view.findViewById(R.id.facility_til_birthing);
        tilDialysis = view.findViewById(R.id.facility_til_dialysis);
            /**Consult*/
        tblConsult = view.findViewById(R.id.table_consult);
        txtConsult = view.findViewById(R.id.facility_consultServices);
        txtConsultPublic = view.findViewById(R.id.facility_consultPublic);
        txtConsultPrivate = view.findViewById(R.id.facility_consultPrivate);

        tilConsultPublic = view.findViewById(R.id.facility_til_consultPublic);
        tilConsultPrivate = view.findViewById(R.id.facility_til_consultPrivate);
            /**tbdots*/
        tblTbdots = view.findViewById(R.id.table_tbdots);
        txtTbdots = view.findViewById(R.id.facility_tbdotsServices);
        txtTbdots1 = view.findViewById(R.id.facility_tbdots1);
        txtTbdots2 = view.findViewById(R.id.facility_tbdots2);

        tilTbdots1 = view.findViewById(R.id.facility_til_tbdots1);
        tilTbdots2 = view.findViewById(R.id.facility_til_tbdots2);
            /**abtc*/
        tblAbtc = view.findViewById(R.id.table_abtc);
        txtAbtc = view.findViewById(R.id.facility_abtcServices);
        txtAbtc1 = view.findViewById(R.id.facility_abtc1);
        txtAbtc2 = view.findViewById(R.id.facility_abtc2);
        txtAbtc3 = view.findViewById(R.id.facility_abtc3);

        tilAbtc1 = view.findViewById(R.id.facility_til_abtc1);
        tilAbtc2 = view.findViewById(R.id.facility_til_abtc2);
        tilAbtc3 = view.findViewById(R.id.facility_til_abtc3);
            /**Laboratory*/
        txtLaboratoryServices = view.findViewById(R.id.facility_labServices);
        tblLaboratory = view.findViewById(R.id.table_laboratoryServices);
        txtLabXray = view.findViewById(R.id.facility_labXray);
        txtLabCBC = view.findViewById(R.id.facility_labCBC);
        txtLabCreatine = view.findViewById(R.id.facility_labCreatine);
        txtLabECG = view.findViewById(R.id.facility_labECG);
        txtLabFBS = view.findViewById(R.id.facility_labFBS);
        txtLabFecal = view.findViewById(R.id.facility_labFecalysis);
        txtLabFOB = view.findViewById(R.id.facility_labFOB);
        txtLabHbAIC = view.findViewById(R.id.facility_labHbAIC);
        txtLabLipid = view.findViewById(R.id.facility_labLipid);
        txtLabOGT = view.findViewById(R.id.facility_labOGT);
        txtLabPap = view.findViewById(R.id.facility_labPap);
        txtLabSputum = view.findViewById(R.id.facility_labsputum);
        txtLabUrine = view.findViewById(R.id.facility_labUrine);

        tilLabXray = view.findViewById(R.id.facility_til_labXray);
        tilLabCBC = view.findViewById(R.id.facility_til_labCBC);
        tilLabCreatine = view.findViewById(R.id.facility_til_labCreatine);
        tilLabECG = view.findViewById(R.id.facility_til_labECG);
        tilLabFBS = view.findViewById(R.id.facility_til_labFBS);
        tilLabFecal = view.findViewById(R.id.facility_til_labFecalysis);
        tilLabFOB = view.findViewById(R.id.facility_til_labFOB);
        tilLabHbAIC = view.findViewById(R.id.facility_til_labHbAIC);
        tilLabLipid = view.findViewById(R.id.facility_til_labLipid);
        tilLabOGT = view.findViewById(R.id.facility_til_labOGT);
        tilLabPap = view.findViewById(R.id.facility_til_labPap);
        tilLabSputum = view.findViewById(R.id.facility_til_labSputum);
        tilLabUrine = view.findViewById(R.id.facility_til_labUrine);

            /**Dental*/
        txtDentalServices = view.findViewById(R.id.facility_dentalServices);
        tblDental = view.findViewById(R.id.table_dentalServices);
        txtDentalExtract = view.findViewById(R.id.facility_dentalExtract);
        txtDentalTempFill = view.findViewById(R.id.facility_dentalTempFill);
        txtDentalPermFill = view.findViewById(R.id.facility_dentalPermFill);
        txtDentalClean = view.findViewById(R.id.facility_dentalClean);
        txtDentalOrtho = view.findViewById(R.id.facility_dentalOrtho);

        tilDentalExtract = view.findViewById(R.id.facility_til_dentalExtract);
        tilDentalTempFill = view.findViewById(R.id.facility_til_dentalTempFill);
        tilDentalPermFill = view.findViewById(R.id.facility_til_dentalPermFill);
        tilDentalClean = view.findViewById(R.id.facility_til_dentalClean);
        tilDentalOrtho = view.findViewById(R.id.facility_til_dentalOrtho);

            /**family planning*/
        tblFamPlan = view.findViewById(R.id.table_famplan);
        txtFamPlan = view.findViewById(R.id.facility_famPlanServices);

        txtFamNSV = view.findViewById(R.id.facility_famNsv);
        txtFamBTL = view.findViewById(R.id.facility_famBtl);
        txtFamCondom = view.findViewById(R.id.facility_famCondom);
        txtFamLAM = view.findViewById(R.id.facility_famLam);
        txtFamProgesterone = view.findViewById(R.id.facility_famProgesterone);
        txtFamImplant = view.findViewById(R.id.facility_famImplant);
        txtFamPOP = view.findViewById(R.id.facility_famPOP);
        txtFamCOC = view.findViewById(R.id.facility_famCOC);
        txtFamPIC = view.findViewById(R.id.facility_famPIC);
        txtFamCIC = view.findViewById(R.id.facility_famCIC);
        txtFamInternal = view.findViewById(R.id.facility_famInternal);
        txtFamPostpartum = view.findViewById(R.id.facility_famPostpartum);

        tilFamNSV = view.findViewById(R.id.facility_til_famNsv);
        tilFamBTL = view.findViewById(R.id.facility_til_famBtl);
        tilFamCondom = view.findViewById(R.id.facility_til_famCondom);
        tilFamLAM = view.findViewById(R.id.facility_til_famLam);
        tilFamProgesterone = view.findViewById(R.id.facility_til_famProgesterone);
        tilFamImplant = view.findViewById(R.id.facility_til_famImplant);
        tilFamPOP = view.findViewById(R.id.facility_til_famPOP);
        tilFamCOC = view.findViewById(R.id.facility_til_famCOC);
        tilFamPIC = view.findViewById(R.id.facility_til_famPIC);
        tilFamCIC = view.findViewById(R.id.facility_til_famCIC);
        tilFamInternal = view.findViewById(R.id.facility_til_famInternal);
        tilFamPostpartum = view.findViewById(R.id.facility_til_famPostpartum);

    }
    public void disableOtherServices(){
        tilBirthing.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilDialysis.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtBirthing.setEnabled(false);
        txtDialysis.setEnabled(false);
    }

    public void disableConsult(){
        tilConsultPrivate.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilConsultPublic.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtConsultPrivate.setEnabled(false);
        txtConsultPublic.setEnabled(false);
    }

    public void disableTbdots(){
        tilTbdots1.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilTbdots2.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtTbdots1.setEnabled(false);
        txtTbdots2.setEnabled(false);
    }

    public void disableAbtc(){
        tilAbtc1.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilAbtc2.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilAbtc3.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtAbtc1.setEnabled(false);
        txtAbtc2.setEnabled(false);
        txtAbtc3.setEnabled(false);
    }

    public void disableDental(){
        tilDentalExtract.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilDentalTempFill.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilDentalPermFill.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilDentalOrtho.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilDentalClean.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtDentalExtract.setEnabled(false);
        txtDentalTempFill.setEnabled(false);
        txtDentalPermFill.setEnabled(false);
        txtDentalOrtho.setEnabled(false);
        txtDentalClean.setEnabled(false);
    }

    public void disableLaboratory(){
        tilLabXray.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabCBC.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabCreatine.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabECG.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabFBS.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabFecal.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabFOB.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabHbAIC.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabLipid.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabOGT.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabPap.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabSputum.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilLabUrine.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtLabXray.setEnabled(false);
        txtLabCBC.setEnabled(false);
        txtLabCreatine.setEnabled(false);
        txtLabECG.setEnabled(false);
        txtLabFBS.setEnabled(false);
        txtLabFecal.setEnabled(false);
        txtLabFOB.setEnabled(false);
        txtLabHbAIC.setEnabled(false);
        txtLabLipid.setEnabled(false);
        txtLabOGT.setEnabled(false);
        txtLabPap.setEnabled(false);
        txtLabSputum.setEnabled(false);
        txtLabUrine.setEnabled(false);
    }

    public void disableFamPlan(){
        tilFamNSV.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamBTL.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamCondom.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamLAM.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamProgesterone.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamImplant.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamPOP.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamCOC.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamPIC.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamCIC.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamInternal.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
        tilFamPostpartum.setBackgroundColor(getResources().getColor(R.color.colorLightGray));

        txtFamNSV.setEnabled(false);
        txtFamBTL.setEnabled(false);
        txtFamCondom.setEnabled(false);
        txtFamLAM.setEnabled(false);
        txtFamProgesterone.setEnabled(false);
        txtFamImplant.setEnabled(false);
        txtFamPOP.setEnabled(false);
        txtFamCOC.setEnabled(false);
        txtFamPIC.setEnabled(false);
        txtFamCIC.setEnabled(false);
        txtFamInternal.setEnabled(false);
        txtFamPostpartum.setEnabled(false);


    }

    public void enableCostOfService(TextInputLayout til){
        til.setEnabled(true);
        til.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
    }
}
