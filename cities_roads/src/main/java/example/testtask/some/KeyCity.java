package example.testtask.some;

public class KeyCity {
    private String name;
    private byte     id;

    public KeyCity(String name){
        this.name = name;
        id        = (byte) name.hashCode();
    }

    public void incId(){
        id = (byte) (id + 1);
    }

    @Override
    public boolean equals(Object key) {
        KeyCity that = (KeyCity) key;
        if (id == that.id && name.equals(that.name))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
