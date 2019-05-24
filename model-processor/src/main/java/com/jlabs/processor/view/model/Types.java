package com.jlabs.processor.view.model;

import java.util.HashMap;
import java.util.Map;

public final class Types {

    private static final Map<Type, Type> types = new HashMap<>();

    public static Type of(String typeName, String innerType) {
        final Type candidateType = new Type(typeName, innerType);
        if (!types.keySet().contains(candidateType)) {
            types.put(candidateType, candidateType);
        }
        return types.get(candidateType);
    }

    public static Type of(String typeName) {
        return of(typeName, null);
    }

}
