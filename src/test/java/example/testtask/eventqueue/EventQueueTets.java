package example.testtask.eventqueue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/13/2016
 * ********************************
 * The test case for 'EventHandler'
 */
public class EventQueueTets {

    private EventHandler eventQueue;

    @Before
    public void setUp() {
        eventQueue = new EventQueue();
    }

    /**
     * Full test #1
     */
    @Test
    public void testFull() {
        try {
            eventQueue.addEvent(new MyEvent("#10"));
            eventQueue.addEvent(new MyEvent("#11"));
            eventQueue.addEvent(new MyEvent("#12"));

            eventQueue.startHandler();

            eventQueue.addEvent(new MyEvent("#13"));
            eventQueue.addEvent(new MyEvent("#14"));
            eventQueue.addEvent(new MyEvent("#15"));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}
            eventQueue.addEvent(new MyEvent("#16"));
            eventQueue.addEvent(new MyEvent("#17"));
            eventQueue.addEvent(new MyEvent("#18"));

            eventQueue.stopHandler();
        } catch (InterruptedException ie) {
            System.err.println("InterruptedException: "+ie.getMessage());
        } catch (Exception e) {
            System.err.print("Exception: "+e.getMessage());
            System.err.print(e.getStackTrace());
        }
    }

    /**
     * Full test #2
     */
    @Test
    public void testFull2() {
        try {
            eventQueue.addEvent(new MyEvent("#20"));
            eventQueue.addEvent(new MyEvent("#21"));
            eventQueue.addEvent(new MyEvent("#22"));

            eventQueue.startHandler();

            eventQueue.addEvent(new MyEvent("#23"));
            eventQueue.addEvent(new MyEvent("#24"));
            eventQueue.addEvent(new MyEvent("#25"));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {}

            eventQueue.stopHandler();
        } catch (InterruptedException ie) {
            System.err.println("InterruptedException-2: "+ie.getMessage());
        } catch (Exception e) {
            System.err.print("Exception-2: "+e.getMessage());
            System.err.print(e.getStackTrace());
        }
    }

    /**
     * Full test #3
     */
    @Test
    public void testFull3() {
        try {
            eventQueue.startHandler();

            eventQueue.addEvent(new MyEvent("#30"));
            eventQueue.addEvent(new MyEvent("#31"));
            eventQueue.addEvent(new MyEvent("#32"));

            eventQueue.stopHandler();
        } catch (InterruptedException ie) {
            System.err.println("InterruptedException-3: "+ie.getMessage());
        } catch (Exception e) {
            System.err.print("Exception-3: "+e.getMessage());
            System.err.print(e.getStackTrace());
        }
    }
}
