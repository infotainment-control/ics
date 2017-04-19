/*
public  class LocationUtilities {
    Context c;
    public LocationUtilities(Context context) {
        c = context;
    }
    public Location getGPS () {
        Context context = c.getApplicationContext();
        LocationManager lm = (LocationManager)c.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        Location l = null;

        for (int i=providers.size()-1; i>=0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) break;
        }
        return l;
    }
    public String getPostalCode(Location loc) {
        Geocoder geocoder = new Geocoder(c, Locale.getDefault());
        if(loc == null) {
            return "44240"; // if something goes wrhing, just return kent's postal code.
        }
        try {
            List<Address> addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);

            String postalCode = addresses.get(0).getPostalCode();
            return postalCode;
        } catch (IOException e) { // if something goes wrhing, just return kent's postal code.
            return "44240"; 
        }
    }
}
*/
