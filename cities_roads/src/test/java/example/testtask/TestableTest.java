package example.testtask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * {@link https://groups.google.com/forum/#!topic/powermock/CEdP24sb_HY}
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Testable.class)
public class TestableTest {
    Testable testable;

    // Verify that public b() is called from a()
    @Test
    public void testB() {
        testable = PowerMockito.spy(new Testable());
        testable.a();
        Mockito.verify(testable).b();
    }
    // Verify that private c() is called from a()
    @Test
    public void testC() throws Exception {
        testable = PowerMockito.spy(new Testable());
        // a() is never called (commented out), and therefore c() should never
        // be called either

        testable.a();
        // And yet, ===> !!!THE TEST PASSES!!! <=== NOT what we want!
        PowerMockito.verifyPrivate(testable).invoke("c");
        // The above line appears to call c() independently and provides no
        // equivalent functionality to Mockito.verify().

        // What am I missing to make this work?
    }
}
