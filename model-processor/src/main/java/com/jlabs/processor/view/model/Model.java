package com.jlabs.processor.view.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private Map<String, Entity> entities;

    public Model() {
        entities = new HashMap<String,Entity>();
    }

    public Entity get(String name) {
        return entities.get(name);
    }

    public void add(Entity entity) {
        entities.put(entity.getName(), entity);
    }

    @Override
    public String toString() {
        return "Model{" +
                "entities=" + entities +
                '}';
    }
}
