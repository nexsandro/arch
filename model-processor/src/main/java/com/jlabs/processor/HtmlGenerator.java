package com.jlabs.processor;

import com.jlabs.processor.view.ViewWriter;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.*;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(
        value = {"javax.persistence.Entity"}
)
public class HtmlGenerator extends AbstractProcessor {

    private Messager messager;
    private ProcessingEnvironment processingEnv;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Entity.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Can be applied to class.");
                return true;
            }

            try {
                final FileObject resource = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", element.getSimpleName() + ".html", element);
                final Writer writer = resource.openWriter();
                new ViewWriter(writer, element).write();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
        this.processingEnv = processingEnv;
        super.init(processingEnv);
    }
}
