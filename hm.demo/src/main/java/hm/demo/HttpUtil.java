package hm.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	private String invokeURL(String urlString) {  
        HttpURLConnection conn = null;  
        URL url = null;  
        try {  
            url = new URL(urlString);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(20000);  
            conn.setReadTimeout(20000);  
            conn.setRequestMethod("GET");  
            conn.connect();  
            InputStream urlStream = conn.getInputStream();  
            StringBuilder sb = new StringBuilder();  
            BufferedReader reader = null;  
            try {  
                reader = new BufferedReader(new InputStreamReader(urlStream));  
                String line = null;  
                while ((line = reader.readLine()) != null) {  
                    sb.append(line);  
                }  
            }  
            finally {  
                if (reader != null)  
                    reader.close();  
            }  
            return sb.toString();  
  
        }  
        catch (Exception e) {  
            e.printStackTrace();  
            //log.error("http调用失败,url=" + urlString, e);  
        }  
        finally {  
            if (conn != null) {  
                conn.disconnect();  
            }  
        }  
        return "error";  
    }  
}
