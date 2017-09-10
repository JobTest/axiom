package example.testtask;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * {@link https://habrahabr.ru/company/luxoft/blog/157273/}
 * {@link https://habrahabr.ru/post/131240/}
 *
 * {@link https://habrahabr.ru/post/172239/}
 * {@link https://habrahabr.ru/post/243155/}
 ** {@link http://www.slideshare.net/khayrutdinov/java-mocking-frameworks-mockito-and-powermock}
 ** {@link https://groups.google.com/forum/#!topic/powermock/CEdP24sb_HY}
 * {@link https://habrahabr.ru/post/72617/}
 * {@link https://www.slideshare.net/mobile/nunafig/mockito-12079903}
 * {@link https://habrahabr.ru/company/luxoft/blog/157273/}
 *
 * http://stackoverflow.com/questions/26921367/how-to-use-powermockito-to-verify-super-method-is-called
 * http://www.gitshah.com/2010/06/how-to-supress-methods-and-constructors.html
 * http://stofl-ru.com/questions/23865555/how-to-mock-super-reference-on-super-class
 * https://javatalks.ru/topics/32508?page=2
 * http://rsdn.ru/forum/java/1818244.all
 ** https://habrahabr.ru/post/185834/
 *** http://www.slideshare.net/khayrutdinov/java-mocking-frameworks-mockito-and-powermock
 * https://habrahabr.ru/post/172239/
 * http://www.johnmullins.co/blog/2015/02/15/beginners-guide-to-using-mockito-and-powermockito-to-unit-test-java/
 ** https://groups.google.com/forum/#!topic/powermock/CEdP24sb_HY
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ServiceSome.class })
public class ServiceSomeTest {

    private ServiceHand service;
    private ServiceSome actualAddMock;
    private ServiceSome actualAdd;

    @Before
    public void setUp() {
        service = mock(ServiceSome.class);

        /*
         * Но пока реального класса у меня несуществует - я прописываю МОК (заглушки) на свои тестируемые методы
         * - и все одинаково работает (от начала написания интерфейсов до их реализации...)!
         */
        actualAddMock = mock(ServiceSome.class);
        when(actualAddMock.addCity("City-A", 100, 100)).thenReturn(true);
        when(actualAddMock.addCity("City-A", 100, 100)).thenReturn(true);
        doThrow(new NullPointerException("City-Name is Null")).when(actualAddMock).addCity(null, 100, 100);

        actualAdd = PowerMockito.spy(new Service());
        actualAdd.addCity("City-A", 100, 100);
        actualAdd.addCity("City-B", 200, 200);
        actualAdd.addCity("City-C", 300, 300);
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void addCityTrue() {
//        when(service.addCity(null, 200, 200)).thenReturn(false);
//        when(service.addCity("City-A", 100, 100)).thenReturn(true).thenReturn(false);
//
//        assertFalse("addCity(null, 200, 200)", service.addCity(null, 200, 200));
//        assertTrue("addCity(\"City-A\", 100, 100)", service.addCity("City-A", 100, 100));
//        assertFalse("addCity(\"City-A\", 100, 100)", service.addCity("City-A", 100, 100));
//    }

    @Test
    public void addCityTrue() {
        assertTrue("addCity(\"City-A\", 100, 100)", actualAddMock.addCity("City-A", 100, 100));
    }

//    @Test(expected=NullPointerException.class)
//    public void addCityException1() {
//        doThrow(new NullPointerException()).when(service).addCity(null, 300, 300);
//
//        service.addCity(null, 300, 300);
//    }

//    @Test(expected=NullPointerException.class)
//    public void addCityException2() {
//        actualAdd.addCity(null, 100, 100);
//    }

    @Test
    public void addCityException() {
        /*
         * Это обычный JUnit-тест который я пишу на уже реальный класс...
         */
        String expected = "City-Name is Null";
        try {
            actualAddMock.addCity(null, 100, 100);
            fail();
        } catch(NullPointerException npe) {
            assertEquals("addCity(null, 100, 100)", expected, npe.getMessage());
        }
    }

    @Test
    public void addCityMethodCall() {
        service.addCity(null, 300, 300);

        verify(service).addCity(null, 300, 300);
    }

    @Test
    public void testIsRoad() {
//        actualAdd.addRoad("Road-0", actualAdd.getCity(0), actualAdd.getCity(2));
        actualAdd.addRoad("Road-0", new City(0, "City-A", new Point(100, 100)), new City(2, "City-C", new Point(300, 300))); // java.lang.ArrayIndexOutOfBoundsException: 0

        Mockito.verify(actualAdd).isRoad(0, 2);
    }
}
