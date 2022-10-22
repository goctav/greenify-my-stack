package org.gms.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Converters {
    public static <T> T convertObjectTo(Object o, TypeReference<T> reference) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(o, reference);
    }
}