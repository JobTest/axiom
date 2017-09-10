//package example.testtask;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.modules.junit4.PowerMockRunner;
//
//import java.lang.reflect.Method;
//
///**
// * {@link http://stackoverflow.com/questions/26921367/how-to-use-powermockito-to-verify-super-method-is-called}
// */
//@RunWith(PowerMockRunner.class)
//public class HumanTest {
//
//    @Test
//    public void test() throws NoSuchMethodException, SecurityException {
//        // 1. arrange
//        Human sut = PowerMockito.spy(new Human());
////        PowerMockito.doNothing().when((SuperHuman) sut).run(); // SuperHuman is the parent class
//        Method toReplace = PowerMockito.method(SuperHuman.class, "run");
//        PowerMockito.suppress(toReplace);
//
//
//        // 2. action
//        sut.run();
//
//        // 3. assert / verify
//        Mockito.verify(sut).run();
//    }
//}
//
