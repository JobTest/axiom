package example.testtask.townsandroads3;

/**
 * Created by alexandr on 04.02.16.
 */
public class CityName implements Cloneable {
    private String name;
    private byte     id;

    public CityName(String name){
        this.name = name;
        this.id   = (byte) name.hashCode();
    }

    public void incId(){
        id = (byte) (id + 1);
    }

    @Override
    public boolean equals(Object o) {
        CityName that = (CityName) o;
        if (id == that.id && name.equals(that.name))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public CityName clone() throws CloneNotSupportedException {
        return (CityName)super.clone();
    }
}

