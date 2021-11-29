package org.stepic.java.generic;

abstract class MailMessageBase<C> implements MailMessageInterface<C> {
    protected String from;
    protected String to;
    protected C content;

    public MailMessageBase(String from, String to, C content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public C getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("{%s} From:'%s' To:'%s' Content'%s'", this.getClass().getName(), from, to, content);
    }
}
