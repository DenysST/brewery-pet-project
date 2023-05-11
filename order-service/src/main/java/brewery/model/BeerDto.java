package brewery.model;

import brewery.model.serializers.OffsetDateTimeDeserializer;
import brewery.model.serializers.OffsetDateTimeToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id = null;
    private Integer version = null;

    @JsonSerialize(using = OffsetDateTimeToStringSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime createdDate = null;

    @JsonSerialize(using = OffsetDateTimeToStringSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime lastModifiedDate = null;
    private String beerName;
    private String beerStyle;
    private String upc;
    private Integer quantityOnHand;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;
}
