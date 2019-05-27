package com.jlabs.processor.view.generator.html;

import com.jlabs.processor.view.PropertyRender;
import com.jlabs.processor.view.model.Field;

public class BooleanPropertyRender implements PropertyRender {

    @Override
    public String render(Field field) {
        StringBuffer renderedField = new StringBuffer();
        renderedField.append("\t\t<div class=\"form-check\">\n");

        String memberId = field.getName();
        String nullableFlag = "";
        if (!field.isNullable()) {
            nullableFlag = "* ";
        }

        renderedField.append("\t\t\t\t<input class=\"form-check-input\" type=\"checkbox\" name=\"").append(memberId).append("\" id=\"").append(memberId).append("\" />");
        renderedField.append("\t\t\t\t").append(nullableFlag).append("<label class=\"form-check-label\" for=\"").append(memberId).append("\">${").append(field.getEntity().getType().getName()).append(".").append(memberId).append("}</label>\n");

        renderedField.append("\t\t</div>\n");
        return renderedField.toString();
    }
}
