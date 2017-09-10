package example.testtask;

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
            queue.add(event);
            queue.notify();
        }
    }

    @Override
    public void startHandler() {
        isStopped = false;
        handler   = new Handler();
        handler.start();
    }

    @Override
    public void stopHandler() throws InterruptedException {
        synchronized(queue) {
            isStopped = true;
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
                            } catch (InterruptedException ignored) {
                                System.err.println(ignored.getMessage());
                            }
                        } else {
                            return;
                        }
                    }
                    event = queue.remove();
                }

                try {
                    event.execute();
                } catch (InterruptedException ie) {
                    System.err.println( ie.getMessage() );
                }
            }
        }
    }
}
