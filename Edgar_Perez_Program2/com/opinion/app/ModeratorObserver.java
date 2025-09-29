/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
 */

package com.opinion.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//Colect all event to an in-memory list
//simulate a mod who reviews changes
public class ModeratorObserver implements DataStoreObserver{

    private final List<Event> events = new ArrayList<>();

    @Override
    public void onEvent(Event e) {
        events.add(e);
    }

    //return an immuatblae snapshot of all events seen so far
    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

}
