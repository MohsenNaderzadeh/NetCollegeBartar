package bluedragon.app.netcollegebartar.Views.Fragments;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bluedragon.app.netcollegebartar.Adapters.User_Comments_Fragment_Recycler_View_Adapter;
import bluedragon.app.netcollegebartar.ApiService.ApiService;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.User_Comments_Recycler_View_Data_Model;
import bluedragon.app.netcollegebartar.R;
import bluedragon.app.netcollegebartar.SessionManager.SessionManager;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;
import bluedragon.app.netcollegebartar.Views.Activities.SubmitNewCommentActivity;

/**
 * Created by Blue_Dragon on 4/9/2019.
 */
public class User_Comments_Fragment extends Fragment implements ApiService.OnTeacherCommentListRecieved {
    View RootView;
    RecyclerView User_Comments_Recycler_View;
    TextView teacher_comment_message;
    ApiService apiService;
    Button Submit_new_Comment_for_teacher;
    private FragmentActivity myContext;
    SessionManager sessionManager;
     int TeacherID;
    ProgressBar user_comments_progressbar;
    String TeacherName;
    SqliteManager sqliteManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RootView=inflater.inflate(R.layout.user_comments_fragment,container,false);
        User_Comments_Recycler_View=(RecyclerView)RootView.findViewById(R.id.User_Comments_Recycler_View);
        teacher_comment_message=(TextView)RootView.findViewById(R.id.teacher_comment_message);
        user_comments_progressbar=(ProgressBar) RootView.findViewById(R.id.user_comments_progressbar);
        Submit_new_Comment_for_teacher=(Button)RootView.findViewById(R.id.Submit_new_Comment_for_teacher);
        sessionManager=new SessionManager(myContext);
        getBundle_Data();
        apiService=new ApiService(myContext);
        sqliteManager=new SqliteManager(myContext);
        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/iransansdn.ttf");
        Submit_new_Comment_for_teacher.setTypeface(typeface);
        teacher_comment_message.setTypeface(typeface);
        if(apiService.internetConnection())
        {
            apiService.GetTeacherCommentListApi(AppConfig.URL_TEACHER_COMMENTLIST,this,TeacherID);
        }else
        {
            if(sqliteManager.checkTeacherCommentsTBAvailbilty())
            {
                if(!sqliteManager.getTeacherCommentListOffline(TeacherID).isEmpty())
                {
                    user_comments_progressbar.setVisibility(View.GONE);
                    User_Comments_Recycler_View.setVisibility(View.VISIBLE);
                    User_Comments_Fragment_Recycler_View_Adapter user_comments_fragment_recycler_view_adapter=new User_Comments_Fragment_Recycler_View_Adapter(getActivity(),sqliteManager.getTeacherCommentListOffline(TeacherID));
                    User_Comments_Recycler_View.setHasFixedSize(true);
                    User_Comments_Recycler_View.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    User_Comments_Recycler_View.setAdapter(user_comments_fragment_recycler_view_adapter);
                }
                else
                {
                    user_comments_progressbar.setVisibility(View.GONE);
                    teacher_comment_message.setVisibility(View.VISIBLE);
                }
            }
        }

        Submit_new_Comment_for_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apiService.internetConnection())
                {
                    if(sessionManager.isLoggedIn())
                    {
                        Intent intent=new Intent(myContext, SubmitNewCommentActivity.class);
                        intent.putExtra("TeacherID",TeacherID);
                        intent.putExtra("Teacher_Name",TeacherName);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(myContext,"برای ثبت نظر باید وارد حساب کاربری خود شوید و یا ثبت نام کنید ",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(myContext,"لطفا اتصال خود به اینترنت را بررسی بفرمایید",Toast.LENGTH_LONG).show();

                }

            }
        });
        return RootView;
    }

    public static User_Comments_Fragment newInstance(Bundle data) {
        User_Comments_Fragment fragment = new User_Comments_Fragment();
        fragment.setArguments(data);
        return fragment;
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
    @Override
    public void OnCommentListRecieved(ArrayList<User_Comments_Recycler_View_Data_Model> CommentList) {
        if(!CommentList.isEmpty())
        {
            sqliteManager.insertTeacherCommentList(CommentList);
            user_comments_progressbar.setVisibility(View.GONE);
            User_Comments_Recycler_View.setVisibility(View.VISIBLE);
            User_Comments_Fragment_Recycler_View_Adapter user_comments_fragment_recycler_view_adapter=new User_Comments_Fragment_Recycler_View_Adapter(getActivity(),CommentList);
            User_Comments_Recycler_View.setHasFixedSize(true);
            User_Comments_Recycler_View.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            User_Comments_Recycler_View.setAdapter(user_comments_fragment_recycler_view_adapter);

        }else
        {
            user_comments_progressbar.setVisibility(View.GONE);
            teacher_comment_message.setVisibility(View.VISIBLE);
        }

    }
    private void getBundle_Data()
    {
        if(getArguments()!=null)
        {
            TeacherID=getArguments().getInt("Teacher_ID");
            TeacherName=getArguments().getString("Teacher_Name");
        }
    }
}
