//package example.testtask;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * {@link http://stofl-ru.com/questions/23865555/how-to-mock-super-reference-on-super-class}
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Bar.class)
//public class FooTest {
//
//    @Test
//    public void testBar() throws Exception {
//        Method method = PowerMockito.method(Bar.class, "bar");
//        PowerMockito.suppress(method);
//
//        Foo foo = new Foo();
//        foo.bar();
//    }
//
//    @Test
//    public void testNumBar() throws Exception {
//        Method methodFoo = PowerMockito.method(Foo.class, "numBar");
//        PowerMockito.replace(methodFoo).with(new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return 1;
//            }
//        });
//
//        Foo foo = new Foo();
//        int numBar = foo.numBar();
//
//        assertEquals(1, numBar);
//    }
//}
