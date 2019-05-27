package com.jlabs.processor.view.generator;

import com.jlabs.processor.view.PropertyRenderFactory;
import com.jlabs.processor.view.ViewWriter;
import com.jlabs.processor.view.model.Entity;
import com.jlabs.processor.view.model.EntityListener;

import javax.annotation.processing.Filer;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;

public class HtmlGeneratorEntityListener implements EntityListener {

    private ViewWriter viewWriter;
    private Filer filer;
    private Writer writer;
    private PropertyRenderFactory propertyRenderFactory;

    public HtmlGeneratorEntityListener(Filer filer, PropertyRenderFactory propertyRenderFactory) {
        this.filer = filer;
        this.propertyRenderFactory = propertyRenderFactory;
    }


    @Override
    public void onStart(Entity entity) throws IOException {
        this.writer = filer.createResource(StandardLocation.CLASS_OUTPUT, "", entity.getName() + ".html").openWriter();
        this.viewWriter = new ViewWriter(propertyRenderFactory, writer);
        this.viewWriter.writeHeader();
    }

    @Override
    public void onEnd(Entity entity) throws IOException {
        if (writer != null) {
            viewWriter.writeFooter();
            this.writer.flush();
            this.writer.close();
        }
    }

    @Override
    public void onEntity(Entity entity) throws IOException {
        viewWriter.writeEntity(entity);
    }

    @Override
    public boolean accepts(Entity entity) {
        return !entity.getName().endsWith("Enum")
                && !entity.getName().contains("common");
    }
}
