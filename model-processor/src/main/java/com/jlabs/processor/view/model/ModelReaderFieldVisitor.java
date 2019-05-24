package com.jlabs.processor.view.model;

import javax.lang.model.element.*;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

public class ModelReaderFieldVisitor implements ElementVisitor<Void, Entity> {

    @Override
    public Void visit(Element e, Entity entity) {
        return null;
    }

    @Override
    public Void visit(Element e) {
        return null;
    }

    @Override
    public Void visitPackage(PackageElement e, Entity entity) {
        return null;
    }

    @Override
    public Void visitType(TypeElement e, Entity entity) {
        return null;
    }

    @Override
    public Void visitVariable(VariableElement e, Entity entity) {

        if (e.getAnnotation(Column.class) != null) {
            final Field field = null;
            entity.addField(field);
        }
        if (e.getAnnotation(JoinColumn.class) != null) {

        }
        return null;
    }

    @Override
    public Void visitExecutable(ExecutableElement e, Entity entity) {
        final Column columnAnnotation = e.getAnnotation(Column.class);
        if (columnAnnotation != null) {
            final Field field = new Field(e.getSimpleName().toString(), Types.of(e.getReturnType().toString()), columnAnnotation.nullable(), columnAnnotation.length());
            entity.addField(field);
        }
        final JoinColumn joinColumnAnnotation = e.getAnnotation(JoinColumn.class);
        if (joinColumnAnnotation != null) {
            final Field field = new Field(e.getSimpleName().toString(), Types.of(e.getReturnType().toString()), joinColumnAnnotation.nullable(), null);
            entity.addField(field);
        }
        return null;
    }

    @Override
    public Void visitTypeParameter(TypeParameterElement e, Entity entity) {
        return null;
    }

    @Override
    public Void visitUnknown(Element e, Entity entity) {
        return null;
    }
}
