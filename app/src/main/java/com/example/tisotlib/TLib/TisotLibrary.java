package com.example.tisotlib.TLib;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TisotLibrary {
    private final String API_LINK= "https://data.gov.il/api/3/action/datastore_search?resource_id=e83f763b-b7d7-479e-b172-ae981ddc6de5&limit=10000";
    public ArrayList<Flight> getHourArrivalsFromAPI(Integer _hour, String date) {
        String hour;
        JSONObject jsonResponse;
        JSONArray JSONrecordsArray;
        ArrayList<Flight> arrayList;
        String response = "";
        URL url;
        if (_hour < 10) {
            hour = "0"+Integer.toString(_hour);
        }else {
            hour = Integer.toString(_hour); }
        try {
            url = new URL(API_LINK +
                    "&q="+date+"T"+hour+
                    "&filters={%22CHAORD%22:%22A%22}");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            try {
                jsonResponse = new JSONObject(response);
                JSONrecordsArray = jsonResponse.getJSONObject("result").getJSONArray("records");
                arrayList = JSONToArray(JSONrecordsArray);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return arrayList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Flight> getHourDeparturesFromAPI(Integer _hour, String date) {
        String hour;
        JSONObject jsonResponse;
        JSONArray JSONrecordsArray;
        ArrayList<Flight> arrayList;
        String response = "";
        URL url;
        if (_hour < 10) {
            hour = "0"+Integer.toString(_hour);
        }else {
            hour = Integer.toString(_hour); }
        try {
            url = new URL(API_LINK +
                    "&q="+date+"T"+hour+
                    "&filters={%22CHAORD%22:%22D%22}");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            Log.d("API", ""+response);
            try {
                jsonResponse = new JSONObject(response);
                JSONrecordsArray = jsonResponse.getJSONObject("result").getJSONArray("records");
                arrayList = JSONToArray(JSONrecordsArray);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return arrayList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Flight> getFlightsByPlaneCodeFromList(String flightCode, @NonNull ArrayList<Flight> flightList) {
        ArrayList<Flight> response = new ArrayList<>();
        for (Flight fl : flightList) {
            if (fl.getFlightCode().equals(flightCode)) {
                Log.d("FLIGHT", "FOUND "+fl.getFlightCode()+" for STRING "+flightCode);
                response.add(fl);
            }
        }
        return response;
    }
    public ArrayList<Flight> updateArrivalsList(@NonNull ArrayList<Flight> list) {
        JSONObject jsonResponse;
        JSONArray JSONrecordsArray;
        ArrayList<Flight> arrayList;
        String response = "";
        String request = API_LINK+"&filters={%22_id%22:[";
        for (Flight fl : list) {
            if (list.indexOf(fl)+1 == list.size()) {
                request += "\""+fl.getId()+"\"]}";
            }else {
                request += "\""+fl.getId()+"\",";
            }
        }
        Log.d("UPDATE", ""+request);
        try {
            URL url = new URL(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            try {
                jsonResponse = new JSONObject(response);
                JSONrecordsArray = jsonResponse.getJSONObject("result").getJSONArray("records");
                arrayList = JSONToArray(JSONrecordsArray);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
    public ArrayList<Flight> getCountryFlightsFromList(String country, ArrayList<Flight> list) {
        ArrayList<Flight> response = new ArrayList<>();
        for (Flight fl : list) {
            if (fl.getCountry().equals(country)) {
                
                Log.d("COUNTRY", "FOUND id: "+fl.getId());
                
            }
        }
        return response;
    }
    
    public Flight getFlightByIdFromList(String id, ArrayList<Flight> list) {
        Flight response = null;
        for (Flight fl : list) {
            if (fl.getId().equals(id)) {
                
                Log.d("ID", "FOUND id: " + fl.getId());
                response = fl;
                
            }
        }
        return response;
    } 
    //returning ArrayList of flights with specific flight number/country (responce is with Arrivals And departures too)
    public ArrayList<Flight> getCountryFlightsFromApi (String country) {
        JSONObject jsonResponse;
        JSONArray JSONrecordsArray;
        ArrayList<Flight> arrayList;
        String response = "";
        URL url;
        try {
            url = new URL(String.format(
                    API_LINK+"&filters={\"CHLOC1CH\":\"%s\"}", country
            ));
            //Log.d("COUNTRYAPI",""+url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            jsonResponse = new JSONObject(response);
            JSONrecordsArray = jsonResponse.getJSONObject("result").getJSONArray("records");
            arrayList = JSONToArray(JSONrecordsArray);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }
    public ArrayList<Flight> getFlightsByPlaneCodeFromApi(String flightnumber) {
        JSONObject jsonResponse;
        JSONArray JSONrecordsArray;
        ArrayList<Flight> arrayList;
        String response = "";
        URL url;
        String[] splitArr = flightnumber.split(" ", 0);
        String apiString = String.format(API_LINK+
                "&filters={\"CHOPER\":\"%1s\",\"CHFLTN\":\"%2s\"}", splitArr[0], splitArr[1]);
        try {
            url = new URL(apiString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            jsonResponse = new JSONObject(response);
            JSONrecordsArray = jsonResponse.getJSONObject("result").getJSONArray("records");
            arrayList = JSONToArray(JSONrecordsArray);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return arrayList;
    }



    @NonNull
    private ArrayList<Flight> JSONToArray(@NonNull JSONArray recordsArray) throws JSONException {
        ArrayList<Flight> arrayList = new ArrayList<>();
        for (int i = 0; i < recordsArray.length(); i++) {
            JSONObject obj = recordsArray.getJSONObject(i);
            arrayList.add(new Flight(
                    obj.getString("_id"),
                    obj.getString("CHAORD"),
                    obj.getString("CHOPER")+" "+obj.getString("CHFLTN"),
                    obj.getString("CHOPERD"),
                    obj.getString("CHPTOL"),
                    obj.getString("CHSTOL"),
                    obj.getString("CHLOC1CH"),
                    obj.getString("CHLOC1TH"),
                    obj.getString("CHRMINH")
            ));
        }
        return  arrayList;
    }
}

