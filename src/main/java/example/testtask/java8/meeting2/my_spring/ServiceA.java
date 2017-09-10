package example.testtask.java8.meeting2.my_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("prototype")
public class ServiceA {
    private Customer customer;
    private ReloadType reloadType;

    private ServiceB serviceB;

    @Autowired
    private ApplicationContext context;

    public ServiceA(final Customer customer, final ReloadType reloadType) {
        this.customer = customer;
        this.reloadType = reloadType;
    }

    @PostConstruct
    public void init(){
        serviceB = (ServiceB) context.getBean("serviceB",customer, reloadType);
    }

    public void doSomethingInteresting(){
        doSomthingWithCustomer(customer,reloadType);
        serviceB.doSomethingBoring();
    }

    private void doSomthingWithCustomer(final Customer customer, final ReloadType reloadType) {

    }
}
