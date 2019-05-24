package com.jlabs.processor.view.model;

import java.util.Objects;

public class Type {

    private String name;

    private String innerType;

    public Type(String name) {
        this.name = name;
    }

    public Type(String name, String innerType) {
        this.name = name;
        this.innerType = innerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInnerType() {
        return innerType;
    }

    public void setInnerType(String innerType) {
        this.innerType = innerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(name, type.name) &&
                Objects.equals(innerType, type.innerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, innerType);
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                ", innerType='" + innerType + '\'' +
                '}';
    }
}
