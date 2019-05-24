package com.jlabs.processor.view;

import com.jlabs.processor.view.model.Model;
import com.jlabs.processor.view.model.ModelReaderEntityVisitor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(
        value = {"javax.persistence.Entity"}
)
public class ModelReader extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver()) return false;

        ModelReaderEntityVisitor visitor = new ModelReaderEntityVisitor();
        Model model = new Model();

        for (Element rootElement : roundEnv.getRootElements()) {
            rootElement.accept(visitor, model);
        }

        System.out.println(model);

        return true;
    }
}
