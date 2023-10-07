package com.example.sampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sampleproject.Model.Asset;
import com.example.sampleproject.Model.Status;
import com.example.sampleproject.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;
    //String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NjUyMDc4MzMsImlhdCI6MTY2NTEyMTQ0OCwiYXV0aF90aW1lIjoxNjY1MTIxNDMzLCJqdGkiOiIxZjc3OTFjMy00OGJmLTQ4NGUtODI3MS1kOGY0NWNiNTcyMzkiLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI5MjYyNGM1Yi1iNjM3LTRjNDItOWVhYS02MzhkNmRkZDJiNjQiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiOTI2MjRjNWItYjYzNy00YzQyLTllYWEtNjM4ZDZkZGQyYjY0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyMSJ9.U0pMuxOkHW8pZKBVmTlQeIzGq-WtQoHzxCIgf2-gmB8vsAFCZlajaBvO8jJpupmS7NzegIFI6TcfAXkXFZXxPIJAmPb0a4RtFr-pthoOtERpct5TjxZvrEfJjNcC1J_EM1aumUJvOUZE7goVl4qG4UCw08Su0wIcmztWthLQu3CTgNuO5XwBqORJwGqIZAdSLyRSjhx4970nU4C_z-DRxVMtv1jlDfUyHeIHUA6c9gEiEY5PpbR6bnHDslb9Ta3MUOzFEdMd_PcXZtNxsJFKaeD_YKp-n9dmIQ45DY5-JKa6Mefo92NuAO_X9Jv5SA3FB5hj4dW1ODxjbnCqR3ZqTg";
    //String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLcHRXNWJCcTlsRGliY2s5NHI3TldHQVl0SHBrUFI3N1A4V0hMWDVIX1E0In0.eyJleHAiOjE2NjUyMDg5NjQsImlhdCI6MTY2NTEyMjU3OSwiYXV0aF90aW1lIjoxNjY1MTIyNTY0LCJqdGkiOiJkNzdiNmE4Ny0yN2ViLTRiYWItYjVlNC03OGY1ZjYzYTQ4NzciLCJpc3MiOiJodHRwczovLzEwMy4xMjYuMTYxLjE5OS9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTAxZGQ1MmMtMjNiYS00ZjM4LWExMjQtYjc4MGUxYjVhODFiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoib3BlbnJlbW90ZSIsInNlc3Npb25fc3RhdGUiOiI4N2RiYWQ0Yy1lNzU0LTQ3NzktYTY1OC0yNDc0YTcyYThjNzQiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vbG9jYWxob3N0IiwiaHR0cHM6Ly8xMDMuMTI2LjE2MS4xOTkiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWFzdGVyIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7Im9wZW5yZW1vdGUiOnsicm9sZXMiOlsicmVhZDp1c2VycyIsInJlYWQ6bG9ncyIsInJlYWQ6bWFwIiwicmVhZDpydWxlcyIsInJlYWQ6YXNzZXRzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiODdkYmFkNGMtZTc1NC00Nzc5LWE2NTgtMjQ3NGE3MmE4Yzc0IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c2VyMSJ9.jr33vmRATXCtvQ6VGLxk_Y3XTRMzfpierqSSEvsOM0kKaZP0IIAlxlvWRnjj2W2iVMJqkKX-7vY6-GqYGoXbqKwXcsdziHQMG3aTK-t-WaiE-fh_FU3Ge5z1A-9dXkLAS_gMjeVxCQZt58vycrBzDcJUSceiSaAsIpDt_DzuVWGJMGFd2-26k2SJbWN-EijQy8f--UH7p_9tZxPytFnVKxRy_QRV6cZ7bYDkDaZkccifVfZnOnwB1OZm52IlDR2YMESg9uGH6HJeGSXJ9tZ6ob5Ta5Bs1Q7PKDqs6MKyi7x3dFGYG0jbZ3KMjz1MEtYUHcVSzSuADm1gJHT592gTNw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txttype = (TextView)findViewById(R.id.textView1);
        TextView txttype2 = (TextView)findViewById(R.id.textView2);
        TextView txttype3 = (TextView)findViewById(R.id.textView3);
        TextView txttype4 = (TextView)findViewById(R.id.textView4);
        TextView txttype5 = (TextView)findViewById(R.id.textView5);
        TextView txttype6 = (TextView)findViewById(R.id.textView6);
        TextView txttype7 = (TextView)findViewById(R.id.textView7);
        TextView txttype8 = (TextView)findViewById(R.id.textView8);
        TextView txttype9 = (TextView)findViewById(R.id.textView9);
        TextView txttype10 = (TextView)findViewById(R.id.textView10);
        txttype.setText("HELLO MY FRIEND");
        txttype2.setText("HELLO MY FRIEND 2");
        txttype3.setText("HELLO MY FRIEND 3");
        txttype4.setText("HELLO MY FRIEND 4");
        txttype5.setText("HELLO MY FRIEND 5");
        txttype6.setText("HELLO MY FRIEND 6");
        txttype7.setText("HELLO MY FRIEND 7");
        txttype8.setText("HELLO MY FRIEND 8");
        txttype9.setText("HELLO MY FRIEND 9");
        txttype10.setText("HELLO MY FRIEND 10");
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Asset> call = apiInterface.getAsset("5zI6XqkQVSfdgOrZ1MyWEf");//, "Bearer "+ token);
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {

                //Log.d ("API CALL", response.toString());
                Asset asset = response.body();


                txttype4.setText("AccessPublicRead: " + asset.accessPublicRead); //Du lieu tra ve
                txttype2.setText("Name of sensor: "+asset.name);
                txttype.setText("ID of sensor: "+ asset.id);
                txttype3.setText("Type of sensor: "+asset.type);
                //txttype.setText(asset.type);
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                //t.printStackTrace();
            }
        });

        Call<User> call1 = apiInterface.getUser();
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call1, Response<User> response1) {

                //Log.d ("API CALL", response.toString());
                User user = response1.body();
                txttype6.setText("Email: " + user.email); //Du lieu tra ve
                txttype5.setText("Username: " + user.username); //Du lieu tra ve
                txttype7.setText("Account is service: " + user.serviceAccount); //Du lieu tra ve
                txttype8.setText("Account ID: " + user.id); //Du lieu tra ve
            }

            @Override
            public void onFailure(Call<User> call1, Throwable t) {
                //t.printStackTrace();
            }
        });

        Call<Status> call2 = apiInterface.getInfor();
        call2.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call2, Response<Status> response2) {

                Log.d ("API2", response2.toString());
                Status status = response2.body();
                txttype9.setText("Version: " + status.version); //Du lieu tra ve
                txttype10.setText("Authen server URL: " + status.authServerUrl); //Du lieu tra ve
                //txttype11.setText("Account is service: " + user.serviceAccount); //Du lieu tra ve
                //txttype12.setText("Account ID: " + user.id); //Du lieu tra ve
                //txttype13.setText("Account ID: " + user.id); //Du lieu tra ve
            }

            @Override
            public void onFailure(Call<Status> call2, Throwable t) {
                //t.printStackTrace();
            }
        });

    }
}