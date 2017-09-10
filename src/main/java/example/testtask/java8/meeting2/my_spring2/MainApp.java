package example.testtask.java8.meeting2.my_spring2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * {@link http://www.javabeat.net/spring-bean-scopes-singleton-prototype/}
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans2.xml");

        System.out.println("*********SINGLETON SCOPE************");
        HelloWorld objA = (HelloWorld) context.getBean("singletonscope");
        objA.setMessage("Message by object 'A'");
        objA.setDate(new Date());
        System.out.println("Your Message: " + objA.getMessage());
        System.out.println("Date:         " + objA.getDate().toString());

        HelloWorld objB = (HelloWorld) context.getBean("singletonscope");
        System.out.println("\nYour Message: " + objB.getMessage());
        System.out.println("Date:         " + objB.getDate().toString());
        System.out.println("********************************\n");

        System.out.println("*********PROTOTYPE SCOPE************");
        HelloWorld objC = (HelloWorld) context.getBean("prototypescope");
        objC.setMessage("Message by object 'C'");
        objC.setDate(new Date());
        System.out.println("Your Message: " + objC.getMessage());
        System.out.println("Date:         " + objC.getDate().toString());

        HelloWorld objD = (HelloWorld) context.getBean("prototypescope");
        System.out.println("\nYour Message: " + objD.getMessage());
        System.out.println("Your Date:    " + objD.getDate());

        System.out.println("********************************\n");
    }
}
