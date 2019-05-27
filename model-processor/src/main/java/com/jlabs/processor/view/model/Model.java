package com.jlabs.processor.view.model;

import java.io.IOException;
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

    public void processEntities(EntityListener listener) throws IOException {
        if (entities.isEmpty()) return;

        for (String s : entities.keySet()) {
            final Entity entity = entities.get(s);
            if (listener.accepts(entity)) {
                try {
                    listener.onStart(entity);
                    listener.onEntity(entity);
                } finally {
                    listener.onEnd(entity);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "{\"Model\": {" +
                "\"entities\": " + entities +
                "}}";
    }
}
