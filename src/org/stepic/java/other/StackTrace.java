package org.stepic.java.other;

public class StackTrace {
    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTraceElements;

        // Yet another way
        /*
        stackTraceElements = new Exception("Test2").getStackTrace();
        for (StackTraceElement element: stackTraceElements) {
            System.out.println(element.getClassName() +"#"+ element.getMethodName());
        }
        */

        stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements.length <= 3 ? null :
                stackTraceElements[3].getClassName() +"#"+ stackTraceElements[3].getMethodName();
    }
}
