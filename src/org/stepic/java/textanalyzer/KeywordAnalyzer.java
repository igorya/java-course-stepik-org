package org.stepic.java.textanalyzer;


abstract class KeywordAnalyzer implements TextAnalyzer {

    @Override
    public Label processText(String text) {
        text = text.toLowerCase();
        for (String keyword: getKeywords()) {
            if (text.contains(keyword.toLowerCase())) {
                return getLabel();
            }
        }
        return Label.OK;
    }

    protected abstract String[] getKeywords();

    protected abstract Label getLabel();
}
