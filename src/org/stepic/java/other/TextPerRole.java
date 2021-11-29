package org.stepic.java.other;

public class TextPerRole {

    public static void main(String[] args) {
        String [] roles= {
                "Городничий","Аммос Федорович",
                "Артемий Филиппович",
                "Лука Лукич"
        };
        String [] textLines={
                "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"
        };

        TextPerRole execClass = new TextPerRole();
        System.out.println(execClass.printTextPerRole(roles, textLines));
    }

    private String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder result;
        result = new StringBuilder();
        String prefix;
        int textLineNumber;

        for (String role: roles) {
            result.append(role).append(":\n");
            prefix = role + ": ";
            textLineNumber = 0;

            for (String textLine: textLines) {
                textLineNumber++;
                if (textLine.startsWith(prefix)) {
                    result.append(textLineNumber)
                            .append(") ")
                            .append(textLine.substring(prefix.length()))
                            .append("\n");
                }
            }

            result.append("\n");
        }

        return result.toString();
    }

}
