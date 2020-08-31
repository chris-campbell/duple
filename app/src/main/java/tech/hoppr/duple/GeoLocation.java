package tech.hoppr.duple;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
//import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GeoLocation implements CanGeoLocation {
    private Context mContext;

    GeoLocation(Context context) {
        this.mContext = context;
    }

    @Override
    public Address fetchAddress(String location) {
        List<Address> addressList;
        Geocoder geocoder = new Geocoder(mContext);
        Address address = null;

        try {
            addressList = geocoder.getFromLocationName(location, 1);
            address = addressList.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public Address reverseFetchLocation(double lat, double lon) {
        List<Address> addresses;
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        Address address = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
            address = addresses.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}
