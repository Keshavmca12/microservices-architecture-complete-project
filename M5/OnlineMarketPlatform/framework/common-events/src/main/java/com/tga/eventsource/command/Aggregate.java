package com.tga.eventsource.command;

import com.tga.eventsource.event.Event;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
public abstract class Aggregate<T, CT> {

    public abstract String getId();
    public abstract void setId(String id);

    /**
     * Apply an event by invoking an apply() method whose parameter class matches the event's class
     *
     * @param event the event representing the state change
     * @return a list (state changing)  events
     */
    public List<Event> applyEvent(Event event) {
        try {
           return (List<Event>) getClass().getMethod("apply", event.getClass()).invoke(this, event);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Event Processing Failed.");
            // throw new EventuateApplyEventFailedUnexpekctedlyException(e.getCause());
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
            log.error("There is no event handler for the event type : {} in the aggregate class: {}", event.getClass().getName(), this.getClass().getName());
            // throw new RuntimeException("Event Processing Failed.");
            // throw new MissingApplyMethodException(e, event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Event Processing Failed.");
            // throw new EventuateApplyEventFailedUnexpectedlyException(e);
        }
        return List.of();
    }

    /**
     * Processes a command by invoking a process() method whose parameter class matches the command's class
     * @param cmd the command to process
     * @return a list (state changing) command events
     */
    public List<Event> processCommand(CT cmd) {
        try {
            return (List<Event>) getClass().getMethod("process", cmd.getClass()).invoke(this, cmd);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Command Processing Failed.");
           // throw new EventuateCommandProcessingFailedException(e.getCause());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Command Processing Failed.");
            // throw new MissingProcessMethodException(e, cmd);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Command Processing Failed.");
            // throw new EventuateCommandProcessingFailedUnexpectedlyException(e);
        }
    }
}
