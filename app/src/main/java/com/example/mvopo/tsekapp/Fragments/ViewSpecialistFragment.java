package com.example.mvopo.tsekapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvopo.tsekapp.Helper.ListAdapter;
import com.example.mvopo.tsekapp.MainActivity;
import com.example.mvopo.tsekapp.Model.AffiliatedFacilitiesModel;
import com.example.mvopo.tsekapp.Model.SpecialistModel;
import com.example.mvopo.tsekapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewSpecialistFragment extends Fragment {
    public View view;
    ListView lv, lvMembers;
    ListAdapter adapter;
    ImageView btnSearch;
    EditText txtSearch;
    ManageSpecialistFragment msf;
    Menu menu;

    Bundle bundle = new Bundle();
    List<SpecialistModel> specialistModelList = new ArrayList<>();
    List<AffiliatedFacilitiesModel> affiliatedFacilitiesModelList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_layout, container, false);
        lv = view.findViewById(R.id.lv);
        txtSearch = view.findViewById(R.id.list_searchTxt);
        btnSearch = view.findViewById(R.id.list_searchBtn);
        msf=new ManageSpecialistFragment();

        specialistModelList.clear();
        specialistModelList = MainActivity.db.getSpecialists("");

        for(SpecialistModel model: specialistModelList){
            Log.e("specialist", "ViewSF fname: " + model.username +" id: "+model.id);
        }

        adapter = new ListAdapter(getContext(), R.layout.population_item, null, null, null,null, specialistModelList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                View focusedView = getActivity().getCurrentFocus();
                if (focusedView != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                bundle.putParcelable("specialist", specialistModelList.get(position));
                bundle.putBoolean("toUpdate", true);
                msf.setArguments(bundle);
                MainActivity.ft = MainActivity.fm.beginTransaction();
                MainActivity.ft.replace(R.id.fragment_container, msf).addToBackStack("").commit();
            }});

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = txtSearch.getText().toString();

                specialistModelList.clear();

                if (!search.isEmpty()) {
                    specialistModelList.addAll(MainActivity.db.getSpecialists(search));
                }else{
                    specialistModelList.addAll(MainActivity.db.getSpecialists(""));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.download_add, menu);

        this.menu = menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add_specialist:
                msf = new ManageSpecialistFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("specialist", new SpecialistModel("", "", "", "","") );
                bundle.putBoolean("toUpdate", false);
                msf.setArguments(bundle);
                MainActivity.ft = MainActivity.fm.beginTransaction();
                MainActivity.ft.replace(R.id.fragment_container, msf).addToBackStack("").commit();
                break;

            case R.id.action_download_specialist:
                break;
           /* case R.id.action_add_member:
                Calendar c = Calendar.getInstance();
                String famId = String.format("%02d", (c.get(Calendar.MONTH) + 1)) +  String.format("%02d", (c.get(Calendar.DAY_OF_MONTH))) +
                        String.format("%02d", (c.get(Calendar.YEAR))) + "-" + String.format("%04d", Integer.parseInt(MainActivity.user.id)) + "-" +
                        String.format("%02d", (c.get(Calendar.HOUR))) + String.format("%02d", (c.get(Calendar.MINUTE))) +
                        String.format("%02d", (c.get(Calendar.SECOND)));

                FamilyProfile familyProfile = new FamilyProfile("", "", famId, "", "", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "","", "", "1", "", "", "", ""
                        , "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "","");

                mpf = new ManagePopulationFragment();
                bundle.putParcelable("familyProfile", familyProfile);
                bundle.putBoolean("toUpdate", false);
                bundle.putBoolean("addHead", true);
                mpf.setArguments(bundle);
                MainActivity.ft = MainActivity.fm.beginTransaction();
                MainActivity.ft.replace(R.id.fragment_container, mpf).addToBackStack("").commit();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


}
