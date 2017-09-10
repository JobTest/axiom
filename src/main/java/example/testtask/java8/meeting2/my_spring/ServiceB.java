package example.testtask.java8.meeting2.my_spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ServiceB {

    private Customer customer;
    private ReloadType reloadType;

    public ServiceB(final Customer customer, final ReloadType reloadType) {
        this.customer = customer;
        this.reloadType = reloadType;
    }

    public void doSomethingBoring(){

    }
}
