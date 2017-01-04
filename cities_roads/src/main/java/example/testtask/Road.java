package example.testtask;

public class Road {
    private int       id;
    private String  name;
    private City   city1;
    private City   city2;
    private double weight;

    public Road(String name, City city1, City city2){
        this.name  = name;
        this.city1 = city1;
        this.city2 = city2;
    }

    Road(int id, String name, City city1, City city2){
        this.id  = id;
        this.name  = name;
        this.city1 = city1;
        this.city2 = city2;
    }

    public Road(String name, City city1, City city2, double weight){
        this.name  = name;
        this.city1 = city1;
        this.city2 = city2;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;
        if (!(that instanceof Road))
            return false;
        Road road = (Road) that;
        if (!name.equals(road.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name=" + name +
                " id=" + id +
                " idCity1=" + city1.getId() +
                " idCity2=" + city2.getId() +
                '}';
    }
}
