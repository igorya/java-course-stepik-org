package org.stepic.java.generic;

import java.util.*;
import java.util.function.Consumer;

class MailService<C> implements Consumer<MailMessageInterface<C>> {

    private Map<String, List<C>> mailBox;

    public Map<String, List<C>> getMailBox() {
        return mailBox;
    }

    public MailService() {
        mailBox = new HashMap<>() {
            @Override
            public List<C> get(Object key) {
                return super.getOrDefault(key, new LinkedList<>());
            }
        };
    }

    @Override
    public void accept(MailMessageInterface<C> cMailMessageInterface) {
        List<C> cList = mailBox.get(cMailMessageInterface.getTo());
        if (cList == null) {
            cList = new ArrayList<>();
        }
        cList.add(cMailMessageInterface.getContent());
        mailBox.put(cMailMessageInterface.getTo(), cList);
    }
}
