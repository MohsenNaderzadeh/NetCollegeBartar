package bluedragon.app.netcollegebartar.Views.Fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bluedragon.app.netcollegebartar.Adapters.Teacher_Camp_Recycler_View_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Teacher_Profile_View_Pager_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataFakeGenerator.Data_Fake_Generator;
import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;
import bluedragon.app.netcollegebartar.Views.Activities.Teacher_Profile_Acitivity;

/**
 * Created by Blue_Dragon on 6/1/2019.
 */
public class Teacher_Camps_Fragment extends Fragment implements ApiService.OnTeacherClassesListRecieved {

    View view;
    RecyclerView recyclerView;
    ApiService apiService;
    private int B_Teacher_ID;
    TextView teachercampmessage;
    private FragmentActivity myContext;
    ProgressBar teacher_camps_progressbar;
    SqliteManager sqliteManager;
    SessionManager sessionManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.teachers_camps_layout,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.teacher_camps);
        teachercampmessage=(TextView)view.findViewById(R.id.teachercampmessage);
        apiService=new ApiService(myContext);
        sqliteManager=new SqliteManager(myContext);
        teacher_camps_progressbar=(ProgressBar)view.findViewById(R.id.teacher_camps_progressbar);
        sessionManager=new SessionManager(myContext);

        getBundle_Data();
        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/iransansdn.ttf");
        if(apiService.internetConnection())
        {
            apiService.GetTeacherClassesListApi(AppConfig.URL_TEACHER_CLASSESLIST,this,B_Teacher_ID);
        }
        else {
            if(sqliteManager.checkTeachersClassesTBAvailbilty())
            {
                if(!sqliteManager.getTeacherCampsListOffline(B_Teacher_ID).isEmpty())
                {
                    teacher_camps_progressbar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Teacher_Camp_Recycler_View_Adapter teacher_camp_recycler_view_adapter=new Teacher_Camp_Recycler_View_Adapter(getContext(), sqliteManager.getTeacherCampsListOffline(B_Teacher_ID),apiService,sessionManager);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(teacher_camp_recycler_view_adapter);
                }
                else
                {
                    teacher_camps_progressbar.setVisibility(View.GONE);
                    teachercampmessage.setVisibility(View.VISIBLE);
                }

            }
            else
            {
                teacher_camps_progressbar.setVisibility(View.GONE);
                teachercampmessage.setText("لطفا ارتباط خود با اینترنت را بررسی کنید");
                teachercampmessage.setTypeface(typeface);
                teachercampmessage.setVisibility(View.VISIBLE);


            }

        }

        teachercampmessage.setTypeface(typeface);



        return view;
    }

    public static Teacher_Camps_Fragment newInstance(Bundle data) {
        Teacher_Camps_Fragment fragment = new Teacher_Camps_Fragment();
        fragment.setArguments(data);
        return fragment;
    }
    private void getBundle_Data()
    {
        if(getArguments()!=null)
        {
            B_Teacher_ID=getArguments().getInt("Teacher_ID");

        }
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void OnClassessListRecieved(ArrayList<Camps> classesList) {
        if(!classesList.isEmpty())
        {
            sqliteManager.insertTeacherClassesList(classesList);
            teacher_camps_progressbar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Teacher_Camp_Recycler_View_Adapter teacher_camp_recycler_view_adapter=new Teacher_Camp_Recycler_View_Adapter(getContext(), classesList,apiService,sessionManager);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(teacher_camp_recycler_view_adapter);
        }
        else
        {
            teacher_camps_progressbar.setVisibility(View.GONE);
            teachercampmessage.setVisibility(View.VISIBLE);
        }

    }
}
