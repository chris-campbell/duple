package tech.hoppr.duple;

import android.location.Address;

public interface CanGeoLocation {
    Address fetchAddress(String Location);
    Address reverseFetchLocation(double lat, double lon);
}
