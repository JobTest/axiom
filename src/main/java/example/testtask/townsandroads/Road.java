package example.testtask.townsandroads;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/20/2016
 */
public class Road {
    private int hashTown1;
    private int hashTown2;

    public Road(int hashTown1, int hashTown2){
        this.hashTown1 = hashTown1;
        this.hashTown2 = hashTown2;
    }

    public int getHashTown1() {
        return hashTown1;
    }
    public void setHashTown1(int hashTown1) {
        this.hashTown1 = hashTown1;
    }
    public int getHashTown2() {
        return hashTown2;
    }
    public void setHashTown2(int hashTown2) {
        this.hashTown2 = hashTown2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Road)) return false;

        Road road = (Road) obj;

        if (hashTown1 != road.hashTown1) return false;
        if (hashTown2 != road.hashTown2) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hashTown1;
        result = 31 * result + hashTown2;
        return result;
    }

    @Override
    public String toString() {
        return "Road{" +
                "hashTown1=" + hashTown1 +
                ", hashTown2=" + hashTown2 +
                '}';
    }
}
