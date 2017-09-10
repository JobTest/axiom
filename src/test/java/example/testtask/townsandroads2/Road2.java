package example.testtask.townsandroads2;

public class Road2 {

    private String name;
    private Integer numCity1;
    private Integer numCity2;

    public Road2(String name, Integer numCity1, Integer numCity2){
        this.name = name;
        this.numCity1 = numCity1;
        this.numCity2 = numCity2;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getNumCity1() {
        return numCity1;
    }
    public void setNumCity1(Integer numCity1) {
        this.numCity1 = numCity1;
    }
    public Integer getNumCity2() {
        return numCity2;
    }
    public void setNumCity2(Integer numCity2) {
        this.numCity2 = numCity2;
    }

    @Override
    public String toString() {
        return "Road2{" +
                "name='" + name +
                ", numCity1=" + numCity1 +
                ", numCity2=" + numCity2 +
                '}';
    }
}
