package org.stepic.java.generic;

class MailMessage extends MailMessageBase<String> {
    public MailMessage(String from, String to, String content) {
        super(from, to, content);
    }
}
