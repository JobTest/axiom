package example.testtask.java8.meeting2.my_spring;

/**
 * Created by alexandr on 16.02.16.
 */
public class CustomerContextThreadLocal {

    private static ThreadLocal<CustomerContext> customerContext = new ThreadLocal();

    public static CustomerContext getCustomerContext() {
        return customerContext.get();
    }

    public static void setSyncContext(CustomerContext context) {
        customerContext.set(context);
    }

    public static void clear() {
        customerContext.remove();
    }

    private CustomerContextThreadLocal() {
    }

    public static void setSyncContext(Customer customer, ReloadType reloadType) {
        setSyncContext(new CustomerContext(customer, reloadType));
    }

}
