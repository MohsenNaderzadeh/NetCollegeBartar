package bluedragon.app.netcollegebartar.Adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bluedragon.app.netcollegebartar.Views.Fragments.Fragment_Teacher_Profile;
import bluedragon.app.netcollegebartar.Views.Fragments.LoginFragment;
import bluedragon.app.netcollegebartar.Views.Fragments.Teacher_Camps_Fragment;
import bluedragon.app.netcollegebartar.Views.Fragments.User_Comments_Fragment;

/**
 * Created by Blue_Dragon on 5/9/2019.
 */
public class Teacher_Profile_View_Pager_Adapter extends FragmentPagerAdapter {

    private Fragment[] Fragments_List;
    public Teacher_Profile_View_Pager_Adapter(FragmentManager fm, Bundle TeacherBundledata) {
        super(fm);

        Fragments_List = new Fragment[] {
                User_Comments_Fragment.newInstance(TeacherBundledata), //0
                Fragment_Teacher_Profile.newInstance(TeacherBundledata), //1
                 Teacher_Camps_Fragment.newInstance(TeacherBundledata) //2
        };
    }

    @Override
    public Fragment getItem(int position) {
        return Fragments_List[position];
    }



    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "نظرات کاربران";
            case 1:
                return "مشخصات استاد";
            case 2:
                return "دوره ها";
            default:
                return "";
        }
    }
}
