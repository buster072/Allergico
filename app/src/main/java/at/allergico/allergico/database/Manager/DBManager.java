package at.allergico.allergico.database.Manager;

import android.os.AsyncTask;
import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Michael on 23/05/2015.
 */
public class DBManager {
    private URL url;
    private OutputStreamWriter wr;


    /**
     * ***** SINGLETON START *******
     */
    private static DBManager _instance;

    public static DBManager getInstance() {
        if (_instance == null) {
            _instance = new DBManager();
        }
        return _instance;
    }

    private DBManager() {
    }


    /**
     * ***** SINGLETON END ********
     */

    public boolean addUser(String jsonString) {
//        AddUserTask addUserTask = new AddUserTask();
//        String[] params = new String[1];
//        params[0] = jsonString;
//       return addUserTask.doInBackground(params);
        try {
            //String jsonString = params[0];
            String link = "http://sadler.or.at/allergico/service.php?InsertUser=" + URLEncoder.encode(jsonString);
            System.out.println(link);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(String jsonString){
        try {
            //String jsonString = params[0];
            String link = "http://sadler.or.at/allergico/service.php?UpdateUser=" + URLEncoder.encode(jsonString);
            System.out.println(link);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProduct(String jsonString){
        try {
            //String jsonString = params[0];
            String link = "http://sadler.or.at/allergico/service.php?InsertProduct=" + URLEncoder.encode(jsonString);
            System.out.println(link);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addUserHasAllergen(String jsonString){
        try {
            //String jsonString = params[0];
            String link = "http://sadler.or.at/allergico/service.php?InsertUserHasAllergen=" + URLEncoder.encode(jsonString);
            System.out.println(link);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean removeUserHasAllergen(int UserID, int AllergenID){
        try {
            //String jsonString = params[0];
            String link = "http://sadler.or.at/allergico/service.php?DeleteUserHasAllergen=T&UserID=" + UserID + "&AllergenID=" + AllergenID;
            System.out.println(link);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getObject(String getParameter) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
            HttpGet httpget = new HttpGet("http://sadler.or.at/allergico/service.php?get=" + getParameter); // Set the action you want to do
            HttpResponse response = httpclient.execute(httpget); // Executeit
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent(); // Create an InputStream with the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) // Read line by line
                sb.append(line + "\n");

            String resString = sb.toString(); // Result is here

            is.close(); // Close the stream
            return resString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addProductHasAllergen(String jsonString) {
        try {
            //String jsonString = params[0];
            String link = "http://sadler.or.at/allergico/service.php?InsertProductHasAllergen=" + URLEncoder.encode(jsonString);
            System.out.println(link);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(link);
            HttpResponse response = client.execute(request);
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

//    public class GetObjektTask extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            try {
////                HttpParams httpParams = new BasicHttpParams();
//                // httpParams.setParameter("get",getParameter);
//                HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
//                HttpGet httpget = new HttpGet("http://sadler.or.at/allergico/service.php?get="+params[0]); // Set the action you want to do
//                HttpResponse response = httpclient.execute(httpget); // Executeit
//                HttpEntity entity = response.getEntity();
//                InputStream is = entity.getContent(); // Create an InputStream with the response
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) // Read line by line
//                    sb.append(line + "\n");
//
//                String resString = sb.toString(); // Result is here
//
//                is.close(); // Close the stream
//                return resString;
//            }catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                return null;
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    public class AddUserTask extends AsyncTask<String, Void, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            try
//            {
//                String jsonString = params[0];
//                String link = "http://sadler.or.at/allergico/service.php?InsertUser=" + URLEncoder.encode(jsonString);
//                System.out.println(link);
//                DefaultHttpClient client = new DefaultHttpClient();
//                HttpGet request = new HttpGet(link);
//                HttpResponse response = client.execute(request);
//                return true;
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//                return false;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//    }
//}
