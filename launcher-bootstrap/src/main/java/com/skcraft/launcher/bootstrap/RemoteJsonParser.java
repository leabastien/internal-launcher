package com.skcraft.launcher.bootstrap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class RemoteJsonParser {


    public static URL urlConvert(String str) throws MalformedURLException, URISyntaxException {
        URI uri = new URI(str);
        return uri.toURL();
    }

    public static String getJsonValues(String url2, String value) throws IOException, URISyntaxException {
        URL url = urlConvert(url2);
        URLConnection request = url.openConnection();
        request.connect();

        //Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
        return  rootobj.get(value).getAsString();
    }


}
