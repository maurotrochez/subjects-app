package com.example.davidmautro.subjectsapp;

import android.accounts.Account;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.davidmautro.subjectsapp.API.UserService;
import com.example.davidmautro.subjectsapp.model.Token;
import com.example.davidmautro.subjectsapp.model.University;
import com.example.davidmautro.subjectsapp.model.User;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetTokenTask extends AsyncTask {
    Activity mActivity;
    String mScope;
    Account mEmail;

    GetTokenTask(Activity activity, Account name, String scope) {
        this.mActivity = activity;
        this.mScope = scope;
        this.mEmail = name;
    }
    private static final String TAG = "GetTokenTask";

    /**
     * Executes the asynchronous job. This runs when you call execute()
     * on the AsyncTask instance.
     */
    @Override
    protected Object doInBackground(Object[] params) {
        try {
            final String token = fetchToken();
            Log.d(TAG, "token is: "+token);
            Token tok = new Token();
            tok.setToken(token);
            UserService userService = ServiceGenerator.createService(UserService.class);
            Call<User> call = userService.validateToken(tok);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    int status_code = response.code();
                    User user = response.body();
                    if (user != null) {
                        Log.d(TAG, user.getName());
                        SharedPreferences spPersonalData = mActivity.getSharedPreferences("PersonalData", mActivity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = spPersonalData.edit();
                        editor.putString("token", token);
                        editor.putInt("idUser", user.getIdUser());
                        editor.apply();

                        Intent intent = new Intent(mActivity, UniversityActivity.class);
                        mActivity.startActivity(intent);

                    }
                    //txtVwExample.setText(user.getName());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(mActivity, "Error try to connect to server", Toast.LENGTH_SHORT).show();

                }
            });
           /*
           This part has not been tested yet.
            if (token != null) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://yourserver/tokenauth");

                try {
                    List nameValuePairs = new ArrayList(1);
                    nameValuePairs.add(new BasicNameValuePair("idToken", token));
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    int statusCode = response.getStatusLine().getStatusCode();
                    final String responseBody = EntityUtils.toString(response.getEntity());
                    Log.i(TAG, "Signed in as: " + responseBody);
                } catch (ClientProtocolException e) {
                    Log.e(TAG, "Error sending ID token to backend.", e);
                } catch (IOException e) {
                    Log.e(TAG, "Error sending ID token to backend.", e);
                }

            }*/
        } catch (IOException e) {
            Log.d(TAG, "exception: "+e);
            // The fetchToken() method handles Google-specific exceptions,
            // so this indicates something went wrong at a higher level.
            // TIP: Check for network connectivity before starting the AsyncTask.

        }

        return null;
    }

    /**
     * Gets an authentication token from Google and handles any
     * GoogleAuthException that may occur.
     */
    protected String fetchToken() throws IOException {
        try {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
        } catch (IOException e) {
            Log.e(TAG, "Error retrieving ID token. IOException", e);
            return null;
        } catch (GoogleAuthException e) {
            Log.e(TAG, "Error retrieving ID token. Auth Exception", e);
            return null;
        }
    }

}


