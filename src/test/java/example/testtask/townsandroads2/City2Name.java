package example.testtask.townsandroads2;

/**
 * Created by alexandr on 04.02.16.
 */
class City2Name implements Cloneable {
    private String name;
    private byte     id;

    public City2Name(String name){
        this.name = name;
        this.id   = (byte) name.hashCode();
    }

    public void incId(){
        id = (byte) (id + 1);
    }
    public void incId(byte id){
        this.id = (byte) (this.id + id);
    }

    @Override
    public boolean equals(Object o) {
        City2Name that = (City2Name) o;
        if (id == that.id && name.equals(that.name))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public City2Name clone() throws CloneNotSupportedException {
        return (City2Name)super.clone();
    }

    @Override
    public String toString() {
        return name;
    }
}
