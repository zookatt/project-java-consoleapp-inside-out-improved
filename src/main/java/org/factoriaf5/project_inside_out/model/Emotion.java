package org.factoriaf5.project_inside_out.model;

public enum Emotion {
    JOY(1, "Alegria"),
    SADNESS(2, "Tristeza"),
    ANGER(3, "Ira"),
    DISGUST(4, "Asco"),
    FEAR(5, "Miedo"),
    ANXIETY(6, "Ansiedad"),
    ENVY(7, "Envidia"),
    SHAME(8, "Verguenza"),
    BOREDOM(9, "Aburrimiento"),
    NOSTALGIA(10, "Nostalgia");

    private int option;
    private String displayName;

    Emotion(int option, String displayName) {
        this.option = option;
        this.displayName = displayName;
    }

    public int getOption() {
        return option;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Emotion fromOption(int option) {
        for (Emotion emotion : Emotion.values()) {
            if (emotion.getOption() == option) {
                return emotion;
            }
        }

        return null;
    }

}
