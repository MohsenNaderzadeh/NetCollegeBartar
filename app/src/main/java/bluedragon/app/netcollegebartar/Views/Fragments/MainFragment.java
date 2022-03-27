package bluedragon.app.netcollegebartar.Views.Fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.Departemants_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Services_Adapter;
import bluedragon.app.netcollegebartar.Adapters.SliderAdapter;
import bluedragon.app.netcollegebartar.Adapters.Teachers_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.Services;
import bluedragon.app.netcollegebartar.DataModels.Teachers;
import bluedragon.app.netcollegebartar.ImageLoadingService.ImageLoadingService1;
import bluedragon.app.netcollegebartar.SqliteManager.DepartemantsAsyncTask;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;
import bluedragon.app.netcollegebartar.Views.Activities.DepartemantsActivity;
import bluedragon.app.netcollegebartar.Views.Activities.OtherServicesfullactivity;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.Views.Activities.Teachers_Activity;
import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

/**
 * Created by Blue_Dragon on 11/29/2018.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    ImageLoadingService1 imageLoadingService1;
    Slider slider;
    View rootview;
    ArrayList<Departemants> Departemants_List;
    ArrayList<Teachers> Teachers_list;
    ArrayList<Services> Services_list;
    //the recyclerviews
    RecyclerView Departemants_recyclerView;
    RecyclerView Teachers_recyclerview;
    RecyclerView Services_recyclerview;
    SwipeRefreshLayout refreshLayout;
    Typeface typeface;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView Departemans_show_all_txt1;
    TextView Teacher_show_all_txt1;
    TextView services_show_all_txt1;
    TextView Departemans_title1;
    TextView Teachers_title1;
    TextView services_title1;
    ApiService apiService;
    ProgressBar Progressbar_departemant;
    ProgressBar Progressbar_teacher;
    ProgressBar Progressbar_Other_Major;
    SqliteManager sqliteManager;
    DepartemantsAsyncTask departemantsAsyncTask;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageLoadingService1 = new ImageLoadingService1(getContext());
        rootview = inflater.inflate(R.layout.fragment_main_layout, container, false);
        setviews(rootview);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshlayout();
                refreshLayout.setRefreshing(false);
            }
        });
        Normal_Execute();
        return rootview;
    }


    private void setviews(View rootview) {
        apiService = new ApiService(getActivity());
        Get_Graphical_Controls(rootview);
        Set_Recycler_Views_Settings();
        Set_Typeface_For_Text_Views();
        sqliteManager=new SqliteManager(getContext());

    }

    private void refreshlayout() {
        Departemants_recyclerView.setVisibility(View.INVISIBLE);
        txt1.setVisibility(View.INVISIBLE);
        Progressbar_departemant.setVisibility(View.VISIBLE);
        Teachers_recyclerview.setVisibility(View.INVISIBLE);
        txt2.setVisibility(View.INVISIBLE);
        Progressbar_teacher.setVisibility(View.VISIBLE);
        Services_recyclerview.setVisibility(View.INVISIBLE);
        txt3.setVisibility(View.INVISIBLE);
        Progressbar_Other_Major.setVisibility(View.VISIBLE);
        if(apiService.internetConnection())
        {
            apiService.loadDepartemants(AppConfig.URL_Departemants, Departemants_List, Departemants_recyclerView, Progressbar_departemant, txt1,sqliteManager);
            apiService.loadTeachers(AppConfig.URL_Teachers, Teachers_list, Teachers_recyclerview, Progressbar_teacher, txt2,sqliteManager);
            apiService.LoadServices(AppConfig.URL_Services, Services_list, Services_recyclerview, Progressbar_Other_Major, txt3,sqliteManager);

        }else
        {
            if(sqliteManager.checkDepatemantsTBAvailbilty() && sqliteManager.checkTeachersTBAvailbilty() && sqliteManager.checkTeachersTBAvailbilty())
            {
                Departemants_Adapter adapter = new Departemants_Adapter(getContext(), sqliteManager.getDepartemantsListForMainPageOffline());
                Departemants_recyclerView.setAdapter(adapter);
                Progressbar_departemant.setVisibility(View.INVISIBLE);
                Departemants_recyclerView.setVisibility(View.VISIBLE);


                Teachers_Adapter teachers_adapter = new Teachers_Adapter(getContext(), sqliteManager.getTeachersListForMainPage());
                Teachers_recyclerview.setAdapter(teachers_adapter);
                Progressbar_teacher.setVisibility(View.INVISIBLE);
                Teachers_recyclerview.setVisibility(View.VISIBLE);

                Services_Adapter adapters = new Services_Adapter(getContext(), sqliteManager.getOtherMajorListOfflineForMainPage());
                Services_recyclerview.setAdapter(adapters);
                Progressbar_Other_Major.setVisibility(View.INVISIBLE);
                Services_recyclerview.setVisibility(View.VISIBLE);

            }
        }
        imageLoadingService1 = new ImageLoadingService1(getContext());
        Slider.init(imageLoadingService1);
        slider.setAdapter(new SliderAdapter());

    }

    private void Normal_Execute() {
        if(apiService.internetConnection())
        {
            apiService.loadDepartemants(AppConfig.URL_Departemants, Departemants_List, Departemants_recyclerView, Progressbar_departemant, txt1,sqliteManager);
            apiService.loadTeachers(AppConfig.URL_Teachers, Teachers_list, Teachers_recyclerview, Progressbar_teacher, txt2,sqliteManager);
            apiService.LoadServices(AppConfig.URL_Services, Services_list, Services_recyclerview, Progressbar_Other_Major, txt3,sqliteManager);

        }else
        {
            if(sqliteManager.checkDepatemantsTBAvailbilty() && sqliteManager.checkTeachersTBAvailbilty() && sqliteManager.checkTeachersTBAvailbilty())
            {
                Departemants_Adapter adapter = new Departemants_Adapter(getContext(), sqliteManager.getDepartemantsListForMainPageOffline());
                Departemants_recyclerView.setAdapter(adapter);
                Progressbar_departemant.setVisibility(View.INVISIBLE);
                Departemants_recyclerView.setVisibility(View.VISIBLE);


                Teachers_Adapter teachers_adapter = new Teachers_Adapter(getContext(), sqliteManager.getTeachersListForMainPage());
                Teachers_recyclerview.setAdapter(teachers_adapter);
                Progressbar_teacher.setVisibility(View.INVISIBLE);
                Teachers_recyclerview.setVisibility(View.VISIBLE);

                Services_Adapter adapters = new Services_Adapter(getContext(), sqliteManager.getOtherMajorListOfflineForMainPage());
                Services_recyclerview.setAdapter(adapters);
                Progressbar_Other_Major.setVisibility(View.INVISIBLE);
                Services_recyclerview.setVisibility(View.VISIBLE);

            }
        }


        //getting the Departemants_recyclerview from xml
        setviews(rootview);
        Slider.init(imageLoadingService1);
        slider.setAdapter(new SliderAdapter());
        slider.setSelectedSlide(0);
        slider.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(int position) {
                Slider_Items_Click(position);
            }
        });

        Show_All_Text_views_Click();
    }

    private void Show_All_Text_views_Click() {
        Departemans_show_all_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Departemants_List_Intent = new Intent(getActivity(), DepartemantsActivity.class);
                startActivity(Departemants_List_Intent);
                getActivity().finish();

            }
        });
        Teacher_show_all_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Teachers_Activity_Intent = new Intent(getActivity(), Teachers_Activity.class);
                startActivity(Teachers_Activity_Intent);
                getActivity().finish();
            }
        });
        services_show_all_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OtherServicesfull = new Intent(getActivity(), OtherServicesfullactivity.class);
                startActivity(OtherServicesfull);
                getActivity().finish();
            }
        });
    }

    private void Slider_Items_Click(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/152"));
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/132"));
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/75"));
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/70"));
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/66"));
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/49"));
                startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://netcollege-bartar.ir/postview/39"));
                startActivity(intent6);
                break;
        }
    }

    private void Get_Graphical_Controls(View rootview) {
        Departemants_recyclerView = rootview.findViewById(R.id.departemans_recylcer);
        Departemans_show_all_txt1 = (TextView) rootview.findViewById(R.id.Departemans_show_all_txt1);
        Teacher_show_all_txt1 = (TextView) rootview.findViewById(R.id.Teacher_show_all_txt1);
        services_show_all_txt1 = (TextView) rootview.findViewById(R.id.services_show_all_txt1);
        Progressbar_departemant = (ProgressBar) rootview.findViewById(R.id.Progressbar_departemant);
        Progressbar_teacher = (ProgressBar) rootview.findViewById(R.id.Progressbar_teacher);
        Progressbar_Other_Major = (ProgressBar) rootview.findViewById(R.id.Progressbar_Other_Major);
        Teachers_recyclerview = rootview.findViewById(R.id.teachers_recyclerview);
        Services_recyclerview = rootview.findViewById(R.id.services_recyclerview);
        Departemans_title1 = (TextView) rootview.findViewById(R.id.Departemans_title1);
        Departemans_show_all_txt1 = (TextView) rootview.findViewById(R.id.Departemans_show_all_txt1);
        Teachers_title1 = (TextView) rootview.findViewById(R.id.Teachers_title1);
        Teacher_show_all_txt1 = (TextView) rootview.findViewById(R.id.Teacher_show_all_txt1);
        services_title1 = (TextView) rootview.findViewById(R.id.services_title1);
        services_show_all_txt1 = (TextView) rootview.findViewById(R.id.services_show_all_txt1);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iransansdn.ttf");
        refreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.refresh);
        slider = rootview.findViewById(R.id.banner_slider11);
        txt1 = (TextView) rootview.findViewById(R.id.txt1);
        txt2 = (TextView) rootview.findViewById(R.id.txt2);
        txt3 = (TextView) rootview.findViewById(R.id.txt3);
    }

    private void Set_Recycler_Views_Settings() {
        //set Departemants Recyclerview Settings
        Departemants_recyclerView.setHasFixedSize(true);
        Departemants_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        Departemants_List = new ArrayList<>();
        //set Teachers Recyclerview Settings
        Teachers_recyclerview.setHasFixedSize(true);
        Teachers_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        Teachers_list = new ArrayList<>();
        //set Services Recyclerview Settings
        Services_recyclerview.setHasFixedSize(true);
        Services_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        Services_list = new ArrayList<>();
    }

    private void Set_Typeface_For_Text_Views() {
        Departemans_title1.setTypeface(typeface);
        Departemans_show_all_txt1.setTypeface(typeface);
        Teachers_title1.setTypeface(typeface);
        Teacher_show_all_txt1.setTypeface(typeface);
        services_title1.setTypeface(typeface);
        services_show_all_txt1.setTypeface(typeface);
        txt1.setTypeface(typeface);
        txt2.setTypeface(typeface);
        txt3.setTypeface(typeface);
    }


}







