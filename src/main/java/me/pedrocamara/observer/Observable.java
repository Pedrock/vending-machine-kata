package me.pedrocamara.observer;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable<T extends Observable> {
    private final Set<Observer<T>> observers = new HashSet<>();

    public synchronized void addObserver(final Observer<T> observer) {
        this.observers.add(observer);
    }

    public synchronized void deleteObserver(final Observer<T> observer) {
        this.observers.remove(observer);
    }

    @SuppressWarnings("unchecked")
    public void notifyObservers() {
        this.observers.forEach(observer -> observer.update((T)this));
    }
}
