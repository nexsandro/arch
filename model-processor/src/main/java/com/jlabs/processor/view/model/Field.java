package com.jlabs.processor.view.model;

public class Field {

    private Entity entity;

    private String name;

    private Type type;

    private boolean nullable;

    private Integer length;

    private Integer precision;

    private String defaultValue;

    public Field(Entity entity, String name, Type type) {
        this.entity = entity;
        this.name = name;
        this.type = type;
    }

    public Field(Entity entity, String name, Type type, boolean nullable, Integer length) {
        this.entity = entity;
        this.name = name;
        this.type = type;
        this.nullable = nullable;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isNullable() {
        return nullable;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getPrecision() {
        return precision;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "{\"Field\": {" +
                "\"name\": '" + name + '\'' +
                ", \"type\": " + type +
                ", \"nullable\": " + nullable +
                ", \"length\": " + length +
                ", \"precision\": " + precision +
                ", \"defaultValue\": '" + defaultValue + '\'' +
                "}}";
    }
}
