package brewery.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.time.OffsetDateTime;

public class OffsetDateTimeToStringSerializer extends JsonSerializer<OffsetDateTime> {

    @SneakyThrows
    @Override
    public void serialize(OffsetDateTime offsetDateTime, 
                          JsonGenerator jsonGenerator, 
                          SerializerProvider serializerProvider) {
        jsonGenerator.writeObject(offsetDateTime.toString());
        
    }
}