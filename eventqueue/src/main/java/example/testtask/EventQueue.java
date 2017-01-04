package example.testtask;

import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Kmets
 * @version 1.0
 * @date 01/13/2016
 * ********************************
 * The implements for 'EventHandler'
 */
public class EventQueue implements EventHandler {
    private static final Logger logger = Logger.getLogger(EventQueue.class);

    private final Deque<MyEvent> queue;
    private Handler            handler;
    private boolean          isStopped;

    public EventQueue(){
        isStopped = true;
        queue = new ArrayDeque();
    }

    @Override
    public void addEvent(MyEvent event) throws Exception {
        synchronized(queue) {
            logger.debug("'" + event + "';");
            queue.add(event);
            queue.notify();
        }
    }

    @Override
    public void startHandler() {
        isStopped = false;
        logger.warn("'isStopped=" + isStopped + "';");
        handler   = new Handler();
        handler.start();
    }

    @Override
    public void stopHandler() throws InterruptedException {
        synchronized(queue) {
            isStopped = true;
            logger.warn("'isStopped=" + isStopped + "';");
            queue.notify();
        }
    }

    private class Handler extends Thread {
        @Override
        public void run() {
            MyEvent event;
            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        if (!isStopped) {
                            try {
                                queue.wait();
                            } catch (InterruptedException ie) {
                                logger.debug("'(ignored) " + ie.getMessage() + "';");
                            }
                        } else {
                            return;
                        }
                    }
                    event = queue.remove();
                }

                try {
                    event.execute();
                    logger.debug("'" + event + "';");
                } catch (InterruptedException ie) {
                    logger.debug("'" + ie.getMessage() + "';");
                }
            }
        }
    }
}
