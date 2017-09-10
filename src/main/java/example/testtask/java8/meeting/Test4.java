package example.testtask.java8.meeting;

/**
 * http://info.javarush.ru/translation/2014/06/28/10-ошибок-зачастую-допускаемых-Java-разработчиками-.html
 * http://info.javarush.ru/Sant9Iga/2014/01/16/Преобразование-ссылочных-типов-или-спящий-волк-на-клавиатуре.html
 * http://javatalks.ru/topics/35013?page=1#177087
 * http://sernam.ru/book_java.php?id=7
 * http://www.cyberforum.ru/java-j2se/thread240949.html
 * http://www.ccfit.nsu.ru/~deviv/courses/oop/tij2nd/Chapter07.html
 * http://www.slideshare.net/coxx/api-3091654
 * http://jsehelper.blogspot.com/2016/01/blog-post_59.html
 *
 *
 * В ООП существует два правила для функций с одинаковыми именами:
 * 0. функции с одинаковыми именами могут иметь тлько один тип (то есть, в одном классе НЕможет быть несколько функций с одинаковыми именами но разными типами ДАЖЕ если это класс-наследник)
 * 1. переопределение - это изменение реализации для методов в классах-наследниках
 * 2. перегрузка - это изменения передаваемых параметров (как угодно) в методе, независимо в каком классе он находится
 *
 * Приведение типов - это автоматизированная процедура которая выполняется в двух случаях - либо явно; либо автоматически;
 *                    внутри каждого типа структуры есть методы со специальным названием
 * ****************
 * 0.1 Выполняется только для идентичных методов (для функций с одинаковыми именами и параметрами И типами...инначе ошибка компиляции)
 * 0.2 Класс должен являться наследником, который унаследовал все методы своего родителя (причем родитель у класса-наследника может быть только один)
 * 0.3 Итак при этих условиях, компилятор ищет одинаковые методы в классе исходного типа и подменяет эти методы в классе конечного типа...
 *
 */
public class Test4 {

    public static void main(String[] args) {
        System.out.println("------------------[1]");
        B4_1 b = new B4_1();
        b.func1(new A4_1());
        b.func1(new B4_1());
        b.func2(new B4_1());

        System.out.println("------------------[2]");

//        B4_2 x2 = new A4_2();       // ошибка компиляции
//        B4_2 x2 = (B4_2)new A4_2(); // ClassCastException
        A4_2 x2 = new B4_2();
        x2.func1();
        x2.func2();

        System.out.println("------------------[3]");
        A4_3 x3 = new B4_3();
        x3.func1();
        x3.func2();

//        System.out.println("------------------[7]");
//        A4_7 a7_1 = new A4_7();
//        B4_7 b7_1 = new B4_7();
//        C4_7 c7_1 = new C4_7();
//        A4_7 a7_2 = new A4_7();
//        C4_7 c7_2 = new C4_7();
////        a7_1 = (A4_7)b7_1; // ошибка компиляции
//        a7_1.func1();
////        a7_1 = c7_1;
//        a7_1 = (C4_7)c7_1;
////        a7_1 = (A4_7)c7_1; // отсекает методы
//        a7_1.func1();
////        a7_1.
//        c7_1 = (C4_7)a7_1;
//        c7_1.func1();
////        c7_2 = (C4_7)a7_2; // ClassCastException
////        c7_2.func(); // ClassCastException

//        System.out.println("------------------[7]");
//        A4_7 a7_1 = new A4_7();
//        a7_1.func1();
////        A4_7 a7_2 = (C4_7)new A4_7(); // ClassCastException
//        A4_7 a7_2 = new A4_7();
//        C4_7 c7_2 = new C4_7();
//        a7_2.func1();
//        c7_2.func3();
//        A4_7 a7_3 = new A4_7();
//        C4_7 c7_3 = new C4_7();
//        a7_3.func1();
//        c7_3 = (C4_7) a7_3;
////        c7_3 = (C4_7)a7_3;
////        c7_3.func1();

        System.out.println("------------------[7.1]");
        A4_7 a7_1 = new A4_7();
        C4_7 c7_1 = new C4_7();

//        C4_7 c7_2 = (C4_7)a7_1; // компилятор пропустит такой код, а вот RunTime выкинет ClassCastException
        if (a7_1 instanceof C4_7) {
            C4_7 c7_2 = (C4_7)a7_1;
            c7_2.func1();
        }

        System.out.println("------------------[7.2]");
        A4_7 a72_1 = new A4_7();
        a72_1.func1();
        C4_7 c72_1 = new C4_7();
        c72_1.func1();
        a72_1 = c72_1;

        a72_1.func1();
        A4_7 a72_2 = new C4_7();
        a72_2.func1();
        /**
         * > В любом случае, компилятор создаст объект тип которого будет соответствовать версии реализации...
         *   И будут использоваться все методы именно этого типа класса (по умолчанию)
         * > Но тип ссылочной переменной (тип интерфейса) будет (ниже по иерархии наследования) ограничивать видимость к функциям соответственно указаному типу класса - то есть, это есть интерфейсные функции
         * > При наследовании нужно жестко соблюдать два правила: переопределение и перегрузка (если одноименные функции будут иметь разный тип даже в классах наследниках - это ошибка на уровне компилятора)
         * > Модификаторы доступа ограничивают область видимости
         */

        /**
         * это обычный способ объявления...
         * версия реализации совпадает с типов интерфейса
         */
        System.out.println("------------------[8.1]");
        A4_8 a8_1 = new A4_8();
        B4_8 b8_1 = new B4_8();
        C4_8 c8_1 = new C4_8();
        a8_1.func1();
        b8_1.func1();
        c8_1.func1();
        /**
         * такой способ объявления предоставляет самую последнюю версию реализации методов НО ограничивает их видимость по типу интерфейса
         * (поскольку все методы идентичны)
         */
        System.out.println("------------------[8.2]");
        A4_8 a8_2 = new C4_8();
        B4_8 b8_2 = new C4_8();
        C4_8 c8_2 = new C4_8();

        a8_2.func1();
        b8_2.func1();
        c8_2.func1();

        a8_2.func3(new A4_8());
        b8_2.func3(new B4_8());
        c8_2.func3(new C4_8());
        /**
         * каждая версия реализации имеет разные функции (переопределенные) И поэтому здесь НЕбудет предоставлено самая последняя версия реализации - то есть, будет ограничения по типу интерфейса
         * здесь есть методы (НИЖЕ тип интерфейса....)
         */
        System.out.println("------------------[8.3]");
        A4_8 a8_3 = new C4_8();
        B4_8 b8_3 = new C4_8();
        C4_8 c8_3 = new C4_8();

        a8_3.func2(new A4_8());
        a8_3.func2(new B4_8());
        a8_3.func2(new C4_8());

        b8_3.func2(new A4_8());
        b8_3.func2(new B4_8());
        b8_3.func2(new C4_8());

        c8_3.func2(new A4_8());
        c8_3.func2(new B4_8());
        c8_3.func2(new C4_8());
    }

}


