package bluedragon.app.netcollegebartar.ApiService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bluedragon.app.netcollegebartar.Adapters.Actvity_details_recyclerview_adapter;
import bluedragon.app.netcollegebartar.Adapters.Departemants_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Departemants_full_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Services_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Services_full_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Teachers_Adapter;
import bluedragon.app.netcollegebartar.Adapters.Teachers_full_recyclerview_Adapter;
import bluedragon.app.netcollegebartar.AppConfig.AppConfig;
import bluedragon.app.netcollegebartar.DataModels.Camps;
import bluedragon.app.netcollegebartar.DataModels.Classes;
import bluedragon.app.netcollegebartar.DataModels.Departemants;
import bluedragon.app.netcollegebartar.DataModels.Departemants_full_DataModel;
import bluedragon.app.netcollegebartar.DataModels.ExClassListModel;
import bluedragon.app.netcollegebartar.DataModels.ImageGallery;
import bluedragon.app.netcollegebartar.DataModels.LastNews;
import bluedragon.app.netcollegebartar.DataModels.Library;
import bluedragon.app.netcollegebartar.DataModels.OtherMajorRegisteredDataModel;
import bluedragon.app.netcollegebartar.DataModels.RegisteredExClassesDataModel;
import bluedragon.app.netcollegebartar.DataModels.Services;
import bluedragon.app.netcollegebartar.DataModels.Services_full_DataModel;
import bluedragon.app.netcollegebartar.DataModels.Teachers;
import bluedragon.app.netcollegebartar.DataModels.Teachers_full_DataModel;
import bluedragon.app.netcollegebartar.DataModels.User_Comments_Recycler_View_Data_Model;
import bluedragon.app.netcollegebartar.SharedPrefDataModels.UserInformationDataModel;
import bluedragon.app.netcollegebartar.SqliteManager.SqliteManager;

/**
 * Created by Blue_Dragon on 2/11/2019.
 */
public class ApiService {

    private int Teacher_requestCount_First_Load = 1;
    private int Teacher_requestCount=2;
    private static final String TAG = "ApiService";
    private Context context;
    private boolean Login_Stat_Error;
    boolean has_error;
    String error_text;

    public ApiService(Context context) {
        this.context = context;
    }

