package me.pedrocamara.observer;

public interface Observer<T> {

    void update(T observable);
}
