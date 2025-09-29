/*
Author: Edgar Perez
Assignemnt: Program 2
Class: CSC 2040
 */

package com.opinion.app;

import java.time.Instant;
import java.util.Objects;


//Immutable even object passed to observers
public final class Event {

    private final EventType type;
    private final String message;
    private final Instant timestamp;
    private final Object payload;

    public Event(EventType type, String message, Object payload) {

        this.type = Objects.requireNonNull(type);
        this.message = Objects.requireNonNull(message);
        this.timestamp = Instant.now();
        this.payload = payload;


    }

    public EventType getType() { return type; }
    public String getMessage() { return message; }
    public Instant getTimestamp() { return timestamp; }
    public Object getPayload() { return payload; }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + type + " " + message;
    }






}
