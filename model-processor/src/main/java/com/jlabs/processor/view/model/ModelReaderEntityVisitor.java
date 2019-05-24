package com.jlabs.processor.view.model;

import javax.lang.model.element.*;

public class ModelReaderEntityVisitor implements ElementVisitor<Void,Model> {

    private Entity currentEntity;

    private ModelReaderFieldVisitor fieldVisitor = new ModelReaderFieldVisitor();

    @Override
    public Void visit(Element e, Model model) {
        System.out.println(e.asType());
        return null;
    }

    @Override
    public Void visit(Element e) {
        System.out.println(e.asType());
        return null;
    }

    @Override
    public Void visitPackage(PackageElement e, Model model) {
        return null;
    }

    @Override
    public Void visitType(TypeElement e, Model model) {

        registerNewType(e, model);
        System.out.println(e.asType());
        for (Element enclosedElement : e.getEnclosedElements()) {
            enclosedElement.accept(fieldVisitor, model.get(e.asType().toString()));
        }
        return null;
    }

    private void registerNewType(TypeElement e, Model model) {
        currentEntity = new Entity(e.asType().toString());
        model.add(currentEntity);
    }

    @Override
    public Void visitVariable(VariableElement e, Model model) {
        System.out.println(e.asType());
        return null;
    }

    @Override
    public Void visitExecutable(ExecutableElement e, Model model) {
        System.out.println(e.asType());
        return null;
    }

    @Override
    public Void visitTypeParameter(TypeParameterElement e, Model model) {
        System.out.println(e.asType());
        return null;
    }

    @Override
    public Void visitUnknown(Element e, Model model) {
        System.out.println(e.asType());
        return null;
    }
}
