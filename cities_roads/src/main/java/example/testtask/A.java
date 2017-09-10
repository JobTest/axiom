package example.testtask;

public class A {
    B objectB;

    public void setObjectB(B objectB) {
        this.objectB = objectB;
    }

    public String methodB(String name) {
        String retVal = objectB.getName(name);
        return retVal;
    }
}
