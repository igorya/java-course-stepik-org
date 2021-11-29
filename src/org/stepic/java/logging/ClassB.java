package org.stepic.java.logging;

import java.util.logging.Logger;

public class ClassB {
    final Logger LOGGER = Logger.getLogger("org.stepic.java.logging.ClassB");

    void logMessage(String string) {
        LOGGER.finest(string + "; step 1");
        LOGGER.warning(string + "; step 2");
    }
}
