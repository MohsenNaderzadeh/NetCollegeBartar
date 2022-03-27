package bluedragon.app.netcollegebartar.DataModels;

/**
 * Created by Blue_Dragon on 12/1/2018.
 */
public class Teachers {
    private int Teacher_ID;
    private String Teachers_full_name;
    private String Teachers_Departemants_name;
    private String Teachers_photo;
    private String Teachers_Skill;
    private String Teachers_Resume;
    private String Teacher_Email;
    private int Teachers_Departemant_ID;



    public int getId() {
        return Teacher_ID;
    }

    public String getTeachers_full_name() {
        return Teachers_full_name;
    }

    public String getTeachers_Departemants_name() {
        return Teachers_Departemants_name;
    }

    public String getTeachers_photo() {
        return Teachers_photo;
    }



    public void setTeachers_full_name(String teachers_full_name) {
        Teachers_full_name = teachers_full_name;
    }



    public void setTeachers_Departemants_name(String teachers_Departemants_name) {
        Teachers_Departemants_name = teachers_Departemants_name;
    }

    public void setTeachers_photo(String teachers_photo) {
        Teachers_photo = teachers_photo;
    }

    public void setTeacher_ID(int Teacher_ID) {
        this.Teacher_ID = Teacher_ID;
    }

    public String getTeachers_Skill() {
        return Teachers_Skill;
    }

    public void setTeachers_Skill(String teachers_Skill) {
        Teachers_Skill = teachers_Skill;
    }

    public String getTeachers_Resume() {
        return Teachers_Resume;
    }

    public void setTeachers_Resume(String teachers_Resume) {
        Teachers_Resume = teachers_Resume;
    }

    public int getTeachers_Departemant_ID() {
        return Teachers_Departemant_ID;
    }

    public void setTeachers_Departemant_ID(int teachers_Departemant_ID) {
        Teachers_Departemant_ID = teachers_Departemant_ID;
    }

    public String getTeacher_Email() {
        return Teacher_Email;
    }

    public void setTeacher_Email(String teacher_Email) {
        Teacher_Email = teacher_Email;
    }
}
