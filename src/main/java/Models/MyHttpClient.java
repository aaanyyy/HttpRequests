package Models;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MyHttpClient implements HttpClient {


    private String headersToString(Map<String,String> headers)
    {
        String result="";
        for (Map.Entry<String, String> header : headers.entrySet()) {
            result=result+header.getKey()+": "+header.getValue()+"\r\n";
        }
        return result;
    }
    @Override
    public Response get(String urlString, Map<String, String> headers) {
        Response response=new Response();
        try {
            URL url=new URL(urlString);
            try{
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "text/html");
                //connection.connect();
                response.setStatusCode(connection.getResponseCode());
                response.setStatusText(connection.getResponseMessage());
                response.setHeaders(connection.getHeaderFields());

                Scanner sc=new Scanner(connection.getInputStream());

                BufferedReader bufferedReader = null;
                if (connection.getResponseCode()>=100 && connection.getResponseCode() <= 399) {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }
                String payload=null;
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    payload+="\n"+output;
                }
                response.setPayload(payload);


            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return response;
    }




    @Override
    public Response post(String urlString, Map<String, String> headers, byte[] payload) {

        Response response=new Response();
        try {
            URL url=new URL(urlString);
            try{
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "text/html");


                connection.setDoOutput(true);
                try(OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                    writer.write(payload.toString());
                }

                response.setStatusCode(connection.getResponseCode());
                response.setStatusText(connection.getResponseMessage());
                response.setHeaders(connection.getHeaderFields());

                Scanner sc=new Scanner(connection.getInputStream());

                BufferedReader bufferedReader = null;
                if (connection.getResponseCode()>=100 && connection.getResponseCode() <= 399) {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }
                String inputPayload=null;
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    inputPayload+="\n"+output;
                }
                response.setPayload(inputPayload);


            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return response;

    }

    @Override
    public Response post(String urlString, Map<String, String> headers, String payload) {
        Response response=new Response();
        try {
            URL url=new URL(urlString);
            try{
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "text/html");


                connection.setDoOutput(true);
                try(OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                    writer.write(payload);
                }


                response.setStatusCode(connection.getResponseCode());
                response.setStatusText(connection.getResponseMessage());
                response.setHeaders(connection.getHeaderFields());

                Scanner sc=new Scanner(connection.getInputStream());

                BufferedReader bufferedReader = null;
                if (connection.getResponseCode()>=100 && connection.getResponseCode() <= 399) {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }
                String inputPayload=null;
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    inputPayload+="\n"+output;
                }
                response.setPayload(inputPayload);


            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return response;

    }

    @Override
    public Response put(String url, Map<String, String> headers, byte[] payload) {
        return null;
    }

    @Override
    public Response put(String url, Map<String, String> headers, String payload) {
        return null;
    }

    @Override
    public Response delete(String url, Map<String, String> headers, byte[] payload) {
        return null;
    }

    @Override
    public Response delete(String url, Map<String, String> headers, String payload) {
        return null;
    }
}
