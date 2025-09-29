/*
Edgar Perez
Program 2
CSC 2040
 */

package com.opinion.app;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//Logs events to "events.log" using java.util.logging.
public class EventLoggerObserver implements DataStoreObserver {

    private final Logger log;

    public EventLoggerObserver() {
        this.log = Logger.getLogger("ReviewEvents");
        try {
            //Avoid duplicate FileHandlers if constructed multiple times
            boolean hasFileHandler = false;
            for (Handler h : log.getHandlers()) {
                if (h instanceof FileHandler) {
                    hasFileHandler = true;
                    break;
                }
            }
            if (!hasFileHandler) {
                FileHandler fh = new FileHandler("events.log", true);
                fh.setFormatter(new SimpleFormatter());
                log.addHandler(fh);
                log.setUseParentHandlers(false);
            }
        } catch (IOException ignored) {
            //Logging must not crash app or tests.
        }
    }

    @Override
    public void onEvent(final Event e) {
        log.info(e.toString());
    }
}

