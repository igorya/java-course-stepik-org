package org.stepic.java.other;

public class RobotManager {

    public static void main(String[] args) {
        Robot robot = new Robot(0, 0, Direction.DOWN);
        moveRobot(robot, 10, -5);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        class RobotDecorator {
            final Robot robot;
            public RobotDecorator(Robot robot) {
                this.robot = robot;
            }
            void turnLeft(Direction destDir) {
                while (robot.getDirection() != destDir) {
                    printTurnInfo("Left", destDir);
                    robot.turnLeft();
                }
                printTurnInfo("Left", destDir);
            }
            void turnRight(Direction destDir) {
                while (robot.getDirection() != destDir) {
                    printTurnInfo("Right", destDir);
                    robot.turnRight();
                }
                printTurnInfo("Right", destDir);
            }
            void stepForwardToX(int destX) {
                while (robot.getX() != destX) {
                    System.out.println("Robot stepForward, cur x/y: " + robot.getX() +"/"+ robot.getY());
                    robot.stepForward();
                }
                System.out.println("Robot cur x/y: " + robot.getX() +"/"+ robot.getY());
            }
            void stepForwardToY(int destY) {
                while (robot.getY() != destY) {
                    System.out.println("Robot stepForward, cur x/y: " + robot.getX() +"/"+ robot.getY());
                    robot.stepForward();
                }
                System.out.println("Robot cur x/y: " + robot.getX() +"/"+ robot.getY());
            }
            void printTurnInfo(String turnName, Direction destDir) {
                System.out.println("Robot turn"+ turnName +", dest/cur directions: " + destDir +"/"+ robot.getDirection());
            }
        }
        RobotDecorator robotDecorator = new RobotDecorator(robot);

        if (robot.getX() > toX) {
            if (robot.getDirection() == Direction.DOWN) {
                robotDecorator.turnRight(Direction.LEFT);
            } else {
                robotDecorator.turnLeft(Direction.LEFT);
            }
        } else if (robot.getX() < toX) {
            if (robot.getDirection() == Direction.UP) {
                robotDecorator.turnRight(Direction.RIGHT);
            } else {
                robotDecorator.turnLeft(Direction.RIGHT);
            }
        }
        robotDecorator.stepForwardToX(toX);

        if (robot.getY() > toY) {
            if (robot.getDirection() == Direction.LEFT) {
                robotDecorator.turnRight(Direction.DOWN);
            } else {
                robotDecorator.turnLeft(Direction.DOWN);
            }
        } else if (robot.getY() < toY) {
            if (robot.getDirection() == Direction.RIGHT) {
                robotDecorator.turnRight(Direction.UP);
            } else {
                robotDecorator.turnLeft(Direction.UP);
            }
        }
        robotDecorator.stepForwardToY(toY);
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static class Robot {
        int x;
        int y;
        Direction direction;

        public Robot(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Direction getDirection() {
            return direction;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void turnLeft() {
            if (direction == Direction.UP) {
                direction = Direction.LEFT;
            } else if (direction == Direction.DOWN) {
                direction = Direction.RIGHT;
            } else if (direction == Direction.LEFT) {
                direction = Direction.DOWN;
            } else if (direction == Direction.RIGHT) {
                direction = Direction.UP;
            }
        }

        public void turnRight() {
            if (direction == Direction.UP) {
                direction = Direction.RIGHT;
            } else if (direction == Direction.DOWN) {
                direction = Direction.LEFT;
            } else if (direction == Direction.LEFT) {
                direction = Direction.UP;
            } else if (direction == Direction.RIGHT) {
                direction = Direction.DOWN;
            }
        }

        public void stepForward() {
            if (direction == Direction.UP) {
                y++;
            }
            if (direction == Direction.DOWN) {
                y--;
            }
            if (direction == Direction.LEFT) {
                x--;
            }
            if (direction == Direction.RIGHT) {
                x++;
            }
        }
    }
}
