package com.example.resource;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

@Provider
public class JsonContextProvider implements ContextResolver<ObjectMapper> {
    final ObjectMapper d;
    final ObjectMapper c;

    public JsonContextProvider() {
        d = createDefaultMapper();
        c = createCombinedMapper();
    }

    private static ObjectMapper createCombinedMapper() {
        Pair ps = createIntrospector();
        ObjectMapper result = new ObjectMapper();
        //result.configure(Feature.WRAP_ROOT_VALUE, true);
        //result.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
        result.setDeserializationConfig(result.getDeserializationConfig().withAnnotationIntrospector(ps));
        result.setSerializationConfig(result.getSerializationConfig().withAnnotationIntrospector(ps));
        return result;
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper result = new ObjectMapper();
        result.configure(Feature.INDENT_OUTPUT, true);
        return result;
    }

    private static Pair createIntrospector() {
        AnnotationIntrospector p = new JacksonAnnotationIntrospector();
        AnnotationIntrospector s = new JaxbAnnotationIntrospector();
        return new Pair(p, s);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        if (type == JsonHybridBook.class) {
            return c;
        } else {
            return d;
        }
    }
}
