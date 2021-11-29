package org.stepic.java.generic;

class Salary extends MailMessageBase<Integer> {
    public Salary(String from, String to, Integer content) {
        super(from, to, content);
    }
}
