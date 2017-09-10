package example.testtask.townsandroads;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/20/2016
 */
class Town {
    private double latitude;
    private double longitude;

    public Town(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Town)) return false;

        Town town = (Town) obj;

        if (Double.compare(town.latitude, latitude) != 0) return false;
        if (Double.compare(town.longitude, longitude) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Town{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", hashCode=" + hashCode() +
                '}';
    }
}
