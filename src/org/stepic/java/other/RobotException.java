package org.stepic.java.other;

public class RobotException {
    public static void main(String[] args) {

    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        int idx, amErrors = 0;
        final int MAX_ATTEMPTS = 3;

        for (idx = 1; idx <= MAX_ATTEMPTS; idx++) {
            try (RobotConnection robotConnection = robotConnectionManager.getConnection()) {
                robotConnection.moveRobotTo(toX, toY);

            } catch (RobotConnectionException e) {
                if (!e.getStackTrace()[0].getMethodName().equals("close")) {
                    amErrors++;
                }
                if (amErrors >= MAX_ATTEMPTS) {
                    throw e;
                }

            } catch (Throwable otherException) {
                throw otherException;

            } finally {
                if (amErrors < idx) {
                    idx = MAX_ATTEMPTS + 1;
                }
            }
        }
    }
}

interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}

interface RobotConnectionManager {
    RobotConnection getConnection();
}

class RobotConnectionException extends RuntimeException {

    public RobotConnectionException(String message) {
        super(message);
    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}