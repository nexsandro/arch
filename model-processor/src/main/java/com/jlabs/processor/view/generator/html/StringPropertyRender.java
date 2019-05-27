package com.jlabs.processor.view.generator.html;

import com.jlabs.processor.view.model.Field;

public class StringPropertyRender implements com.jlabs.processor.view.PropertyRender {

    @Override
    public String render(Field field) {

        StringBuffer renderedField = new StringBuffer();
        renderedField.append("\t\t<div class=\"form-group\">\n");

        String memberId = field.getName();
        String nullableFlag = "";
        if (!field.isNullable()) {
            nullableFlag = "* ";
        }

        renderedField.append("\t\t\t\t").append(nullableFlag).append("<label for=\"").append(memberId).append("\">${").append(field.getEntity().getType().getName()).append(".").append(memberId).append("}</label>\n");
        renderedField.append("\t\t\t\t<input class=\"form-control\" type=\"text\" name=\"").append(memberId).append("\" id=\"").append(memberId).append("\" />\n");

        renderedField.append("\t\t</div>\n");
        return renderedField.toString();
    }

}
