package model;

public interface Subject {
    void attach(MetroEventsEnum event, Observer o);
    void detach(MetroEventsEnum event, Observer o);
    void notifyObservers(MetroEventsEnum event);
}
