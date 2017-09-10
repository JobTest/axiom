package example.testtask.townsandroads2;

/**
 * Created by alexandr on 04.02.16.
 */
class City2 {
    private String name;
    private double latitude;
    private double longitude;

    public City2(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City2{" +
                "latitude=" + latitude +
                " longitude=" + longitude +
                '}';
    }
}
