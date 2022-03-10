package simpleFX;

import javafx.event.Event;

public interface EventHandler<T extends Event> { //interface for the events
void handle(T event);
}