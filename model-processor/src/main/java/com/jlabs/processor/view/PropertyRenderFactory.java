package com.jlabs.processor.view;

import com.jlabs.processor.view.generator.html.BooleanPropertyRender;
import com.jlabs.processor.view.generator.html.StringPropertyRender;
import com.jlabs.processor.view.model.Field;
import com.jlabs.processor.view.model.Type;
import com.jlabs.processor.view.model.Types;

import java.util.HashMap;
import java.util.Map;

public class PropertyRenderFactory {

    private Map<Type, PropertyRender> renders = new HashMap<>();

    public PropertyRender getRender(Field field) {

        final Type fieldType = field.getType();
        PropertyRender render = renders.get(fieldType);
        if (render == null) {
            if (Types.of("java.lang.String").equals(fieldType)) {
                render = new StringPropertyRender();
            } else if (Types.of("boolean").equals(fieldType) || Types.of(Boolean.class.getCanonicalName()).equals(fieldType)) {
                render = new BooleanPropertyRender();
            } else {
                render = new StringPropertyRender();
            }
            renders.put(fieldType, render);
        }

        return render;
    }

}
