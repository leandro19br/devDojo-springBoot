package br.com.devdojo.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<PageImpl<?>> {


    @Override
    public void serialize(PageImpl page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("content", page.getContent());
        jsonGenerator.writeBooleanField("first", page.isFirst());
        jsonGenerator.writeBooleanField("last", page.isLast());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());

        jsonGenerator.writeNumberField("size", page.getSize());
        jsonGenerator.writeNumberField("number", page.getNumber());

        jsonGenerator.writeArrayFieldStart("sort");

        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

}

