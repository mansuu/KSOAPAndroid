package ksoapexample.com.ksoapexample.soap;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static android.provider.ContactsContract.CommonDataKinds.Identity.NAMESPACE;

/**
 * Created by Himanshu on 11/23/2016.
 */

public class SoapCall {
    private String soapAction;
    private String soapOperation;
    private String wsdlTargetNamespace;
    private String soapAddress;

    public  SoapCall(String soapAction,String soapOperation,String wsdlTargetNamespace,String soapAddress){
        this.soapAction=soapAction;
        this.soapOperation=soapOperation;
        this.wsdlTargetNamespace=wsdlTargetNamespace;
        this.soapAddress=soapAddress;
    }

    public Object call(String celcius){
        SoapObject request=new SoapObject(wsdlTargetNamespace,soapOperation);
        request.addProperty("Celsius",celcius);
        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        HttpTransportSE httpTransport=new HttpTransportSE(soapAddress);
        Object response=null;
        try{
           httpTransport.call(soapAction,envelope);
            response=envelope.getResponse();
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return response;
    }
}
