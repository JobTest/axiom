package example.testtask.townsandroads2;

public class Road2Name {
    private Integer numRoad;

    public Road2Name(Integer numRoad){
        this.numRoad = numRoad;
    }

    public Integer getNumRoad() {
        return numRoad;
    }
    public void setNumRoad(Integer numRoad) {
        this.numRoad = numRoad;
    }

    @Override
    public boolean equals(Object o) {
        Road2Name that = (Road2Name) o;
        if (numRoad == that.numRoad) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return numRoad.hashCode();
    }

    @Override
    public String toString() {
        return "Road2Name{" +
                "numRoad=" + numRoad +
                '}';
    }
}
