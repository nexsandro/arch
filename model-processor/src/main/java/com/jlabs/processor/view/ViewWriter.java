package com.jlabs.processor.view;

import org.springframework.util.StringUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.persistence.Column;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ViewWriter {

    private final Writer writer;

    private final Element element;

    public ViewWriter(Writer writer, Element element) {
        this.writer = writer;

        this.element = element;
    }

    public void write() {
        final List<? extends Element> enclosedElements = element.getEnclosedElements();

        try {
            writer.write("<!DOCTYPE html>\n<html><head>" +
                    "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "\t<link href=\"bootstrap-4.3.1-dist/css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\"></head><body><div class=\"container\">\n\t<form>\n");

            for (Element member : enclosedElements) {

                List<AnnotationMirror> annotations = retrievePersistenceAnnotations(member);

                for (AnnotationMirror annotation : annotations) {
                    System.out.println( member.getSimpleName() + "---" + annotation);
                }

                if (annotations.size() > 0) {
                    processMember(element, member);
                }

            }
            writer.write("\t</div></form>\n" +
                    "\t<script src=\"jquery-3.3.1.slim.min.js\"></script>\n" +
                    "\t<script src=\"bootstrap-4.3.1-dist/js/bootstrap.min.js\"></script>\n</body>\n</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void processMember(Element element, Element member) throws IOException {
        final Column column = member.getAnnotation(Column.class);

        if (column == null) return;

        writer.write("\t\t<div class=\"form-group\">\n");

        if (column != null) {
            String memberId = StringUtils.uncapitalize(element.getSimpleName() + "." + member.getSimpleName());
            String nullableFlag = "";
            if (!column.nullable()) {
                nullableFlag = "* ";
            }

            writer.append("\t\t\t\t").append(nullableFlag).append("<label for=\"").append(memberId).append("\">${").append(memberId).append("}</label>\n");
            writer.append("\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"").append(memberId).append("\" id=\"").append(memberId).append("\" />\n");
        }

        writer.write("\t\t</div>\n");

    }

    private List<AnnotationMirror> retrievePersistenceAnnotations(Element member) {
        List<AnnotationMirror> annotations = new ArrayList<>();
        final List<? extends AnnotationMirror> annotationMirrors = member.getAnnotationMirrors();
        for (AnnotationMirror annotationMirror : annotationMirrors) {
            if (annotationMirror.getAnnotationType().toString().startsWith("javax.persistence")) {
                annotations.add(annotationMirror);
            }
        }
        return annotations;
    }
}
