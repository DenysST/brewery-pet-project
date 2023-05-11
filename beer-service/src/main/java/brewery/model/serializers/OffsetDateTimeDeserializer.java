package brewery.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.time.OffsetDateTime;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    @SneakyThrows
    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String date = jsonParser.getValueAsString();
        return OffsetDateTime.parse(date);
    }
}