package ksoapexample.com.ksoapexample;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ksoapexample.com.ksoapexample.soap.SoapCall;
import ksoapexample.com.ksoapexample.utils.Util;

public class MainActivity extends AppCompatActivity {
private Button btn_post_to_server;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        btn_post_to_server=(Button)findViewById(R.id.btn_post_to_server);
        btn_post_to_server.setOnClickListener(postToServerClickListener);
    }


    View.OnClickListener postToServerClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final SoapCall soapCall=new SoapCall(Util.SOAP_ACTION,Util.SOAP_METHOD_NAME,Util.WSDL_NAMESAPCE,Util.SOAP_URL);
            new AsyncTask<Void,Object,Object>(){

                @Override
                protected Object doInBackground(Void... params) {
                    Object response= soapCall.call("60");
                    return response;
                }

                @Override
                protected void onPostExecute(Object response) {
                    super.onPostExecute(response);
                    if(response==null){
                        
                        Toast.makeText(context,"ERROR 404 found",Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute();


        }
    };
}
