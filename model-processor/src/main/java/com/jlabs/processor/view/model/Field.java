package com.jlabs.processor.view.model;

public class Field {

    private String name;

    private Type type;

    private boolean nullable;

    private Integer length;

    private Integer precision;

    private String defaultValue;

    public Field(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Field(String name, Type type, boolean nullable, Integer length) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", nullable=" + nullable +
                ", length=" + length +
                ", precision=" + precision +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
