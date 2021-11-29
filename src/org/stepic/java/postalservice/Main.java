package org.stepic.java.postalservice;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());

        Inspector inspector = new Inspector();
        Spy spy = new Spy(logger);
        Thief thief = new Thief(1000);
        MailService[] specialWorkers = new MailService[]{spy, thief, inspector};
        UntrustworthyMailWorker untrustedWorker = new UntrustworthyMailWorker(specialWorkers);

        AbstractSendable[] allSendables = {
                new MailPackage(AUSTIN_POWERS, "CoolAddr", new Package("Some message 6", 1000)),
                new MailMessage("BlaBlaAddr", "Bar", "Some message 1\n"),
                new MailMessage("Bar", "BlaBlaAddr", "Some message 2\n"),
                new MailMessage("Add1", AUSTIN_POWERS, "Some message 3"),
                new MailMessage(AUSTIN_POWERS, "Add2", "Some message 4"),
                new MailPackage("Addr3", "Add4", new Package("Content", 32)),
                new MailMessage("CoolAddr", AUSTIN_POWERS, "Some message 5"),
                new MailPackage(AUSTIN_POWERS, "CoolAddr", new Package("stones", 1000)),
                new MailPackage("Addr5", "Addr6", new Package("banned substance", 99)),
                new MailPackage(AUSTIN_POWERS, "SomeTo", new Package("tiny bomb", 9000)),
                new MailMessage(AUSTIN_POWERS, "Add3", "Hi"),
        };
        Arrays.stream(allSendables).forEach(mailObj -> {
            try {
                untrustedWorker.processMail(mailObj);
            } catch (StolenPackageException e) {
                logger.log(Level.WARNING, "Inspector found stolen package: " + e);
            } catch (IllegalPackageException e) {
                logger.log(Level.WARNING, "Inspector found illegal package: " + e);
            }
        });
    }

    public static class UntrustworthyMailWorker implements MailService {

        private RealMailService realMailService;
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
            this.realMailService = new RealMailService();
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService mailService : mailServices) {
                mail = mailService.processMail(mail);
            }
            return realMailService.processMail(mail);
        }
    }

    public static class Spy implements MailService {
        private Logger logger;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (AUSTIN_POWERS.equals(mail.getFrom()) || AUSTIN_POWERS.equals(mail.getTo())) {
                    logger.log(
                            Level.WARNING,
                            "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()}
                    );
                } else {
                    logger.log(
                            Level.INFO,
                            "Usual correspondence: from {0} to {1}",
                            new Object[]{mail.getFrom(), mail.getTo()}
                    );
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private int minValue;
        private int stolenValue;

        public Thief(int minValue) {
            this.minValue = minValue;
            this.stolenValue = 0;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                int curPrice = ((MailPackage) mail).getContent().getPrice();
                if (curPrice >= minValue) {
                    stolenValue += curPrice;
                    return new MailPackage(
                            mail.getFrom(),
                            mail.getTo(),
                            new Package("stones instead of "+((MailPackage) mail).getContent().getContent(), 0)
                    );
                }
            }
            return mail;
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException() {
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException() {
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                String curContent = ((MailPackage) mail).getContent().getContent();

                if (curContent.contains(WEAPONS) || curContent.contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                }
                if (curContent.contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    /*
    Интерфейс: сущность, которую можно отправить по почте.
    У такой сущности можно получить от кого и кому направляется письмо.
    */
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    /*
     * Абстрактный класс,который позволяет абстрагировать логику хранения
     * источника и получателя письма в соответствующих полях класса.
     */
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }
    }

    /*
     * Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
     */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }
    }

    /*
     * Посылка, содержимое которой можно получить с помощью метода `getContent`
     */
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }
    }

    /*
     * Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
     */
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    /*
    Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
    */
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    Класс, в котором скрыта логика настоящей почты
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }
}
