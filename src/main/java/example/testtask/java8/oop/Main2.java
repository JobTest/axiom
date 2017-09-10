package example.testtask.java8.oop;

/**
 * Created by Саша on 22.01.2016.
 */
public class Main2 {

    public static void main(String[] args) {
        System.out.println("--------------------------[1]");
        A a1 = new A();
        B b1 = new B();
        a1.func1();
        a1 = b1;
        a1.func1();
        a1.func3(new C());

        System.out.println("--------------------------[2]");
        A a2 = new C();
        B b2 = new C();
        a2.func1();
        if (a2 instanceof B){
            b2 = (B)a2;
            b2.func1();
            b2.func3(new C());
        }

        /**
         * 1. Создали объект версия реализации 'Chield'
         * +  Есть одинаковые функции, которые перекрываются версией реализации 'Chield'
         * 2. Тип интерфейса - нет (ограничения) НО вместо этого, в конструкторе, пытаемся вызвать конструктор (типа) родительского-класса
         *    И поскольку одинаковые функции перекрываются версией реализации 'Chield', поэтому мы независимо будем получать функцию версии реализации 'Chield'
         */
        System.out.println("--------------------------[3]");
        new Chield();

        System.out.println("--------------------------[4.1]");
        Parent p = new Parent();
        p.test1(new Chield());
        p.test1(new Parent());
//        p.test1(new Object());
        p.test1(new Chield2());
        /**
         * 1. Создали объект версия реализации 'Chield'
         * +  (перегруженные) функции - разные И поэтому принадлежат отдельному (типу) классу версии реализации
         * 2. Тип интерфейса совпадает с версией реализации (нет ограничения)
         * 3. Компилятор находит совпадения функции (название,тип,параметры) в родительском классе
         *    Поэтому будет вызвана функция родительского класса
         */
        System.out.println("--------------------------[4.2]");
        Chield c = new Chield();
        c.test1(new Parent());
        /**
         * 1. Создали объект версия реализации 'Chield'
         * +  (перегруженные) функции - разные И поэтому принадлежат отдельному (типу) классу версии реализации
         * 2. Тип интерфейса ограничивает версиею реализации до 'Parent'
         * 3. Компилятор НЕнаходит совпадения функции, поэтому использует по умолчанию функцию из класса типа интерфейса
         * +  Компилятор проверяет-находит что тип параметра является наследником (НЕродительским классом)
         *    И поэтому компилятор НЕругается на ошибку при компиляции И пропускает - в Runtime будет вызвана функция класса типа 'Parent'
         */
        System.out.println("--------------------------[4.3]");
        Parent p2 = new Chield();
        p.test1(new Chield());
    }

    static class N {}
}


class Parent {
    Parent(){
        func();
    }
    void func(){
        System.out.println("Parent");
    }
    void test1(Parent p){
        System.out.println("Parent test1");
    }
}
class Chield extends Parent {
    Chield(){
        super();
    }
    void func(){
        System.out.println("Chield");
    }
    void test1(Chield c){
        System.out.println("Chield test1");
    }
}
class Chield2 extends Chield {
}


/*
 * внутри интерфейса должны быть абстрактные методы
 * конструкторов интерфейс содержать НЕможет
 * может содержать переменные и константы определение классов
 */
interface M {
    void func();

    int a = 0;
    final int b = 0;
    static int c = 0;

    class Z {
        void f(){}
    }
}
