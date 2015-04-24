package com.example.amit.whattoeat.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jun on 4/23/2015.
 */
public class URLGetter {
    public String getUrlString(String urlSpec) throws IOException {
//        urlSpec = "http://api.yummly.com/v1/api/recipes?_app_id=7a7d2bea&_app_key=c178a9fb9e73b844dea1ee9fdc3d02ea&q=Onion";//todo delete this just for testing
//                  "http://api.yummly.com/v1/api/recipes?_app_id=7a7d2bea&_app_key=c178a9fb9e73b844dea1ee9fdc3d02ea&q=Onion"
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) { return null;}
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        }finally {
            connection.disconnect();
        }
    }
}
