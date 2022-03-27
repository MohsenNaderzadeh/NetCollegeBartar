package bluedragon.app.netcollegebartar.Views.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import bluedragon.app.netcollegebartar.R;

/**
 * Created by Blue_Dragon on 4/6/2019.
 */
public class Fragment_Teacher_Profile extends Fragment {
    private static final String TAG = "Fragment_Teacher_Profile";
    View rootview;
    private int B_Teacher_ID;
    private String B_Teachers_Skill;
    private String B_Teachers_Resume;
    private int B_Teachers_Departemant_ID;
    private String B_Teacher_Email_Address;
    private CardView Skill_Card_View,Resume_Card_View,Email_Card_View;
    private TextView Teacher_Profile_Skill_Title_Text_View,Teacher_Skill_Textview,Teacher_Profile_Resume_Title_Text_View,Teacher_Resume_Textview,Teacher_Email_Address_Title,Teacher_Email_Address;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_teacher_profile,container,false);
        SetViews();
        SetTypeFace();
        SetFragmentContent();


        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getBundle_Data();
        }
    }


    private void SetViews()
    {
        Skill_Card_View=(CardView)rootview.findViewById(R.id.Skill_Card_View);
        Resume_Card_View=(CardView)rootview.findViewById(R.id.Resume_Card_View);
        Teacher_Profile_Skill_Title_Text_View=(TextView)rootview.findViewById(R.id.Teacher_Profile_Skill_Title_Text_View);
        Teacher_Skill_Textview=(TextView)rootview.findViewById(R.id.Teacher_Skill_Textview);
        Teacher_Profile_Resume_Title_Text_View=(TextView)rootview.findViewById(R.id.Teacher_Profile_Resume_Title_Text_View);
        Teacher_Resume_Textview=(TextView)rootview.findViewById(R.id.Teacher_Resume_Textview);
        Teacher_Email_Address_Title=(TextView)rootview.findViewById(R.id.Teacher_Email_Address_Title);
        Teacher_Email_Address=(TextView)rootview.findViewById(R.id.Teacher_Email_Address);
        Email_Card_View=(CardView)rootview.findViewById(R.id.Email_Card_View);
    }
    private void SetTypeFace()
    {
        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/iransansdn.ttf");
        Teacher_Profile_Skill_Title_Text_View.setTypeface(typeface);
        Teacher_Skill_Textview.setTypeface(typeface);
        Teacher_Profile_Resume_Title_Text_View.setTypeface(typeface);
        Teacher_Resume_Textview.setTypeface(typeface);
        Teacher_Email_Address_Title.setTypeface(typeface);
    }
    private void SetFragmentContent()
    {
        Teacher_Skill_Textview.setText(B_Teachers_Skill);
        if(B_Teachers_Resume.length()!=0)
        {
            Resume_Card_View.setVisibility(View.VISIBLE);
            Teacher_Resume_Textview.setText(B_Teachers_Resume);
        }
        if(B_Teacher_Email_Address.length()!=0)
        {
            Email_Card_View.setVisibility(View.VISIBLE);
            Teacher_Email_Address.setText(B_Teacher_Email_Address);
        }

    }
    private void getBundle_Data()
    {
        if(getArguments()!=null)
        {
            B_Teacher_ID=getArguments().getInt("Teacher_ID");
            B_Teachers_Skill=getArguments().getString("Teachers_Skill");
            B_Teachers_Resume=getArguments().getString("Teachers_Resume");
            B_Teachers_Departemant_ID=getArguments().getInt("Teachers_Departemant_ID");
            B_Teacher_Email_Address=getArguments().getString("Teacher_Email");
        }
    }

    public static Fragment_Teacher_Profile newInstance(Bundle data) {

        Fragment_Teacher_Profile fragment = new Fragment_Teacher_Profile();
        fragment.setArguments(data);
        return fragment;
    }


}
