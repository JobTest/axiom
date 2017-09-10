package example.testtask.java8.meeting2.my_spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CustomerScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        CustomerContext context = resolve();
        Object result = context.getBean(name);
        if (result == null) {
            result = objectFactory.getObject();
            ICustomerScopeBean syncScopedBean = (ICustomerScopeBean) result;
            syncScopedBean.setContext(context);
            Object oldBean = context.setBean(name, result);
            if (oldBean != null) {
                result = oldBean;
            }
        }
        return result;
    }

    @Override
    public Object remove(String name) {
        CustomerContext context = resolve();

        return context.removeBean(name);
    }

    protected CustomerContext resolve() {
        return CustomerContextThreadLocal.getCustomerContext();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return resolve().toString();
    }

}
