package example.testtask;

public class Road {
    private int       id;
    private String  name;
    private int   idCity1;
    private int   idCity2;
    private double weight;

    public Road(String name, int idCity1, int idCity2){
        this.name  = name;
        this.idCity1 = idCity1;
        this.idCity2 = idCity2;
    }
    public Road(int id, String name, int idCity1, int idCity2){
        this.id    = id;
        this.name  = name;
        this.idCity1 = idCity1;
        this.idCity2 = idCity2;
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
    public int getIdCity1() {
        return idCity1;
    }
    public void setIdCity1(int idCity1) {
        this.idCity1 = idCity1;
    }
    public int getIdCity2() {
        return idCity2;
    }
    public void setIdCity2(int idCity2) {
        this.idCity2 = idCity2;
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
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Road{" +
                "name=" + name +
                " id=" + id +
                " idCity1=" + idCity1 +
                " idCity2=" + idCity2 +
                '}';
    }
}
