package example.testtask.java8.meeting.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Преобразование ссылочных типов, или спящий волк на клавиатуре
 * *************************************************************
 ** {@link http://info.javarush.ru/Sant9Iga/2014/01/16/Преобразование-ссылочных-типов-или-спящий-волк-на-клавиатуре.html}
 *
 * (10 ошибок зачастую допускаемых Java разработчиками) http://info.javarush.ru/translation/2014/06/28/10-ошибок-зачастую-допускаемых-Java-разработчиками-.html
 * http://javatalks.ru/topics/35013?page=1#177087
 */
public class Main {

    public static void main(String[] args) {
        // Расширяющее приведение (или неявное)
        Animal animalCat = new Cat();
        Animal animalDog = new YorkshireTerrier();

        // Сужающее приведение (или явное)
        Cat cat = (Cat) animalCat;
        cat.sleepOnKeyboard();
        YorkshireTerrier dog = (YorkshireTerrier) animalDog;
        dog.bark();

        Animal animalCat2 = new Cat();
//        YorkshireTerrier dog2 = (YorkshireTerrier) animalCat2;
        if (animalCat2 instanceof YorkshireTerrier) {
            YorkshireTerrier dog2 = (YorkshireTerrier) animalCat;
            dog2.bark();
        }

        Animal animalDog3 = new YorkshireTerrier();
        if (animalDog3 instanceof YorkshireTerrier) {
            YorkshireTerrier dog3 = (YorkshireTerrier) animalDog3;
            dog3.bark();
        }

//        Animal animalCat3 = new Cat();
//        YorkshireTerrier dog2 = (YorkshireTerrier) animalCat3;
//        Animal dog2 = (YorkshireTerrier) animalCat3;

        List<Animal> allAnimals = new ArrayList<Animal>();
        allAnimals.add(new Cat());
        allAnimals.add(new Wolf());
        allAnimals.add(new Fox());
        allAnimals.add(new YorkshireTerrier());
        for (Animal animal:allAnimals) {
            animal.eat();
            animal.sleep();
        }
    }

}

abstract class Animal {
    String name;
    int age;
    String nameOfClass = getClass().getSimpleName();
    public void eat(){
        System.out.println(nameOfClass + ": Omnomnom");
    }
    public void sleep(){
        System.out.println(nameOfClass + ": Z-z-z-z");
    }
}

abstract class WildAnimal extends Animal {
    public void steelChicken() {
        System.out.println(nameOfClass+": Muhaha,I stole a chicken!");
    }
}

abstract class Pet extends Animal {
    public void peeInTray(){
        System.out.println(nameOfClass + ": Master, I peed");
    }
}

class Fox extends WildAnimal {
    public void eatColobok(){
        System.out.println(nameOfClass + ": I will eat you, Colobok");
    }
}

class Wolf extends WildAnimal {
    public void hawlAtTheMoon(){
        System.out.println(nameOfClass + ": Ouuuuu!!!Ouuuu!!!");
    }
}

class Cat extends Pet {
    public void sleepOnKeyboard(){
        System.out.println(nameOfClass + ": Master, stop working!!I wanna sleep on your keyboard");
    }
}

class YorkshireTerrier extends Pet {
    public void bark(){
        System.out.println(nameOfClass + ": Meow!!! Meow!!!");
    }
}