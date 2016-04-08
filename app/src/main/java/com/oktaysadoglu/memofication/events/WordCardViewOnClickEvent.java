package com.oktaysadoglu.memofication.events;

/**
 * Created by oktaysadoglu on 08/02/16.
 */
public class WordCardViewOnClickEvent {

    private boolean truth;

    private int positionInTab;

    private int tabSection;

    private long wordId;

    public WordCardViewOnClickEvent(boolean truth, int positionInTab, int tabSection, long wordId) {
        this.truth = truth;
        this.positionInTab = positionInTab;
        this.tabSection = tabSection;
        this.wordId = wordId;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public boolean isTruth() {
        return truth;
    }

    public void setTruth(boolean truth) {
        this.truth = truth;
    }

    public int getPositionInTab() {
        return positionInTab;
    }

    public void setPositionInTab(int positionInTab) {
        this.positionInTab = positionInTab;
    }

    public int getTabSection() {
        return tabSection;
    }

    public void setTabSection(int tabSection) {
        this.tabSection = tabSection;
    }
}
