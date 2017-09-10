package example.testtask.java8.meeting2.my_spring;

/**
 * Created by alexandr on 16.02.16.
 */
public class AbstractCustomerScopeBean {

    protected Customer customer;
    protected ReloadType reloadType;

//    @Override
    public void setContext(final CustomerContext context) {
        customer = context.getCustomer();
        reloadType = context.getReloadType();
    }

}
