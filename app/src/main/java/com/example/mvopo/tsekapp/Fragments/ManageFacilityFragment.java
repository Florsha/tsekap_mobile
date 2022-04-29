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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvopo.tsekapp.Model.Constants;
import com.example.mvopo.tsekapp.R;

import java.util.Calendar;
import java.util.List;

public class ManageFacilityFragment extends Fragment implements View.OnClickListener {
    View view;
    ScrollView optionHolder;
    Button optionBtn;
    EditText txtServiceCapability, txtOwnership, txtHospitalStatus, txtVaccine, txtReferralUse;
    EditText txtDayFrom, txtDayTo, txtTimeFrom, txtTimeTo, txtSchedNote;
    EditText txtLaboratoryServices, txtDentalServices;
    EditText   txtLabCost1, txtLabCost2, txtLabCost3, txtLabCost4, txtDentalCost1, txtDentalCost2, txtDentalCost3, txtDentalCost4;
    TableLayout tblDental, tblLaboratory;

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

        txtDayFrom.setOnClickListener(this);
        txtDayTo.setOnClickListener(this);
        txtTimeFrom.setOnClickListener(this);
        txtTimeTo.setOnClickListener(this);

        txtDentalServices.setOnClickListener(this);
        txtLaboratoryServices.setOnClickListener(this);

        //Constants.setMoneyTextWatcher(getContext(), txtChiefCost);
        Constants.setMoneyTextWatcher(getContext(), txtLabCost1);
        Constants.setMoneyTextWatcher(getContext(), txtLabCost2);
        Constants.setMoneyTextWatcher(getContext(), txtLabCost3);
        Constants.setMoneyTextWatcher(getContext(), txtLabCost4);
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
                    tblLaboratory.setVisibility(View.VISIBLE);
                    for(int x=0; x<split.length; x++){
                        switch (split[x].trim()){
                            case "Laboratory 1":
                                txtLabCost1.setEnabled(true);
                                break;
                            case "Laboratory 2":
                                txtLabCost2.setEnabled(true);
                                break;
                            case "Laboratory 3":
                                txtLabCost3.setEnabled(true);
                                break;
                            case "Laboratory 4":
                                txtLabCost4.setEnabled(true);
                                break;
                        }
                    }
                }else {
                    txtLabCost1.setEnabled(false);
                    txtLabCost2.setEnabled(false);
                    txtLabCost3.setEnabled(false);
                    txtLabCost4.setEnabled(false);
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
                    for(int x=0; x<split.length; x++){
                        switch (split[x].trim()){
                            case "Dental 1": txtDentalCost1.setEnabled(true);
                                break;
                            case "Dental 2": txtDentalCost2.setEnabled(true);
                                break;
                            case "Dental 3": txtDentalCost3.setEnabled(true);
                                break;
                            case "Dental 4": txtDentalCost4.setEnabled(true);
                                break;
                        }
                    }
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

                /**Services*/
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

    public void findViewByIds(){
        txtServiceCapability = view.findViewById(R.id.facility_serviceCapability);
        txtOwnership = view.findViewById(R.id.facility_ownership);
        txtHospitalStatus = view.findViewById(R.id.facility_hospitalStatus);
        txtVaccine = view.findViewById(R.id.facility_vaccine);
        txtReferralUse = view.findViewById(R.id.facility_referralUse);

        //Schedule
        txtDayFrom = view.findViewById(R.id.facility_dayFrom);
        txtDayTo = view.findViewById(R.id.facility_dayTo);
        txtTimeFrom = view.findViewById(R.id.facility_timeFrom);
        txtTimeTo = view.findViewById(R.id.facility_timeTo);
        txtSchedNote = view.findViewById(R.id.facility_schedNote);

        /**Services And costs*/
        txtLaboratoryServices = view.findViewById(R.id.facility_laboratoryServices);
        tblLaboratory = view.findViewById(R.id.table_laboratoryServices);
        txtLabCost1 = view.findViewById(R.id.facility_labCost1);
        txtLabCost2 = view.findViewById(R.id.facility_labCost2);
        txtLabCost3 = view.findViewById(R.id.facility_labCost3);
        txtLabCost4 = view.findViewById(R.id.facility_labCost4);

        txtDentalServices = view.findViewById(R.id.facility_dentalServices);
        tblDental = view.findViewById(R.id.table_dentalServices);
        txtDentalCost1 = view.findViewById(R.id.facility_dentalCost1);
        txtDentalCost2 = view.findViewById(R.id.facility_dentalCost2);
        txtDentalCost3 = view.findViewById(R.id.facility_dentalCost3);
        txtDentalCost4 = view.findViewById(R.id.facility_dentalCost4);
    }
}
