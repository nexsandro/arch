package com.jlabs.processor.view.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Entity {

    private Type type;

    private Type parent;

    private Set<Field> fields;

    public Entity(String entityName) {
        this.type = new Type(entityName);
        this.fields = new HashSet<>();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getParent() {
        return parent;
    }

    public void setParent(Type parent) {
        this.parent = parent;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public void addField(Field field) {
        getFields().add(field);
    }

    public String getName() {
        String name = type.getName();
        if (type.getInnerType() != null) {
            name = name + "<" + type.getInnerType() + ">";
        }
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "type=" + type +
                ", parent=" + parent +
                ", fields=" + fields +
                '}';
    }
}
