package org.stepic.java.generic;

import java.util.*;

public class MainMailService {

    public static void main(String[] args) {
        // Random variables
        String randomFrom = "addr1";
        String randomTo = "addr2";
        int randomSalary = 101;

        /*
        * Mail messages
        */
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );
        assert firstMessage.getFrom().equals("Robert Howard"): "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft"): "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!"): "Wrong firstMessage content ending";
//        System.out.println(firstMessage);

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );
        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );
        // Make List of MailMessage
        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Process MailMessage by MailService
        MailService<String> mailService = new MailService<>();
        messages.stream().forEachOrdered(mailService);

        Map<String, List<String>> mailBox = mailService.getMailBox();

        System.out.println(mailBox.get(firstMessage.getTo()));
        System.out.println(mailBox.get(secondMessage.getTo()));
        System.out.println(mailBox.get(randomTo));

        // Assertions
        assert mailBox.get("H.P. Lovecraft").equals(
                Collections.singletonList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ): "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ): "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()): "wrong mailService mailbox content (3)";


        /*
         * Salaries
         */
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        MailService<Integer> salaryService = new MailService<>();
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        Map<String, List<Integer>> salaries = salaryService.getMailBox();

        System.out.println(salaries.get(salary1.getTo()));
        System.out.println(salaries.get(salary2.getTo()));
        System.out.println(salaries.get(randomTo));

        // Assertions
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)): "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)): "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)): "wrong salaries mailbox content (3)";

        ////
//        List<String> list1 = Arrays.asList("One", "Two");
//        LinkedList<String> list2 = new LinkedList<>(Arrays.asList("One", "Two"));
//        System.out.println(list1.equals(list2));
    }
}
