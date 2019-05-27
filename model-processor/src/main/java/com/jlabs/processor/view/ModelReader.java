package com.jlabs.processor.view;

import com.jlabs.processor.view.generator.HtmlGeneratorEntityListener;
import com.jlabs.processor.view.model.Model;
import com.jlabs.processor.view.model.ModelReaderEntityVisitor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(
        value = {"javax.persistence.Entity"}
)
public class ModelReader extends AbstractProcessor {


    private ProcessingEnvironment processingEnv;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver()) return false;

        ModelReaderEntityVisitor visitor = new ModelReaderEntityVisitor();
        Model model = new Model();

        for (Element rootElement : roundEnv.getRootElements()) {
            rootElement.accept(visitor, model);
        }

        // TODO: Serialize model to further processing.

        // Sample further processing
        // .. model = loadFromModel(modelSerialized)

        try {

            HtmlGeneratorEntityListener listener = new HtmlGeneratorEntityListener(processingEnv.getFiler(), new PropertyRenderFactory());
            model.processEntities(listener);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        super.init(processingEnv);
    }
}
