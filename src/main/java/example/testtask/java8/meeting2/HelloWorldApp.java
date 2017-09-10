package example.testtask.java8.meeting2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  {@link http://www.tutorialspoint.com/spring/spring_bean_scopes.htm}
 ** {@link http://www.tutorialspoint.com/spring/spring_hello_world_example.htm}
 *  {@link http://www.java2s.com/Code/Jar/s/Downloadspring256jar.htm}
 ** {@link http://dev-blogs.com/simple-spring-application-using-autowired-annotation/}
 *  {@link http://www.mkyong.com/spring3/spring-3-hello-world-example/}
 *  {@link http://www.java2blog.com/2012/08/spring-hello-world-example-in-eclipse.html}
 *  {@link http://projects.spring.io/spring-framework/}
 ** {@link http://maven.springframework.org/release/org/springframework/spring/4.2.4.RELEASE/}
 *  (ошибка Exception in thread "main" java.lang.NoClassDefFoundError - Java EE) {@link http://www.cyberforum.ru/java-j2ee/thread528414.html}
 *  *****************
 *  (java jms пример)
 *  {@link https://habrahabr.ru/post/162813/}
 *  {@link http://www.javable.com/columns/serv_side/workshop/14/}
 */
public class HelloWorldApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        HelloWorldService service = (HelloWorldService) context.getBean("service");
        System.out.println( service.getMessage() );
    }
}
