//
//  https://data.gov.il/api/3/action/datastore_search?resource_id=e83f763b-b7d7-479e-b172-ae981ddc6de5&q=2023-07-03T14&filters={%22CHAORD%22:%22A%22}&limit=1000
//
//



package com.example.tisotlib.TLib;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class TisotLibrary {
    private ArrayList<Fly> arrayList;
    public ArrayList<Fly> getHourArrivals(Integer _hour, String date) {
        JSONObject jsonResponse;
        JSONArray recordsArray;
        this.arrayList = new ArrayList<>();
        String response = "";
        URL url;
        String hour = Integer.toString(_hour);
        try {
            url = new URL("https://data.gov.il/api/3/action/datastore_search?resource_id=e83f763b-b7d7-479e-b172-ae981ddc6de5" +
                    "&q="+date+"T"+hour+
                    "&filters={%22CHAORD%22:%22A%22}" +
                    "&limit=1000");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }
            reader.close();
            Log.d("API", ""+response);
            try {
                jsonResponse = new JSONObject(response);
                recordsArray = jsonResponse.getJSONObject("result").getJSONArray("records");
                for (int i = 0; i < recordsArray.length(); i++) {
                    JSONObject obj = recordsArray.getJSONObject(i);
                    this.arrayList.add(new Fly(obj.getString("CHAORD"),
                            obj.getString("CHOPER")+" "+obj.getString("CHFLTN"),
                            obj.getString("CHOPERD"),
                            obj.getString("CHPTOL"),
                            obj.getString("CHSTOL"),
                            obj.getString("CHLOC1CH"),
                            obj.getString("CHLOC1TH")));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return arrayList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Only needed if you creating more then one list of flights to work properly with search
    public void updateFlightsList (ArrayList<Fly> flightlist) {
        this.arrayList = flightlist;
    }
    public Fly getSpecificFlight(String flightCode) {
        Fly response = null;
        for (Fly fl : this.arrayList) {
            if (fl.getFlightCode().equals(flightCode)) {
                Log.d("FLIGHT", "FOUND "+fl.getFlightCode()+" for STRING "+flightCode);
                response = fl;
            }
        }
        return response;
    }
}

