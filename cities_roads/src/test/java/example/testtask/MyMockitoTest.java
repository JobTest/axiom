package example.testtask;

import org.hamcrest.BaseMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Description;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MyMockitoTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void iterator_will_return_hello_world() {
        /*
         * mock(class) - Чтобы создать заглушку (мок)
         * when(mock).thenReturn(value) - чтобы указать возвращаемое значение для метода
         *
         * Этот пример демонстрирует создание мок-итератора и «заставляет» его возвращать «Hello» + «World»
         */
        //подготавливаем
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //выполняем
        String result = i.next()+" "+i.next();
        //сравниваем
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments() {
        /*
         * Заглушки также могут возвращать различные значения в зависимости от передаваемых в метод аргументов
         *
         * Здесь мы создаём объект-заглушку Comparable, и возвращаем 1 в случае, если он сравнивается с определённым String-значением «Test»
         */
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        assertEquals(1, c.compareTo("Test"));
    }

    @Test
    public void with_unspecified_arguments() {
        /*
         * anyInt() - Если метод имеет какие-то аргументы но Вам всё равно что будет в них передано или предсказать это невозможно
         *
         * Эта заглушка возвращает -1 вне зависимости от переданного аргумента
         */
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, c.compareTo(5));
    }

    @Test(expected=IOException.class)
    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream() throws IOException {
        /*
         * Void-методы составляют некоторую проблему, так как Вы не можете использовать их в методе when()
         * Альтернативой в этой ситуации будет doReturn(result).when(mock_object).void_method_call();
         * Вместо возврата результата Вы также можете использовать .thenThrow() или .doThrow() для void-методов
         *
         * В этом примере выбрасывается IOException, когда в заглушке OutputStream вызывается метод close
         * (проверяем, что OutputStreamWriter пробрасывает такой эксепшн наружу)
         */
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        doThrow(new IOException()).when(mock).close();
        osw.close();
    }

    @Test
    public void OutputStreamWriter_Closes_OutputStream_on_Close() throws IOException {
        /*
         * verify(mock_object).method_call - Чтобы проверить, что метод действительно был вызван
         *
         * В этом примере мы проверяем, что OutputStreamWriter совершает вызов метода close() во вложенном OutputStream
         */
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }

    @Test
    public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream() throws IOException {
        /*
         * eq(value) - для конвертирования литерала в матчер, который сравнит значение
         *
         * К примеру, OutputStreamWriter будет буферизовать вывод и затем передавать его обёрнутому объекту при заполнении буфера, но мы не знаем, насколько длинный буфер нам собираются передать
         * (Здесь мы не можем использовать сравнение на равенство) Однако, мы можем запилить собственный матчер
         */
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.write('a');
        osw.flush();
        // не можем делать так, потому что мы не знаем,
        // насколько длинным может быть массив
        // verify(mock).write(new byte[]{'a'}, 0, 1);

        BaseMatcher<byte[]> arrayStartingWithA = new BaseMatcher<byte[]>() {
            @Override
            public void describeTo(Description description) {
                // пустота
            }
            // Проверяем, что первый символ - это A
            @Override
            public boolean matches(Object item) {
                byte[] actual = (byte[]) item;
                return actual[0] == 'a';
            }
        };
        // проверяем, что первый символ массива - это A, и что другие два аргумента равны 0 и 1.
        verify(mock).write(argThat(arrayStartingWithA), eq(0), eq(1));
    }
}
