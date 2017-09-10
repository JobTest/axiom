package example.testtask.townsandroads3;

/**
 * Created by Саша on 07.02.2016.
 */
public class RoadName implements Cloneable {
    private String city1;
    private String city2;
    private byte     id;

    public RoadName(String city1){
        this.city1 = city1;
        this.id   = (byte) city1.hashCode();
    }
    public RoadName(String city1, String city2){
        this.city1 = city1;
        this.city2 = city2;
        this.id   = (byte) city1.hashCode();
    }

    public void incId(){
        id = (byte) (id + 1);
    }

    @Override
    public boolean equals(Object o) {
        RoadName that = (RoadName) o;

//        if (city2==null){ //if (city2==null || that.city2==null){
            if (city1.equals(that.city1) && id==that.id)
                return true;
//        } else {
//            if (city1.equals(that.city1) && city2.equals(that.city2))
//                return true;
//        }
        return false;
    }

    @Override
    public int hashCode() {
        return city1.hashCode();
    }

    @Override
    public RoadName clone() throws CloneNotSupportedException {
        return (RoadName)super.clone();
    }
}
