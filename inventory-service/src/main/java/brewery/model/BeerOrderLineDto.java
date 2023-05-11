package brewery.model;

import brewery.model.serializers.OffsetDateTimeDeserializer;
import brewery.model.serializers.OffsetDateTimeToStringSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BeerOrderLineDto {

    @JsonProperty("id")
    private UUID id = null;

    @JsonProperty("version")
    private Integer version = null;

    @JsonSerialize(using = OffsetDateTimeToStringSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate = null;

    @JsonSerialize(using = OffsetDateTimeToStringSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonProperty("lastModifiedDate")
    private OffsetDateTime lastModifiedDate = null;

    private String upc;
    private String beerName;
    private String beerStyle;
    private UUID beerId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated;
    private BigDecimal price;
}
