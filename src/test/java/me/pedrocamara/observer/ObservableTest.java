package me.pedrocamara.observer;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


class ObservableTest {

    @Test
    @SuppressWarnings("unchecked")
    void testAddObservable() {
        final Observable observable = new Observable() {};
        final Observer observer = mock(Observer.class);

        observable.addObserver(observer);
        verify(observer, never()).update(observable);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testNotifyObserver() {
        final Observable observable = new Observable() {};
        final Observer observer = mock(Observer.class);

        observable.addObserver(observer);
        observable.notifyObservers();
        verify(observer, times(1)).update(observable);
        observable.notifyObservers();
        verify(observer, times(2)).update(observable);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testDeleteObserver() {
        final Observable observable = new Observable() {};
        final Observer observer = mock(Observer.class);

        observable.addObserver(observer);
        observable.deleteObserver(observer);
        observable.notifyObservers();
        verify(observer, never()).update(observable);
    }
}