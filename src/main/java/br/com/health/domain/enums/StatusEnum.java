package br.com.health.domain.enums;

public enum StatusEnum {

    ACTIVE(1, "ACTIVE"),
    INACTIVE(0, "INACTIVE");

    private final Integer value;
    private final String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
