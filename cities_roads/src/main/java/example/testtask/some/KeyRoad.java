package example.testtask.some;

public class KeyRoad {
    private int idCity;
    private byte    id;

    public KeyRoad(int idCity){
        this.idCity = idCity;
        id          = (byte) idCity;
    }

    public void incId(){
        id = (byte) (id + 1);
    }

    @Override
    public boolean equals(Object key) {
        KeyRoad that = (KeyRoad) key;
        if (id == that.id && idCity == that.idCity)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return idCity;
    }
}
