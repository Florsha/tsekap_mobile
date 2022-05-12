package com.example.mvopo.tsekapp.Fragments;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button optionBtn;
    EditText txtServiceCapability, txtOwnership, txtHospitalStatus, txtVaccine, txtReferralUse, txtTransport;
    EditText txtDayFrom, txtDayTo, txtTimeFrom, txtTimeTo, txtSchedNote;
    EditText txtLaboratoryServices, txtDentalServices;
    EditText txtLabXray, txtLabCBC, txtLabCreatine, txtLabECG, txtLabFBS, txtLabFecal, txtLabFOB, txtLabHbAIC, txtLabLipid, txtLabOGT, txtLabPap, txtLabSputum, txtLabUrine;
    EditText   txtDentalCost1, txtDentalCost2, txtDentalCost3, txtDentalCost4;
    TableLayout tblDental, tblLaboratory;

    TextInputLayout tilLabXray, tilLabCBC, tilLabCreatine, tilLabECG, tilLabFBS, tilLabFecal, tilLabFOB, tilLabHbAIC, tilLabLipid, tilLabOGT, tilLabPap, tilLabSputum, tilLabUrine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_facility, container, false);
        findViewByIds();

        txtServiceCapability.setOnClickListener(this);
        txtOwnership.setOnClickListener(this);
        txtHospitalStatus.setOnClickListener(this);
        txtVaccine.setOnClickListener(this);
        txtReferralUse.setOnClickListener(this);
        txtTransport.setOnClickListener(this);

        txtDayFrom.setOnClickListener(this);
        txtDayTo.setOnClickListener(this);
        txtTimeFrom.setOnClickListener(this);
        txtTimeTo.setOnClickListener(this);

        txtDentalServices.setOnClickListener(this);
        txtLaboratoryServices.setOnClickListener(this);

        //Constants.setMoneyTextWatcher(getContext(), txtChiefCost);
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

        Constants.setMoneyTextWatcher(getContext(), txtDentalCost1);
        Constants.setMoneyTextWatcher(getContext(), txtDentalCost2);
        Constants.setMoneyTextWatcher(getContext(), txtDentalCost3);
        Constants.setMoneyTextWatcher(getContext(), txtDentalCost4);




        txtLaboratoryServices.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String text = txtLaboratoryServices.getText().toString().trim();
                if(!text.isEmpty()){
                    String[] split = text.split(",");
                    disableLaboratory();
                    tblLaboratory.setVisibility(View.VISIBLE);
                    setVisibilityAvailableServices(split);

                }else {
                    disableLaboratory();
                    tblLaboratory.setVisibility(View.GONE);
                }
            }
        });

        txtDentalServices.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String text = txtDentalServices.getText().toString().trim();
                if(!text.isEmpty()){
                    String[] split = text.split(",");
                    tblDental.setVisibility(View.VISIBLE);
                    setVisibilityAvailableServices(split);
                }else {
                    txtDentalCost1.setEnabled(false);
                    txtDentalCost2.setEnabled(false);
                    txtDentalCost3.setEnabled(false);
                    txtDentalCost4.setEnabled(false);
                    tblDental.setVisibility(View.GONE);
                }
            }
        });
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
                showOptionDialog(R.array.hospital_status, null,txtHospitalStatus);
                break;
            case R.id.facility_vaccine:
                showOptionDialog(R.array.yes_no, null,txtVaccine);
                break;
            case R.id.facility_referralUse:
                showOptionDialog(R.array.yes_no, null,txtReferralUse);
                break;
            case R.id.facility_transport:
                showOptionDialog(R.array.transport_type, null,txtTransport);
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
            case R.id.facility_laboratoryServices:
                showCheckboxDialog(R.array.laboratory_services, txtLaboratoryServices);
                break;

            case R.id.facility_dentalServices:
                showCheckboxDialog(R.array.dental_services, txtDentalServices);
                break;
        }
    }


    public void showOptionDialog(final int id, final List<String> list, final EditText txtView) {
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

                if(!selectedCheckbox.equalsIgnoreCase("")) {
                    txtView.setText(selectedCheckbox);
                    txtView.setTag("");

                } else {
                    txtView.setText("");
                    txtView.setTag("");
                }
                optionDialog.dismiss();
            }
        });

    }

    public void setVisibilityAvailableServices(String[] s){
        for (String value : s) {
            switch (value.trim().toUpperCase()) {
            /**Laboratory*/
                case "CHEST X-RAY":
                    txtLabXray.setEnabled(true);
                    tilLabXray.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "COMPLETE BLOOD COUNT":
                    txtLabCBC.setEnabled(true);
                    tilLabCBC.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "CREATININE":
                    txtLabCreatine.setEnabled(true);
                    tilLabCreatine.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "ECG":
                    txtLabECG.setEnabled(true);
                    tilLabECG.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "FBS":
                    txtLabFBS.setEnabled(true);
                    tilLabFBS.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "FECALYSIS":
                    txtLabFecal.setEnabled(true);
                    tilLabFecal.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "FECAL OCCULT BLOOD":
                    txtLabFOB.setEnabled(true);
                    tilLabFOB.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "HBAIC":
                    txtLabHbAIC.setEnabled(true);
                    tilLabHbAIC.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "LIPID PROFILE":
                    txtLabLipid.setEnabled(true);
                    tilLabLipid.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "ORAL GLUCOSE TOLERANCE":
                    txtLabOGT.setEnabled(true);
                    tilLabOGT.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "PAP SMEAR":
                    txtLabPap.setEnabled(true);
                    tilLabPap.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "SPUTUM MICROSCOPY":
                    txtLabSputum.setEnabled(true);
                    tilLabSputum.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;
                case "URINALYSIS":
                    txtLabUrine.setEnabled(true);
                    tilLabUrine.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_edittext,null));
                    break;

            /**Dental*/
                case "DENTAL 1":
                    txtDentalCost1.setEnabled(true);
                    break;
                case "DENTAL 2":
                    txtDentalCost2.setEnabled(true);
                    break;
                case "DENTAL 3":
                    txtDentalCost3.setEnabled(true);
                    break;
                case "DENTAL 4":
                    txtDentalCost4.setEnabled(true);
                    break;
            }

        }
    }
    public void findViewByIds(){
        txtServiceCapability = view.findViewById(R.id.facility_serviceCapability);
        txtOwnership = view.findViewById(R.id.facility_ownership);
        txtHospitalStatus = view.findViewById(R.id.facility_hospitalStatus);
        txtVaccine = view.findViewById(R.id.facility_vaccine);
        txtReferralUse = view.findViewById(R.id.facility_referralUse);
        txtTransport = view.findViewById(R.id.facility_transport);

        //Schedule
        txtDayFrom = view.findViewById(R.id.facility_dayFrom);
        txtDayTo = view.findViewById(R.id.facility_dayTo);
        txtTimeFrom = view.findViewById(R.id.facility_timeFrom);
        txtTimeTo = view.findViewById(R.id.facility_timeTo);
        txtSchedNote = view.findViewById(R.id.facility_schedNote);

        /**Services And costs*/
            /**Laboratory*/
        txtLaboratoryServices = view.findViewById(R.id.facility_laboratoryServices);
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
        txtDentalCost1 = view.findViewById(R.id.facility_dentalCost1);
        txtDentalCost2 = view.findViewById(R.id.facility_dentalCost2);
        txtDentalCost3 = view.findViewById(R.id.facility_dentalCost3);
        txtDentalCost4 = view.findViewById(R.id.facility_dentalCost4);
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
}
