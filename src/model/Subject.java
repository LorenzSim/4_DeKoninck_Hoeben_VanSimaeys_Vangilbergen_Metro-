package model;

public interface Subject {
    void addObserver(MetroEventsEnum event, Observer o);
    void removeObserver(MetroEventsEnum event, Observer o);
    void notifyObservers(MetroEventsEnum event);
}
