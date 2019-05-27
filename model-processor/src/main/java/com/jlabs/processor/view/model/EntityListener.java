package com.jlabs.processor.view.model;

import java.io.IOException;

public interface EntityListener {

    void onStart(Entity entity) throws IOException ;

    void onEnd(Entity entity) throws IOException;

    void onEntity(Entity entity) throws IOException ;

    boolean accepts(Entity entity);

}
