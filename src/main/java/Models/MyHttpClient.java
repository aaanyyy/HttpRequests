package Models;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class MyHttpClient implements HttpClient {


    private Response request(String urlString, Map<String, String> headers, String type, String payload) {
        Response response = new Response();


        try {
            URL url = new URL(urlString);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(type);
                //connection.setRequestProperty("Accept", "text/html");
                for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                    connection.setRequestProperty(headerEntry.getKey(), headerEntry.getValue());
                }
                if (payload != null) {

                    connection.setDoOutput(true);

                    try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                        writer.write(payload);

                    }
                }
                if (type.equals("DELETE"))
                    connection.connect();


                response.setStatusCode(connection.getResponseCode());
                response.setStatusText(connection.getResponseMessage());
                Map<String, List<String>> res = connection.getHeaderFields();
                Map<String, String> resultHeadersMap = new HashMap<>();
                for (Map.Entry<String, List<String>> entryHeader : res.entrySet()) {
                    resultHeadersMap.put(entryHeader.getKey(), entryHeader.getValue().get(0));
                }
                response.setHeaders(resultHeadersMap);


                BufferedReader bufferedReader = null;
                if (connection.getResponseCode() >= 100 && connection.getResponseCode() <= 399) {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                }

                String payloadLine;
                StringBuilder receivedPayload = new StringBuilder();
                while ((payloadLine = bufferedReader.readLine()) != null) {
                    receivedPayload.append("\n");
                    receivedPayload.append(payloadLine);

                }
                response.setPayload(receivedPayload.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return response;

    }


    @Override
    public Response get(String urlString, Map<String, String> headers) {


        return request(urlString,headers,"GET",null);
    }


    @Override
    public Response post(String urlString, Map<String, String> headers, byte[] payload) {

        return request(urlString,headers,"POST",payload.toString());

    }

    @Override
    public Response post(String urlString, Map<String, String> headers, String payload) {
        return request(urlString,headers,"POST",payload);
    }

    @Override
    public Response put(String urlString, Map<String, String> headers, byte[] payload) {
        return request(urlString,headers,"PUT",payload.toString());

    }

    @Override
    public Response put(String urlString, Map<String, String> headers, String payload) {

        return request(urlString,headers,"PUT",payload);

    }

    @Override
    public Response delete(String urlString, Map<String, String> headers, byte[] payload) {
        return request(urlString,headers,"DELETE",payload.toString());
        }


    

    @Override
    public Response delete(String urlString, Map<String, String> headers, String payload) {
        return request(urlString,headers,"DELETE",payload);

    }
}
