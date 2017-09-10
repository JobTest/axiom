package example.testtask;

public class C extends B {

    B objectB;

    void setObjectB (B objectB){
        this.objectB = objectB;
    }

    public String methodB(String name) {
//        B objectB = new B();
        String retVal = objectB.getName(name);
        return retVal;
    }
}
