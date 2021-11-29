package org.stepic.java.textanalyzer;

public class Main {
    public static void main(String[] args) {
        String[] spamKeywords = {"spam", "bad"};
        int commentMaxLength = 40;
        TextAnalyzer[] textAnalyzers1 = {
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers2 = {
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers3 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords),
                new NegativeTextAnalyzer()
        };
        TextAnalyzer[] textAnalyzers4 = {
                new TooLongTextAnalyzer(commentMaxLength),
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords)
        };
        TextAnalyzer[] textAnalyzers5 = {
                new NegativeTextAnalyzer(),
                new SpamAnalyzer(spamKeywords),
                new TooLongTextAnalyzer(commentMaxLength)
        };
        TextAnalyzer[] textAnalyzers6 = {
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(commentMaxLength),
                new SpamAnalyzer(spamKeywords)
        };
        String[] inputTexts = new String[8];
        inputTexts[0] = "This comment is so good.";                            // OK
        inputTexts[1] = "This comment is so Loooooooooooooooooooooooooooong."; // TOO_LONG
        inputTexts[2] = "Very negative comment !!!!=(!!!!;";                   // NEGATIVE_TEXT
        inputTexts[3] = "Very BAAAAAAAAAAAAAAAAAAAAAAAAD comment with :|;";    // NEGATIVE_TEXT or TOO_LONG
        inputTexts[4] = "This comment is so bad....";                          // SPAM
        inputTexts[5] = "The comment is a spam, maybeeeeeeeeeeeeeeeeeeeeee!";  // SPAM or TOO_LONG
        inputTexts[6] = "Negative bad :( spam.";                               // SPAM or NEGATIVE_TEXT
        inputTexts[7] = "Very bad, very neg =(, very ..................";      // SPAM or NEGATIVE_TEXT or TOO_LONG
        TextAnalyzer[][] textAnalyzers = {textAnalyzers1, textAnalyzers2, textAnalyzers3,
                textAnalyzers4, textAnalyzers5, textAnalyzers6};

        Main objMain = new Main();
        int analyzerNumber;
        int textNumber = 0;

        for (String curText : inputTexts) {
            analyzerNumber = 1;
            System.out.print("text #" + textNumber + ": ");
            System.out.println(curText);
            for (TextAnalyzer[] curAnalyzers : textAnalyzers) {
                System.out.print(analyzerNumber + ": ");
                System.out.println(objMain.checkLabels(curAnalyzers, curText));
                analyzerNumber++;
            }
            textNumber++;
        }
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        Label curLabel = Label.OK;

        for (TextAnalyzer textAnalyzer : analyzers) {
            curLabel = textAnalyzer.processText(text);
            if (curLabel != Label.OK) {
                break;
            }
        }
        return curLabel;
    }
}



