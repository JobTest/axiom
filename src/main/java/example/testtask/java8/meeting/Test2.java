package example.testtask.java8.meeting;

/**
 * Created by Саша on 19.01.2016
 * * * * * * * * * * * * * * * *
 * {@link http://ru.stackoverflow.com/questions/279518/%D0%9F%D0%BE%D0%BB%D0%B8%D0%BC%D0%BE%D1%80%D1%84%D0%B8%D0%B7%D0%BC-%D0%B2-java}
 * Изучая полиморфизм в Java натолкнулся на такой пример:
 */
public class Test2 {
    public static void main(String[] args) {
        A a = new C();
        a.m1(); // согласно хитрому полиморфизму, выполняется поиск метода с таким-же названием сперва в классе наследнике...двигаясь к родительсколму классу
        a.m1(new B()); // проблема в том что метод, в классах наследниках, является перегруженным - поэтому это уже разные методы
                       // а согласно хитрому полиморфизму нужно чтобы методы были абсолютно идентичны КРОМЕ области видимости...
                       // и поэтому здесь остаются чисто законы интерфейсов...по которым доступен только метот типа интерфейса
                       /// хотя в классе типа интерфейса тоже нет метода с такими параметрами - дальше (после проверки полиморфизма позднего связывания) компилятор выполняет приведения типов в классе типа интерфейса!
//        a.m2(12l);

        Test2 test2 = new Test2();
        Object obj = test2.getObject(new A());
    }

    public Object getObject(Object o) {
        return o;
    }
}


class A {
    void m1(A a) {
        System.out.println("A");
    }
    void m1(){
        System.out.println("A2");
    }
    void m2(int i) {
        System.out.println("A3");
    }
}

class B extends A {
    void m1(B b) {
        System.out.println("B");
    }
    void m1(){
        System.out.println("B2");
    }
    void m2(long l) {
        System.out.println("B3");
    }
}

class C extends B {
    void m1(B b) {
        System.out.println("C");
    }
    void m1(){
        System.out.println("C2");
    }
    void m2(float f) {
        System.out.println("C3");
    }
}
