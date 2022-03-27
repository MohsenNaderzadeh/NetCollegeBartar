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

import bluedragon.app.netcollegebartar.Adapters.ExListRecyclerViewAdapter;
import bluedragon.app.netcollegebartar.Adapters.RegisteredExClassRecyclerViewAdapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.RegisteredExClassesDataModel;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SharedPrefManager.UserInformationSharedPrefManager;

/**
 * Created by Blue_Dragon on 6/27/2019.
 */
public class Fragment_show_all_my_registered_exclasses extends Fragment implements ApiService.OnRegisteredExClassResult {
    View view;
    RecyclerView ShowallUserRegisteredExClasses;
    RegisteredExClassRecyclerViewAdapter registeredExClassRecyclerViewAdapter;
    TextView show_all_registered_ex_class_textview;
    ProgressBar progressbar_registeredexclasses;
    UserInformationSharedPrefManager userInformationSharedPrefManager;
    ApiService apiService;
    Typeface typeface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show_all_my_registered_exclasses,container,false);
        ShowallUserRegisteredExClasses=view.findViewById(R.id.ShowallUserRegisteredExClasses);
        apiService=new ApiService(getContext());
        userInformationSharedPrefManager=new UserInformationSharedPrefManager(getContext());
        typeface= Typeface.createFromAsset(getActivity().getAssets(),"fonts/iransansdn.ttf");
        show_all_registered_ex_class_textview=view.findViewById(R.id.show_all_registered_ex_class_textview);
        show_all_registered_ex_class_textview.setTypeface(typeface);
        progressbar_registeredexclasses=view.findViewById(R.id.progressbar_registeredexclasses);
        UserInformationDataModel userInformationDataModel=userInformationSharedPrefManager.getRegisterationInfo();
        apiService.UserRegisteredExClassApi(AppConfig.URL_GET_USER_REGISTERED_EX_CLASSES,Fragment_show_all_my_registered_exclasses.this,userInformationDataModel.getUser_ID());
        return view;
    }

    @Override
    public void OnRegisteredExClassResultRecieved(ArrayList<RegisteredExClassesDataModel> registeredExClassesList) {
        progressbar_registeredexclasses.setVisibility(View.GONE);
        ShowallUserRegisteredExClasses.setVisibility(View.VISIBLE);
        ShowallUserRegisteredExClasses.setHasFixedSize(true);
        ShowallUserRegisteredExClasses.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        registeredExClassRecyclerViewAdapter=new RegisteredExClassRecyclerViewAdapter(getContext(),registeredExClassesList);
        ShowallUserRegisteredExClasses.setAdapter(registeredExClassRecyclerViewAdapter);
    }

    @Override
    public void OnRegisteredExClassResultError() {
        progressbar_registeredexclasses.setVisibility(View.GONE);
        show_all_registered_ex_class_textview.setVisibility(View.VISIBLE);

    }
}
