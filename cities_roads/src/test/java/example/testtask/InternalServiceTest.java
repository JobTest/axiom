package example.testtask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.*;

/**
** {@link http://www.coderanch.com/t/639192/Testing/Wanted-invoked-Error}
* {@link https://groups.google.com/forum/#!topic/mockito/ldRgay2kjRE}
** {@link https://habrahabr.ru/post/185834/} >> {@link https://github.com/BorzdeG/ru.itbasis.demo.mock.staticMethod/commits/master}
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ExternalService.class })
public class InternalServiceTest {

    private final ExternalService external = PowerMockito.mock(ExternalService.class);
    private final InternalService internal = new InternalService(external);

    @Before
    public void before() {
        Mockito.reset(external);
    }

    @Test
    public void doWorkTest() {
        internal.doWork();

        Mockito.verify(external).doMegaWork();
    }

    @Test
    public void testMethodA() throws Exception {
        A objectA = new A();
        B mockObjectB = Mockito.mock(B.class);
//          B objectB = new B();

        objectA.setObjectB(mockObjectB);
        Mockito.when(mockObjectB.getName("BCA")).thenReturn("BCA MCA");

        assertEquals("BCA MCA", objectA.methodB("BCA"));
        Mockito.verify(mockObjectB, Mockito.times(1)).getName(Mockito.anyString());
    }

    @Test
    public void testMethodC() throws Exception {
        C objectC = new C();
        B mockObjectB = Mockito.mock(B.class);
//          B objectB = new B();

        objectC.setObjectB(mockObjectB);
        Mockito.when(mockObjectB.getName("BCA")).thenReturn("BCA MCA");

        assertEquals("BCA MCA", objectC.methodB("BCA"));
        Mockito.verify(mockObjectB, Mockito.times(1)).getName(Mockito.anyString());
    }

}

