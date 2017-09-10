package example.testtask.java8;

/**
 * Created by Саша on 19.01.2016.
 */
public class Main4 {

    static {
        System.out.println("static block");
    }


    public static void main(String[] args) {
        /////////////////////////////////////////////////////////////////////
        new Main4(); // >> static block
        Main4.show(); // >> static block - show

        Main4 main4 = new Main4();
        main4.show(); // >> static block - show

        /*
         * можно вызвать статические методы используя переменную ссылающую на NULL (потому что статические методы находяться на уровне самого класса, поэтому можна вызвать статические функции при помощи класса так и при помощи переменной равной NULL)
         */
        Main4 main41 = null;
        main41.show(); // >> static block - show

        /////////////////////////////////////////////////////////////////////
        /*
         * такая конструкция 'a--' делает следующее:
         * 1) первым делом ПОЛУЧАЕМ значение переменной ('a')
         * 2) следующим шагом: выполняем АРИФМЕТИЧЕСКУЮ ОПЕРАЦИЮ ('--')
         * 3) при переходе к следующей инструкции ПЕРЕДАЕТСЯ результат выпонения арифметической операции (>> 'a-1')
         *
         * такая конструкция '--a' делает следующее:
         * 1) первым делом выполняем АРИФМЕТИЧЕСКУЮ ОПЕРАЦИЮ ('--')
         * 2) следующим шагом: результат выпонения арифметической операции ПЕРЕДАЕТСЯ-ПРИСВАИВАЕТСЯ переменной (= 'a')
         * 3) и только после (перехода к следующей инструкции) ПОЛУЧАЕМ значение переменной ('a')
         *
         * такая конструкция 'a-- - --a' делает следующее:
         * 1) первым делом ПОЛУЧАЕМ значение из левого операнда ('a')
         * 2.1) следующим шагом: выполняем АРИФМЕТИЧЕСКУЮ операцию в левом операнде ('--')
         * 2.2) следующим шагом: из результата операции полученнго из левого операнда выполняем АРИФМЕТИЧЕСКУЮ операцию в правом операнде И результат передаем-присваиваем в переменную. В итоге получаем результат для правого операнда (>> 'a-1' '--' '=')
         * 3) итак уже имеем значение левого и правого операнда. Теперь выполняем локальную операцию между левым и правым операндом ('a' '-' 'a-2')
         *
         * такая конструкция '--a - a--' делает следующее:
         * 1) выполняем АРИФМЕТИЧЕСКУЮ операцию в левом операнде ('--') И результат передаем-присваиваем в переменную. В итоге получаем результат для левого операнда (= 'a-1')
         * 2) ПОЛУЧАЕМ значение в правом операнде - оно будет равно значению левого операнда ('a')
         * 3) выполняем АРИФМЕТИЧЕСКУЮ операцию между левым и правым операндом - то есть разница между двумя одинаковыми значениями ('-') (= 0)
         */
        int a = 1111;
        a = a-- - --a; // = 2
        System.out.println(a);
        int b = 100;
        System.out.println("1) " + b--);
        System.out.println("1) " + b);
        System.out.println("2) " + --b);
        System.out.println("2) " + b);
        b = 100;
        b = b-- - --b; // = 2
        System.out.println("3) " + b);
        b = 100;
        b = --b - b--; // = 0
        System.out.println("4) " + b);
    }


    public static void show(){
        System.out.println("static block - show");
    }
}