class A4_1 {
}
class B4_1 extends A4_1 {
    public void func1(A4_1 a){
        System.out.println("A4_1");
    }
    public void func1(B4_1 b){
        System.out.println("B4_1");
    }

    public void func2(A4_1 x){
        System.out.println("X");
    }
}

class A4_2 {
    public void func1(){
        System.out.println("A4_2 func1");
    }
    public void func2(){
        System.out.println("A4_2 func2");
    }
}
class B4_2 extends A4_2 {
    public void func1(){
        System.out.println("B4_2 func1");
    }
    public void func2(){
        System.out.println("B4_2 func2");
    }
}

class A4_3 {
    public A4_3(){
        System.out.println("A4_3");
    }
    public void func1(){
        System.out.println("A4_3 func1");
    }
    public void func2(){
        System.out.println("A4_3 func2");
    }
}
class B4_3 extends A4_3 {
    public B4_3(){
        super();
        System.out.println("B4_3");
    }
    public void func1(){
        System.out.println("B4_3 func1");
    }
    public void func2(){
        System.out.println("B4_3 func2");
    }
}

// Ошибка компиляции:
//class A4_5 {
//    public int func(){
//        return 0;
//    }
//    public long func(){
//        return 0;
//    }
//}

// Ошибка компиляции:
//class A4_6 {
//    public int func(){
//        return 0;
//    }
//}
//class B4_6 extends A4_6 {
//    public String func(){
//        return null;
//    }
//}

class A4_7 {
    public int func1(){
        System.out.println("A4_7 func1");
        return 0;
    }
}
class B4_7 {
    public int func1(){
        System.out.println("B4_7 func1");
        return 0;
    }
    public int func2(){
        System.out.println("B4_7 func2");
        return 0;
    }
}
class C4_7 extends A4_7 {
    public int func1(){
        System.out.println("C4_7 func1");
        return 0;
    }
    public int func2(){
        System.out.println("C4_7 func2");
        return 0;
    }
    public int func3(){
        System.out.println("C4_7 func3");
        return 0;
    }
}

class A4_8 {
    public void func1(){
        System.out.println("A4_8 func1");
    }
    public void func2(A4_8 a){
        System.out.println("A4_8 func2");
    }
    public void func3(A4_8 a){
        System.out.println("A4_8 func3");
    }
}
class B4_8 extends A4_8 {
    public void func1(){
        System.out.println("B4_8 func1");
    }
    public void func2(B4_8 b){
        System.out.println("B4_8 func2");
    }
    public void func3(A4_8 a){
        System.out.println("B4_8 func3");
    }
}
class C4_8 extends B4_8 {
    public void func1(){
        System.out.println("C4_8 func1");
    }
    public void func2(C4_8 c){
        System.out.println("C4_8 func2");
    }
    public void func3(A4_8 a){
        System.out.println("C4_8 func3");
    }
}