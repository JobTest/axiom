package example.testtask.java8.meeting2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Саша on 16.02.2016.
 */

@Service
@Scope("prototype")
public class HelloWorldService {
    private String message;
//    private int        num;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
//    public void setNum(int num){
//        this.num = num;
//    }

//    public void getMessage(){
//        System.out.println("Your Message: ("+num+") " + message);
//    }
}
