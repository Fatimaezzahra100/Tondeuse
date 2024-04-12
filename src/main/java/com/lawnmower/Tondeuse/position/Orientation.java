package com.lawnmower.Tondeuse.position;

public enum Orientation {

    NORTH('N', "Nord"),
    EAST('E', "est"),
    WEST('W', "ouest"),
    SOUTH('S', "sud");

    private char code;
    private String label;

    private Orientation(char code, String label) {
        this.code = code;
        this.label = label;
    }

    public char getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Orientation getByCode(char code) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.code == code) {
                return orientation;
            }
        }
        throw new IllegalArgumentException("Invalid orientation code: " + code);
    }
}
