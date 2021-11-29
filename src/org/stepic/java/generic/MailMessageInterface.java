package org.stepic.java.generic;

import java.util.Optional;

interface MailMessageInterface<C> {
    public String getFrom();
    public String getTo();
    public C getContent();
}
