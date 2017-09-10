package example.testtask.java8.meeting2.my_spring;

/**
 * Created by alexandr on 16.02.16.
 */
public class CustomerContext implements ICustomerScopeBean {
    private Customer customer;
    private ReloadType reloadType;

    public CustomerContext(){
        customer = new Customer();
    }
    CustomerContext(Customer customer, ReloadType reloadType){
        this.customer = customer;
        this.reloadType = reloadType;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public ReloadType getReloadType() {
        return reloadType;
    }
    public void setReloadType(ReloadType reloadType) {
        this.reloadType = reloadType;
    }

    @Override
    public void setContext(CustomerContext context) {

    }

    public Object getBean(String name){
        return customer;
    }
    public Object setBean(String name, Object result){
        customer.setName(name);
        return customer;
    }
    public Object removeBean(String name){
        return customer;
    }
}
