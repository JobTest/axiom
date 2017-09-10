package example.testtask.townsandroads3;

/**
 * Created by Саша on 07.02.2016.
 */
public class Road {
    private String name;
    private String city1;
    private String city2;

    public Road(String name, String city1, String city2){
        this.name      = name;
        this.city1 = city1;
        this.city2 = city2;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity1() {
        return city1;
    }
    public void setCity1(String city1) {
        this.city1 = city1;
    }
    public String getCity2() {
        return city2;
    }
    public void setCity2(String city2) {
        this.city2 = city2;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name=" + name +
                " city1=" + city1 +
                " city2=" + city2 +
                '}';
    }
}
