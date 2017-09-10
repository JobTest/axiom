package example.testtask.eventqueue;

/**
 * Created by IntelliJ IDEA.
 * Date: 24.03.2010
 * Time: 16:38:08
 */
public interface EventHandler {
    /**
     * Scheduling new event.
     * @param event
     * @throws Exception In case if handler is not running.  
     */
    void addEvent(MyEvent event) throws Exception;

    /**
     * Starts event handler engine.
     *
     * @throws Exception In case of any error.
     */
    void startHandler() throws Exception;

    /**
     * Stops event handler engine.
     * This method should wait until all scheduled events are complete.
     *
     * @throws Exception In case of any error.
     */
    void stopHandler() throws Exception;
}
