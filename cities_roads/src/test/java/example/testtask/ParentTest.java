//package example.testtask;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//
///**
// * {@link http://stofl-ru.com/questions/23865555/how-to-mock-super-reference-on-super-class}
// */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Parent.class)
//public class ParentTest {
//
//    @Test
//    public void testSave() {
//        PowerMockito.suppress(PowerMockito.methodsDeclaredIn(Parent.class));
//
//        Child child = new Child();
//        System.out.println(child.save());
//    }
//
//}
//
