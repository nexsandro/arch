package com.jlabs.processor.view;

import com.jlabs.processor.view.model.Entity;
import com.jlabs.processor.view.model.Field;

import java.io.IOException;
import java.io.Writer;

public class ViewWriter {

    private final Writer writer;

    private PropertyRenderFactory propertyRenderFactory;

    public ViewWriter(PropertyRenderFactory propertyRenderFactory, Writer writer) {
        this.propertyRenderFactory = propertyRenderFactory;
        this.writer = writer;
    }

    public void writeEntity(Entity entity) {

        try {
            for (Field field : entity.getFields()) {
                processField(field);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeHeader() throws IOException {
        writer.write("<!DOCTYPE html>\n<html><head>" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t<link href=\"bootstrap-4.3.1-dist/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\"></head><body><div class=\"container\">\n\t<form>\n");
    }

    public void writeFooter() throws IOException {
        writer.write("<button type=\"button\" class=\"btn btn-primary mr-2\">Cancel</button>");
        writer.write("<button type=\"button\" class=\"btn btn-primary mr-2\">Save</button>");

        writer.write("\t</div></form>\n\t<script src=\"jquery-3.3.1.slim.min.js\"></script>\n\t<script src=\"bootstrap-4.3.1-dist/js/bootstrap.min.js\"></script>\n</body>\n</html>");
    }

    private void processField(Field field) throws IOException {

        writer.write(
            propertyRenderFactory.getRender(field).render(field)
        );

    }
}
