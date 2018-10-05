package com.nixbyte.project.modules.restaurantcards.view;

public class DataCard {
    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public DataCard(String snippet) {
        this.snippet = snippet;
    }

    private String snippet;
}
