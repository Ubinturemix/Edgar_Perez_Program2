/*
Author: Edgar Perez
Assignment: Program 2
Class: CSC 2040
 */

package com.opinion.app;

//Observer of datastore events
public interface DataStoreObserver {
    void onEvent(Event e);
}
