package com.app.thenhpattern.util;

public class Event<T> {

    private T content;
    boolean hasBeenHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public T getContentIfNotHandled(){
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public T peekContent(){
        return content;
    }

}
