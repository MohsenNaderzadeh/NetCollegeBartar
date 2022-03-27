package bluedragon.app.netcollegebartar.Views.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.OtherRegisteredMajorsRecyclerViewAdapter;
import bluedragon.app.netcollegebartar.Adapters.RegisteredExClassRecyclerViewAdapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.OtherMajorRegisteredDataModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

/**
 * Created by Blue_Dragon on 6/27/2019.
 */
public class Fragment_Show_all_Other_Registered_Classes_Fragment extends Fragment implements ApiService.OnRegisteredOTherClassResult {
    View view;
    RecyclerView recyclerView;
    ProgressBar progressbar_registeredOthersclasses;
    TextView show_all_registered_Others_class_textview;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    OtherRegisteredMajorsRecyclerViewAdapter otherRegisteredMajorsRecyclerViewAdapter;
    ApiService apiService;
    Typeface typeface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_show_all_others_registered_classes,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.ShowallUserRegisteredOthersClasses);
        progressbar_registeredOthersclasses=(ProgressBar)view.findViewById(R.id.progressbar_registeredOthersclasses);
        show_all_registered_Others_class_textview=(TextView)view.findViewById(R.id.show_all_registered_Others_class_textview);
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getContext());
        apiService=new ApiService(getContext());
        typeface= Typeface.createFromAsset(getActivity().getAssets(),"fonts/iransansdn.ttf");
        show_all_registered_Others_class_textview.setTypeface(typeface);
        UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
        apiService.UserRegisteredOtherClassApi(AppConfig.URL_GET_USER_REGISTERED_Other_CLASSES,this,userInformationDataModel.getUser_ID());
        return view;
    }

    @Override
    public void OnRegisteredOTherClassResultRecieved(ArrayList<OtherMajorRegisteredDataModel> registeredOtherClassesList) {
        progressbar_registeredOthersclasses.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        otherRegisteredMajorsRecyclerViewAdapter=new OtherRegisteredMajorsRecyclerViewAdapter(getContext(),registeredOtherClassesList);
        recyclerView.setAdapter(otherRegisteredMajorsRecyclerViewAdapter);
    }

    @Override
    public void OnRegisteredOTherClassResultError() {
        progressbar_registeredOthersclasses.setVisibility(View.GONE);
        show_all_registered_Others_class_textview.setVisibility(View.VISIBLE);
    }
}
