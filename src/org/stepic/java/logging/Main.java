package org.stepic.java.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Main {
    public static void main(String[] args) {
        ClassA objA = new ClassA();
        ClassB objB = new ClassB();

        configureLogging();

        objA.logMessage("Inside A");
        objB.logMessage("Inside B");
    }

    private static void configureLogging() {
        final Logger loggerA = Logger.getLogger("org.stepic.java.logging.ClassA");
        // Works here but doesn't work on stepic server
        // final Logger loggerA = Logger.getLogger(ClassA.class.getName());
        final Logger loggerB = Logger.getLogger("org.stepic.java.logging.ClassB");
        final Logger loggerMain = Logger.getLogger("org.stepic.java");

        loggerA.setLevel(Level.ALL);
        loggerB.setLevel(Level.WARNING);

        loggerMain.setUseParentHandlers(false);
        loggerMain.setLevel(Level.ALL);

        XMLFormatter xmlFormatter = new XMLFormatter();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(xmlFormatter);
        consoleHandler.setLevel(Level.ALL);
        loggerMain.addHandler(consoleHandler);
    }
}
