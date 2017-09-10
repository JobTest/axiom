package example.testtask.java8.meeting.my_spring;

import example.testtask.java8.meeting2.my_spring.ICustomerScopeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = {"classpath:CustomerScope.xml"}, loader = GenericXmlContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerBeanScopeTest {

    @Autowired
    private AbstractApplicationContext context;

    @Test
    public void testScopeBeans() throws ClassNotFoundException {

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDef : beanDefinitionNames) {
            BeanDefinition def = beanFactory.getBeanDefinition(beanDef);
            String scope = def.getScope();
            String beanClassName = def.getBeanClassName();
            if (beanClassName == null)
                continue;
            Class<?> aClass = Class.forName(beanClassName);
            if (ICustomerScopeBean.class.isAssignableFrom(aClass))
                assertTrue(beanClassName + " should have scope 'customer'", scope.equals("customer"));
            if (scope.equals("customer"))
                assertTrue(beanClassName + " should implement 'ICustomerScopeBean'", ICustomerScopeBean.class.isAssignableFrom(aClass));
        }
    }
}