    public void loadfullDepartemants(String URL_Full_Departemants, final ArrayList<Departemants_full_DataModel> DepartemantsList, final RecyclerView recyclerview, final ProgressBar Departemants_Main_Progressbar, final SqliteManager sqliteManager) {

        Log.i(TAG, "loadfullDepartemants:");
        JsonArrayRequest LoadFullDepartemants_Json_Array_Request = new JsonArrayRequest(Request.Method.GET, URL_Full_Departemants, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Parse_Full_Departemants_Json(response, DepartemantsList);
                Departemants_full_Adapter adapter = new Departemants_full_Adapter(context, DepartemantsList);
                recyclerview.setAdapter(adapter);
                sqliteManager.insertfullDepartemants(DepartemantsList);
                Departemants_Main_Progressbar.setVisibility(View.INVISIBLE);
                recyclerview.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        LoadFullDepartemants_Json_Array_Request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(LoadFullDepartemants_Json_Array_Request);
    }


    public void getfullclaseslist(final int Departemants_id, final ArrayList<Classes> ClassesList, final RecyclerView recyclerview,final ProgressBar Full_Classes_Progressbar,final SqliteManager sqliteManager) {

        StringRequest getfullclaseslist_strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_Classes, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG, " getfullclaseslist response is " + response);

                try {
                    JSONArray array = new JSONArray(response);
                    Log.i(TAG, array.toString());
                    Parse_Full_Classes_Json(array, ClassesList);
                    sqliteManager.insertClassesList(ClassesList);
                    Actvity_details_recyclerview_adapter recyclerview_details = new Actvity_details_recyclerview_adapter(context, ClassesList);
                    recyclerview.setAdapter(recyclerview_details);
                    Full_Classes_Progressbar.setVisibility(View.GONE);
                    recyclerview.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Departemant_ID", String.valueOf(Departemants_id));
                return params;
            }

        };
        getfullclaseslist_strReq.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(getfullclaseslist_strReq);

    }


    public void LoadfullServices(String URL_Services, final ArrayList<Services_full_DataModel> Services_list, final RecyclerView recyclerView,final ProgressBar Other_Services_Main_ProgressBar,final SqliteManager sqliteManager) {
        JsonArrayRequest LoadFullServices_JsonArrayRequest= new JsonArrayRequest(Request.Method.GET, URL_Services, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, " LoadfullServices onResponse: ");
                Parse_Full_Services_Json(response,Services_list);
                Services_full_Adapter services_full_adapter = new Services_full_Adapter(context, Services_list);
                recyclerView.setAdapter(services_full_adapter);
                sqliteManager.insertFullOtherMajor(Services_list);
                Other_Services_Main_ProgressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        LoadFullServices_JsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(LoadFullServices_JsonArrayRequest);
    }


    public void LoadfullTeachers(final int requestCount, final ArrayList<Teachers_full_DataModel> Teachers_List, final Teachers_full_recyclerview_Adapter recyclerview_details,final ProgressBar LoadMore_Progressbar,final SqliteManager sqliteManager) {

        // LoadMore_Progressbar.setVisibility(View.VISIBLE);
        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOAD_MORE_TEACHERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                Parse_Full_Teachers(response, Teachers_List);
                sqliteManager.insertfullTeachers(Teachers_List);
                recyclerview_details.notifyDataSetChanged();
                LoadMore_Progressbar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("page", String.valueOf(requestCount));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void LoadfullTeachersFirstLoad(final int requestCount, final ArrayList<Teachers_full_DataModel> Teachers_List, final Teachers_full_recyclerview_Adapter recyclerview_details,final ProgressBar Progressbar_First_Load,final RecyclerView recyclerView,final SqliteManager sqliteManager) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOAD_MORE_TEACHERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                Parse_Full_Teachers(response, Teachers_List);
                sqliteManager.insertfullTeachers(Teachers_List);
                recyclerview_details.notifyDataSetChanged();
                Progressbar_First_Load.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("page", String.valueOf(requestCount));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }

    public void getFullTeachersSecondPartData(ArrayList<Teachers_full_DataModel> Teachers_list, Teachers_full_recyclerview_Adapter recyclerview_details,final  ProgressBar Teacher_LoadMore_Progressbar,final SqliteManager sqliteManager) {
        //Adding the method to the queue by calling the method getDataFromServer
        LoadfullTeachers(Teacher_requestCount, Teachers_list, recyclerview_details,Teacher_LoadMore_Progressbar,sqliteManager);
        //Incrementing the request counter
        Teacher_requestCount++;
    }
    public void getFullTeacher_DataFirstLoad(ArrayList<Teachers_full_DataModel> Teachers_list, Teachers_full_recyclerview_Adapter recyclerview_details,final ProgressBar Teacher_FirstLoad_Progressbar,final RecyclerView recyclerView,final SqliteManager sqliteManager) {
        //Adding the method to the queue by calling the method getDataFromServer
        LoadfullTeachersFirstLoad(Teacher_requestCount_First_Load, Teachers_list, recyclerview_details,Teacher_FirstLoad_Progressbar,recyclerView,sqliteManager);
        //Incrementing the request counter
    }



    public void loadDepartemants(String URL_DEPARTEMANTS, final ArrayList<Departemants> Departemants_List, final RecyclerView Departemants_recyclerView, final ProgressBar departemant_progressbar, final TextView txt1,final SqliteManager sqliteManager) {

        JsonArrayRequest LoadDepartemants_JsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL_DEPARTEMANTS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Parse_Departemants_Json(response,Departemants_List);
                Departemants_Adapter adapter = new Departemants_Adapter(context, Departemants_List);
                Departemants_recyclerView.setAdapter(adapter);
                sqliteManager.insertDepartemants(Departemants_List);
                departemant_progressbar.setVisibility(View.INVISIBLE);
                Departemants_recyclerView.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                departemant_progressbar.setVisibility(View.INVISIBLE);
                txt1.setVisibility(View.VISIBLE);
            }
        });
        LoadDepartemants_JsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(LoadDepartemants_JsonArrayRequest);
    }


    public void loadTeachers(String URL_Teachers, final ArrayList<Teachers> Teachers_list, final RecyclerView recyclerView, final ProgressBar Teacher_Progressbar, final TextView txt2,final SqliteManager sqliteManager) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_Teachers, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: " + response.toString());
                Parse_Teachers_Json(response, Teachers_list);
                sqliteManager.insertTeachers(Teachers_list);
                Teachers_Adapter teachers_adapter = new Teachers_Adapter(context, Teachers_list);
                recyclerView.setAdapter(teachers_adapter);
                Teacher_Progressbar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Teacher_Progressbar.setVisibility(View.INVISIBLE);
                txt2.setVisibility(View.VISIBLE);
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonArrayRequest);
    }



    public void LoadServices(String URL_Services, final ArrayList<Services> Services_list, final RecyclerView Services_recyclerview, final ProgressBar Services_Progressbar, final TextView txt3,final SqliteManager sqliteManager) {
        JsonArrayRequest LoadServices_JsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL_Services, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Parse_Services_Json(response,Services_list);
                Services_Adapter adapters = new Services_Adapter(context, Services_list);
                Services_recyclerview.setAdapter(adapters);
                sqliteManager.insertOtherMajor(Services_list);
                Services_Progressbar.setVisibility(View.INVISIBLE);
                Services_recyclerview.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Services_Progressbar.setVisibility(View.INVISIBLE);
                txt3.setVisibility(View.VISIBLE);
            }
        });
        LoadServices_JsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(LoadServices_JsonArrayRequest);
    }



    public void LoadImageGalleryImAGE(String url_Images, final OnImageList onImageList) {
        JsonArrayRequest LoadServices_JsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url_Images, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                onImageList.OnImageListRecieved(ParseImageGalleryImage(response));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        LoadServices_JsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(LoadServices_JsonArrayRequest);
    }

    public void LoginAPI(final String URL_LOGIN, final OnLoginData loginData, final String UserName, final String Password, final ProgressDialog progressDialog) {
        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Login Response: " + response.toString());
                try {

                    char[] responsearray=response.toCharArray();
                    if(responsearray[0]=='[')
                    {
                        JSONArray ResponseJsonArray=new JSONArray(response);
                        JSONObject LoginJsonObject=ResponseJsonArray.getJSONObject(0);
                        loginData.onRecieved(parseLoginData(LoginJsonObject));
                    }
                    else if (responsearray[0] =='{') {

                        try {
                            JSONObject LoginJsonObject=new JSONObject(response);
                            has_error=LoginJsonObject.getBoolean("Error");
                            if(has_error)
                            {
                                Toast.makeText(context,"نام کاربری و یا رمز عبور غیرمتعبر است",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",UserName);
                params.put("pass",Password);
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void RegisterApi(final String URL_REGISTER,final OnRegisterInformationRecieved RegisterList, final String PhoneNumber, final String Password) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG, "Register Response: " + response);

                char[] responsearray=response.toCharArray();

                if(responsearray[0]=='[')
                {
                    try {
                        JSONArray ResponseJsonArray=new JSONArray(response);
                        JSONObject RegisterJsonObject=ResponseJsonArray.getJSONObject(0);
                        has_error=RegisterJsonObject.getBoolean("has_error");
                        if(!has_error)
                        {
                            RegisterList.OnRegisterInformationRecieved(parseUserRegisterInformationDataModel(RegisterJsonObject));
                        }else
                        {
                            Toast.makeText(context,"خطا در ارتباط با سرور",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else if (responsearray[0] =='{') {

                    try {
                        JSONObject RegisterJsonObject=new JSONObject(response);
                        has_error=RegisterJsonObject.getBoolean("has_error");
                        if(has_error)
                        {
                            Toast.makeText(context,"شماره تلفن وارده در سیستم وجود دارد لطفا از شماره تلفن دیگری استفاده نمایید",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }






            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to Register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Phone_Number",PhoneNumber);
                params.put("User_Password",Password);
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void GetTeacherClassesListApi(final String URL_TEACHER_CLASSESLIST,final OnTeacherClassesListRecieved onTeacherClassesListRecieved , final int TeacherID) {

        final ArrayList<Camps> campslist=new ArrayList<>();
        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_TEACHER_CLASSESLIST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG, "TeacherClassesList Response: " + response);
                try {
                    Camps camps=new Camps();
                    JSONArray TeacherCampsList=new JSONArray(response);
                    for(int i=0;i<TeacherCampsList.length();i++)
                    {
                        JSONObject TeacherJsonObject=TeacherCampsList.getJSONObject(i);
                        camps.setCamp_Id(Integer.parseInt(TeacherJsonObject.getString("Execute_Class_ID")));
                        camps.setCamp_ClassID(Integer.parseInt(TeacherJsonObject.getString("Classes_ID")));
                        camps.setCamp_Teacher_ID(Integer.parseInt(TeacherJsonObject.getString("Class_Teacher_ID")));
                        camps.setCamp_Name(TeacherJsonObject.getString("Class_Name"));
                        camps.setCampCapacity(Integer.parseInt(TeacherJsonObject.getString("Execute_Class_Capacity")));
                        camps.setCamp_Duration_Value(Byte.valueOf(TeacherJsonObject.getString("Execute_Class_DaysCount")));
                        camps.setCamp_fee(Integer.parseInt(TeacherJsonObject.getString("Execute_Class_fee")));
                        camps.setCamp_Logo_Url(TeacherJsonObject.getString("Execute_Class_Photo"));
                        campslist.add(camps);
                    }
                    onTeacherClassesListRecieved.OnClassessListRecieved(campslist);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to Register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("TeacherID",String.valueOf(TeacherID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void GetTeacherCommentListApi(final String URL_TEACHER_COMMENT_LIST,final OnTeacherCommentListRecieved onTeacherCommentListRecieved , final int TeacherID) {

        final ArrayList<User_Comments_Recycler_View_Data_Model> commentlist=new ArrayList<>();
        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_TEACHER_COMMENT_LIST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG, "TeacherCommentsList Response: " + response);
                try {
                    User_Comments_Recycler_View_Data_Model comments=new User_Comments_Recycler_View_Data_Model();
                    JSONArray TeacherCampsList=new JSONArray(response);
                    for(int i=0;i<TeacherCampsList.length();i++)
                    {
                        JSONObject TeacherCommentsListJsonObject=TeacherCampsList.getJSONObject(i);
                        comments.setUser_Comment_ID(Integer.parseInt(TeacherCommentsListJsonObject.getString("CommentID")));
                        comments.setUser_Rating_Score(Float.parseFloat(TeacherCommentsListJsonObject.getString("CommentScore")));
                        comments.setUser_Profile_URL(TeacherCommentsListJsonObject.getString("User_Profile"));
                        comments.setTeacherID(Integer.parseInt(TeacherCommentsListJsonObject.getString("CommentTeacherID")));
                        comments.setUser_Commenter_Name(TeacherCommentsListJsonObject.getString("User_First_Name")+" "+TeacherCommentsListJsonObject.getString("User_Last_Name"));
                        comments.setUser_Comment_Text(TeacherCommentsListJsonObject.getString("CommentText"));
                        comments.setCommentSenderID(Integer.parseInt(TeacherCommentsListJsonObject.getString("CommentSenderID")));
                        comments.setCommentSendDate(TeacherCommentsListJsonObject.getString("CommentSendDate"));
                        comments.setCommentSendTime(TeacherCommentsListJsonObject.getString("CommentSendTime"));
                        commentlist.add(comments);
                    }
                    onTeacherCommentListRecieved.OnCommentListRecieved(commentlist);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to Register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Teacher_ID",String.valueOf(TeacherID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void UpdateUserProfileInfo(final String URL_UPDATE_PROFILE_INFO,final OnRegisterInformationRecieved onRegisterInformationRecieved , final int UserID,final String UserFirstName,final String UserLastName,final String User_Profile_URL,final String UserEmailAddress,final String UserPhoneNumber,final String UserUserName,final String UserPassword) {

        final ArrayList<Camps> campslist=new ArrayList<>();
        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_UPDATE_PROFILE_INFO, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                char[] responsearray=response.toCharArray();

                if(responsearray[0]=='[')
                {
                    JSONArray ResponseJsonArray= null;
                    try {
                        ResponseJsonArray = new JSONArray(response);
                        JSONObject ProfileUpdateJsonObject=ResponseJsonArray.getJSONObject(0);
                        onRegisterInformationRecieved.OnRegisterInformationRecieved(parseUserRegisterInformationDataModel(ProfileUpdateJsonObject));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else
                {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String ErrorText=jsonObject.getString("error_text");
                        if(ErrorText.equals("UserName is repative"))
                        {
                            Toast.makeText(context,"نام کاربری وارده تکراری می باشد لطفا از نام کاربری دیگری استفاده بفرمایید",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to Register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("UserID",String.valueOf(UserID));
                params.put("UserFirstName",UserFirstName);
                params.put("UserLastName",UserLastName);
                params.put("UserProfileURL",User_Profile_URL);
                params.put("UserEmailAddress",UserEmailAddress);
                params.put("UserPhoneNumber",UserPhoneNumber);
                params.put("UserUserName",UserUserName);
                params.put("UserPassword",UserPassword);
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void UserExClassRegisterApi(final String URL_REGISTER_EX_CLASS,final OnExClassRegisterResult OnExClassRegisterResultRecieved , final int UserID,final int ClassID) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_REGISTER_EX_CLASS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                JSONObject ResponseJsonObject= null;
                try {
                    ResponseJsonObject = new JSONObject(response);
                    OnExClassRegisterResultRecieved.OnExClassRegisterResultRecieved(ResponseJsonObject.getString("Error_Text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("User_ID",String.valueOf(UserID));
                params.put("Class_ID",String.valueOf(ClassID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void UserOtherMajorRegister(final String URL_OTHER_MAJOR_REGISTER,final OnOtherMajorRegisterResultRecieved OtherMajorRegisterResultRecieved , final int UserID,final int MajorID) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_OTHER_MAJOR_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                JSONObject ResponseJsonObject= null;
                try {
                    ResponseJsonObject = new JSONObject(response);
                    OtherMajorRegisterResultRecieved.OnOtherMajorResultRecieved(ResponseJsonObject.getBoolean("Error"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("User_ID",String.valueOf(UserID));
                params.put("MajorID",String.valueOf(MajorID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void GetExClassListByClassID(final String URL_GET_EX_CLASS_LIST_BY_CLASS_ID,final OnExClassListByClassIDRecieved OnExClassListByClassIDRecieved , final int ClassID) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_GET_EX_CLASS_LIST_BY_CLASS_ID, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                char[] responsearray=response.toCharArray();
                Log.i(TAG, "onResponse: "+response.toString());
                if(responsearray[0]=='[')
                {
                    try {
                        JSONArray ResponseJsonArray=new JSONArray(response);
                        for(int i=0;i<ResponseJsonArray.length();i++)
                        {
                            JSONObject ResponseJsonObject=ResponseJsonArray.getJSONObject(i);
                            OnExClassListByClassIDRecieved.ExClassListByClassIDRecieved(ParseExClassListModel(ResponseJsonObject));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if(responsearray[0] =='{') {
                    try {
                        JSONObject ResponseJsonObject=new JSONObject(response);
                        OnExClassListByClassIDRecieved.ExClassListByClassIDRecievedhasError();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("ClassID",String.valueOf(ClassID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void UserRegisteredExClassApi(final String URL_REGISTER_EX_CLASS,final OnRegisteredExClassResult OnRegisteredExClassResultRecieved , final int UserID) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_REGISTER_EX_CLASS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                char[] responsearray=response.toCharArray();
                if(responsearray[0]=='[')
                {
                    OnRegisteredExClassResultRecieved.OnRegisteredExClassResultRecieved(ParseRegisteredExClassData(response));
                }else if(responsearray[0]=='{')
                {
                    OnRegisteredExClassResultRecieved.OnRegisteredExClassResultError();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("User_ID",String.valueOf(UserID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void UserRegisteredOtherClassApi(final String URL_REGISTER_OTHER_CLASS,final OnRegisteredOTherClassResult OnRegisteredOtherClassResultRecieved , final int UserID) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_REGISTER_OTHER_CLASS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                char[] responsearray=response.toCharArray();
                if(responsearray[0]=='[')
                {
                    OnRegisteredOtherClassResultRecieved.OnRegisteredOTherClassResultRecieved(ParseRegisteredOtherClassData(response));
                }else if(responsearray[0]=='{')
                {
                    OnRegisteredOtherClassResultRecieved.OnRegisteredOTherClassResultError();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("User_ID",String.valueOf(UserID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void SubmitNewCommentForTeacher(final String URL_SUBMIT_NEW_COMMENT_FOR_TEACHER,final OnSubmitNewCommentResult onSubmitNewCommentResult , final int UserID,final String CommentText,final int CommentTeacherID,final float CommentScore) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_SUBMIT_NEW_COMMENT_FOR_TEACHER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    onSubmitNewCommentResult.OnSubmitNewCommentResultRecieved(jsonObject.getBoolean("Error"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("CommentText",CommentText);
                params.put("CommentSenderID",String.valueOf(UserID));
                params.put("CommentTeacherID",String.valueOf(CommentTeacherID));
                params.put("CommentScore",String.valueOf(CommentScore));

                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }
    public void GetLastNewsList(final String URL_LastNews,final OnLastNewsList OnLastNewsList)
    {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL_LastNews, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: ");
                OnLastNewsList.OnLastNewsList(ParseLastNewsList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonArrayRequest);
    }
    public void GetBookList(final String URL_GET_BOOK,final OnBookList onBookList , final int bookCategoryID) {

        final StringRequest LoadFullTeachers_StringRequest = new StringRequest(Request.Method.POST,
                URL_GET_BOOK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                onBookList.OnBookListRecieved(ParseLibrayInfo(response));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("bookCategoryID",String.valueOf(bookCategoryID));
                return params;
            }

        };
        LoadFullTeachers_StringRequest.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(LoadFullTeachers_StringRequest);


    }


























    private ArrayList<ImageGallery>ParseImageGalleryImage(JSONArray response)
    {
        ArrayList<ImageGallery>ImagesList=new ArrayList<>();
        for(int i=0;i<response.length();i++)
        {
            ImageGallery imageGallery=new ImageGallery();
            try {
                JSONObject jsonObject=response.getJSONObject(i);
                imageGallery.setImageID(Integer.parseInt(jsonObject.getString("ImageID")));
                imageGallery.setImageLogoUrl(jsonObject.getString("Imageurl"));
                imageGallery.setImageCaption(jsonObject.getString("ImageCaption"));
                ImagesList.add(imageGallery);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ImagesList;
    }
    private ArrayList<Library> ParseLibrayInfo(String response)
    {
        JSONArray jsonArray= null;
        ArrayList<Library>BookList=new ArrayList<>();
        try {
            jsonArray = new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++)
            {
                Library library=new Library();
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                library.setBook_id(Integer.parseInt(jsonObject.getString("BookID")));
                library.setBook_category(Integer.parseInt(jsonObject.getString("BookCategory")));
                library.setBookName(jsonObject.getString("BookName"));
                library.setBookImage(jsonObject.getString("BookLogo"));
                library.setBooKDescription(jsonObject.getString("BookDescription"));
                library.setBookDownloadLink(jsonObject.getString("BookDownloadLink"));
                BookList.add(library);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return BookList;
    }

    private ArrayList<OtherMajorRegisteredDataModel> ParseRegisteredOtherClassData(String Response)
    {
        ArrayList<OtherMajorRegisteredDataModel>registeredOtherClassesList=new ArrayList<>();
        try {
            JSONArray ResponseJsonArray = new JSONArray(Response);
            for (int i = 0; i < ResponseJsonArray.length(); i++) {
                OtherMajorRegisteredDataModel OtherregisteredDataModel=new OtherMajorRegisteredDataModel();
                JSONObject responseJsonObject=ResponseJsonArray.getJSONObject(i);
                OtherregisteredDataModel.setOtherMajorRegisterID(Integer.parseInt(responseJsonObject.getString("Other_Major_Register_ID")));
                OtherregisteredDataModel.setRegistered_MajorID(Integer.parseInt(responseJsonObject.getString("Registered_MajorID")));
                OtherregisteredDataModel.setMajor_Name(responseJsonObject.getString("Major_Name"));
                OtherregisteredDataModel.setMajor_logo(responseJsonObject.getString("Major_logo"));
                OtherregisteredDataModel.setRegisterDate(responseJsonObject.getString("RegisterDate"));
                registeredOtherClassesList.add(OtherregisteredDataModel);

            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return registeredOtherClassesList;
    }

    private ArrayList<RegisteredExClassesDataModel> ParseRegisteredExClassData(String Response)
    {
        ArrayList<RegisteredExClassesDataModel>registeredExClassesList=new ArrayList<>();
        try {
            JSONArray ResponseJsonArray=new JSONArray(Response);
            for(int i=0;i<ResponseJsonArray.length();i++)
            {
                RegisteredExClassesDataModel registeredExClassesDataModel=new RegisteredExClassesDataModel();
                JSONObject ResponseJsonObject=ResponseJsonArray.getJSONObject(i);
                registeredExClassesDataModel.setRegisterId(Integer.parseInt(ResponseJsonObject.getString("Register_ID")));
                registeredExClassesDataModel.setRegisteredExClassID(Integer.parseInt(ResponseJsonObject.getString("Registered_ExClass_ID")));
                registeredExClassesDataModel.setRegisteredExClassLogo(ResponseJsonObject.getString("Execute_Class_Photo"));
                registeredExClassesDataModel.setRegisteredExClassName(ResponseJsonObject.getString("Class_Name"));
                registeredExClassesDataModel.setRegisteredExClassTeacherName(ResponseJsonObject.getString("Teachers_Full_Name"));
                registeredExClassesDataModel.setRegisteredExClassRegDate(ResponseJsonObject.getString("RegDate"));
                registeredExClassesList.add(registeredExClassesDataModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return registeredExClassesList;
    }
    private ArrayList<ExClassListModel> ParseExClassListModel(JSONObject Response)
    {
        ArrayList<ExClassListModel> ExClassList=new ArrayList<>();
        ExClassListModel exClassListModel=new ExClassListModel();
        try {
            exClassListModel.setExClassId(Response.getInt("Execute_Class_ID"));
            exClassListModel.setExLogoURL(Response.getString("Execute_Class_Photo"));
            exClassListModel.setExName(Response.getString("Class_Name"));
            exClassListModel.setExTeacherName(Response.getString("Teachers_Full_Name"));
            exClassListModel.setExDuration(Response.getInt("Execute_Class_DaysCount"));
            exClassListModel.setExFee(Response.getInt("Execute_Class_fee"));
            exClassListModel.setExCapacity(Response.getInt("Execute_Class_Capacity"));
            ExClassList.add(exClassListModel);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ExClassList;
    }
    private List<Teachers> Parse_Teachers_Json(JSONArray response, final List<Teachers> Teachers_list) {
        Log.i(TAG, "Parse_Teachers_Json: ");
        for (int i = 0; i < 4; i++) {
            try {
                JSONObject Teachers_JsonObject = response.getJSONObject(i);
                Teachers teachers = new Teachers();
                teachers.setTeacher_ID(Teachers_JsonObject.getInt("Teachers_ID"));
                teachers.setTeachers_photo(Teachers_JsonObject.getString("Teachers_Photo"));
                teachers.setTeachers_full_name(Teachers_JsonObject.getString("Teachers_Full_Name"));
                teachers.setTeachers_Departemants_name(Teachers_JsonObject.getString("Departemants_Name"));
                teachers.setTeachers_Skill(Teachers_JsonObject.getString("Teachers_Skill"));
                teachers.setTeachers_Resume(Teachers_JsonObject.getString("Teaches_resume"));
                teachers.setTeacher_Email(Teachers_JsonObject.getString("Teacher_Email"));
                teachers.setTeachers_Departemant_ID(Teachers_JsonObject.getInt("Teachers_Departemants_ID"));
                Teachers_list.add(teachers);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Teachers_list;
    }

    private List<Teachers_full_DataModel> Parse_Full_Teachers(String response, List<Teachers_full_DataModel> Teachers_List) {
        try {
            JSONArray array = new JSONArray(response);
            Log.i(TAG, array.toString());
            for (int i = 0; i < array.length(); i++) {

                //getting product object from json array
                JSONObject Teachers = array.getJSONObject(i);
                Log.i(TAG, Teachers.toString());
                Teachers_full_DataModel teachers_full_dataModel = new Teachers_full_DataModel();
                teachers_full_dataModel.setTeacher_ID(Teachers.getInt("Teachers_ID"));
                teachers_full_dataModel.setTeachers_full_name(Teachers.getString("Teachers_Full_Name"));
                teachers_full_dataModel.setTeachers_Departemant_ID(Teachers.getInt("Teachers_Departemants_ID"));
                teachers_full_dataModel.setTeachers_Resume(Teachers.getString("Teaches_resume"));
                teachers_full_dataModel.setTeacher_Email(Teachers.getString("Teacher_Email"));
                teachers_full_dataModel.setTeachers_Departemants_name(Teachers.getString("Departemants_Name"));
                teachers_full_dataModel.setTeachers_Skill(Teachers.getString("Teachers_Skill"));
                teachers_full_dataModel.setTeachers_photo(Teachers.getString("Teachers_Photo"));

                Teachers_List.add(teachers_full_dataModel);


            }
        } catch (JSONException e) {
            // JSON error
            e.printStackTrace();
            Toast.makeText(context, "به پایان لیست رسیدید ! ", Toast.LENGTH_LONG).show();
        }

        return Teachers_List;
    }








    private ArrayList<LastNews> ParseLastNewsList(JSONArray response)
    {
        ArrayList<LastNews>LastNewsList=new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject LastNewsJsonObject = response.getJSONObject(i);
                LastNews lastNews = new LastNews();
                lastNews.setLastNewsID(Integer.parseInt(LastNewsJsonObject.getString("NewsID")));
                lastNews.setLastNewsTitle(LastNewsJsonObject.getString("NewsTitle"));
                lastNews.setLastNewsText(LastNewsJsonObject.getString("NewsMainText"));
                lastNews.setLastNewsLogo(LastNewsJsonObject.getString("NewsLogo"));
                lastNews.setLastNewsDate(LastNewsJsonObject.getString("NewsDate"));
                LastNewsList.add(lastNews);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return LastNewsList;

    }
    private ArrayList<Departemants_full_DataModel> Parse_Full_Departemants_Json(JSONArray response, ArrayList<Departemants_full_DataModel> DepartemantsList) {
        Log.i(TAG, "Parse_Full_Departemants_Json:");
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject FullDepartemantsJsonObject = response.getJSONObject(i);
                Departemants_full_DataModel FullDepartemantsDataModel = new Departemants_full_DataModel();
                FullDepartemantsDataModel.setId(FullDepartemantsJsonObject.getInt("Departemants_ID"));
                FullDepartemantsDataModel.setDepartemant_Title(FullDepartemantsJsonObject.getString("Departemants_Name"));
                FullDepartemantsDataModel.setDepartemants_Image(FullDepartemantsJsonObject.getString("Departemants_Logo"));
                DepartemantsList.add(FullDepartemantsDataModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return DepartemantsList;
    }

    private ArrayList<Classes> Parse_Full_Classes_Json(JSONArray response, ArrayList<Classes> Classes_List) {
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject full_Classes_Json_Object = response.getJSONObject(i);
                Classes full_Classes_Data_Model = new Classes();
                full_Classes_Data_Model.setClass_id(full_Classes_Json_Object.getInt("Class_ID"));
                full_Classes_Data_Model.setDepartemant_id(full_Classes_Json_Object.getInt("Departemant_ID"));
                full_Classes_Data_Model.setClass_Name(full_Classes_Json_Object.getString("Class_Name"));
                full_Classes_Data_Model.setClass_Description(full_Classes_Json_Object.getString("Class_Description"));
                full_Classes_Data_Model.setClass_Logo(full_Classes_Json_Object.getString("Class_Logo"));
                Classes_List.add(full_Classes_Data_Model);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return Classes_List;
    }
    private ArrayList<Services_full_DataModel> Parse_Full_Services_Json(JSONArray response,ArrayList<Services_full_DataModel> Services_List)
    {
        for(int i=0;i<response.length();i++)
        {
            try {
                JSONObject LoadFullServices_JsonObject=response.getJSONObject(i);
                Services_full_DataModel services_full_dataModel=new Services_full_DataModel();
                services_full_dataModel.setId(LoadFullServices_JsonObject.getInt("Major_ID"));
                services_full_dataModel.setService_Name(LoadFullServices_JsonObject.getString("Major_Name"));
                services_full_dataModel.setServices_Description(LoadFullServices_JsonObject.getString("Major_Description"));
                services_full_dataModel.setService_Logo(LoadFullServices_JsonObject.getString("Major_logo"));

                Services_List.add(services_full_dataModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Services_List;
    }
    private ArrayList<Departemants> Parse_Departemants_Json(JSONArray response,ArrayList<Departemants> Departemants_List)
    {
        for(int i=0;i<4;i++)
        {
            try {
                JSONObject LoadDepartemants_JsonObject=response.getJSONObject(i);
                Departemants Departemants_Data_Model=new Departemants();
                Departemants_Data_Model.setId(LoadDepartemants_JsonObject.getInt("Departemants_ID"));
                Departemants_Data_Model.setDepartemant_Title(LoadDepartemants_JsonObject.getString("Departemants_Name"));
                Departemants_Data_Model.setDepartemants_Image(LoadDepartemants_JsonObject.getString("Departemants_Logo"));
                Departemants_List.add(Departemants_Data_Model);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Departemants_List;
    }
    private ArrayList<Services> Parse_Services_Json(JSONArray response,ArrayList<Services>Services_List)
    {
        for(int i=0;i<4;i++)
        {
            try {
                JSONObject Parse_Services_JsonObject=response.getJSONObject(i);
                Services Parse_Services_Data_Model=new Services();
                Parse_Services_Data_Model.setId(Parse_Services_JsonObject.getInt("Major_ID"));
                Parse_Services_Data_Model.setService_Name(Parse_Services_JsonObject.getString("Major_Name"));
                Parse_Services_Data_Model.setServices_Description(Parse_Services_JsonObject.getString("Major_Description"));
                Parse_Services_Data_Model.setService_Logo(Parse_Services_JsonObject.getString("Major_logo"));
                Services_List.add(Parse_Services_Data_Model);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return Services_List;
    }

    private UserInformationDataModel parseUserRegisterInformationDataModel(JSONObject RegisterJsonObject)
    {
        UserInformationDataModel userInformationDataModel =new UserInformationDataModel();
        try {

            userInformationDataModel.setUser_ID(Integer.parseInt(RegisterJsonObject.getString("User_ID")));
            userInformationDataModel.setUser_First_Name(RegisterJsonObject.getString("User_First_Name"));
            userInformationDataModel.setUser_User_Last_Name(RegisterJsonObject.getString("User_Last_Name"));
            userInformationDataModel.setUser_Email_Address(RegisterJsonObject.getString("User_Email_Address"));
            userInformationDataModel.setUser_Phone_Number(RegisterJsonObject.getString("User_Phone_Number"));
            userInformationDataModel.setUser_Profile(RegisterJsonObject.getString("User_Profile"));
            userInformationDataModel.setUser_User_Name(RegisterJsonObject.getString("User_User_Name"));
            userInformationDataModel.setUser_Password(RegisterJsonObject.getString("User_Password"));
            userInformationDataModel.setCompelte_Profile(Integer.parseInt(RegisterJsonObject.getString("Compelte_Profile")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userInformationDataModel;
    }
    private UserInformationDataModel parseLoginData(JSONObject RegisterJsonObject)
    {
        UserInformationDataModel userInformationDataModel=new UserInformationDataModel();
        try {
            userInformationDataModel.setUser_ID(Integer.parseInt(RegisterJsonObject.getString("User_ID")));
            userInformationDataModel.setUser_First_Name(RegisterJsonObject.getString("User_First_Name"));
            userInformationDataModel.setUser_User_Last_Name(RegisterJsonObject.getString("User_Last_Name"));
            userInformationDataModel.setUser_Email_Address(RegisterJsonObject.getString("User_Email_Address"));
            userInformationDataModel.setUser_Phone_Number(RegisterJsonObject.getString("User_Phone_Number"));
            userInformationDataModel.setUser_Profile(RegisterJsonObject.getString("User_Profile"));
            userInformationDataModel.setUser_User_Name(RegisterJsonObject.getString("User_User_Name"));
            userInformationDataModel.setUser_Password(RegisterJsonObject.getString("User_Password"));
            userInformationDataModel.setCompelte_Profile(Integer.parseInt(RegisterJsonObject.getString("Compelte_Profile")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userInformationDataModel;
    }

    public boolean internetConnection() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifi != null && wifi.isConnected()) {
            return true;
        }
        NetworkInfo data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (data != null && data.isConnected()) {
            return true;
        }
        NetworkInfo active = cm.getActiveNetworkInfo();
        if (active != null && active.isConnected()) {
            return true;
        }

        return false;
    }



    public interface OnLoginData{
        void onRecieved(UserInformationDataModel userInformationDataModel);
    }
    public interface OnRegisterInformationRecieved
    {
        void OnRegisterInformationRecieved(UserInformationDataModel userInformationDataModel);
    }
    public interface  OnTeacherClassesListRecieved{
        void OnClassessListRecieved(ArrayList<Camps> classesList);
    }
    public interface  OnTeacherCommentListRecieved{
        void OnCommentListRecieved(ArrayList<User_Comments_Recycler_View_Data_Model> CommentList);
    }

    public interface  OnOtherMajorRegisterResultRecieved{
        void OnOtherMajorResultRecieved(boolean HasError);
    }
    public interface OnExClassListByClassIDRecieved{
        void ExClassListByClassIDRecieved(ArrayList<ExClassListModel> ExClassList);
        void ExClassListByClassIDRecievedhasError();
    }
    public interface OnExClassRegisterResult{
        void OnExClassRegisterResultRecieved(String ErrorText);
    }
    public interface OnRegisteredExClassResult{
        void OnRegisteredExClassResultRecieved(ArrayList<RegisteredExClassesDataModel> registeredExClassesList);
        void OnRegisteredExClassResultError();
    }
    public interface OnRegisteredOTherClassResult{
        void OnRegisteredOTherClassResultRecieved(ArrayList<OtherMajorRegisteredDataModel> registeredOtherClassesList);
        void OnRegisteredOTherClassResultError();
    }
    public interface OnSubmitNewCommentResult{
        void OnSubmitNewCommentResultRecieved(Boolean hasError);
    }
    public interface  OnLastNewsList{
        void OnLastNewsList(ArrayList<LastNews>LastNewsList);
    }
    public interface OnBookList{
        void OnBookListRecieved(ArrayList<Library> BookList);
    }
    public interface  OnImageList{
        void OnImageListRecieved(ArrayList<ImageGallery>ImageList);
    }
}

