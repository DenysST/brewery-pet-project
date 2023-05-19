package brewery.order.service.web.mappers;

import brewery.model.BeerDto;
import brewery.model.BeerOrderLineDto;
import brewery.order.service.domain.BeerOrderLine;
import brewery.order.service.services.beer.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class BeerOrderLineMapper {
    private DateMapper dateMapper;
    private BeerService beerService;

    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if ( line == null ) {
            return null;
        }

        BeerOrderLineDto.BeerOrderLineDtoBuilder beerOrderLineDto = BeerOrderLineDto.builder();

        beerOrderLineDto.id( line.getId() );
        if ( line.getVersion() != null ) {
            beerOrderLineDto.version( line.getVersion().intValue() );
        }
        beerOrderLineDto.createdDate( dateMapper.asOffsetDateTime( line.getCreatedDate() ) );
        beerOrderLineDto.lastModifiedDate( dateMapper.asOffsetDateTime( line.getLastModifiedDate() ) );
        beerOrderLineDto.upc( line.getUpc() );
        beerOrderLineDto.beerId( line.getBeerId() );
        beerOrderLineDto.orderQuantity( line.getOrderQuantity() );
        beerOrderLineDto.quantityAllocated( line.getQuantityAllocated() );
        Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(line.getUpc());
        beerDtoOptional.ifPresent(beerDto -> {
            beerOrderLineDto.beerName(beerDto.getBeerName());
            beerOrderLineDto.beerStyle(beerDto.getBeerStyle());
            beerOrderLineDto.price(beerDto.getPrice());
            beerOrderLineDto.beerId(beerDto.getId());
        });

        return beerOrderLineDto.build();
    }

    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        if ( dto == null ) {
            return null;
        }

        BeerOrderLine.BeerOrderLineBuilder beerOrderLine = BeerOrderLine.builder();

        beerOrderLine.id( dto.getId() );
        if ( dto.getVersion() != null ) {
            beerOrderLine.version( dto.getVersion().longValue() );
        }
        beerOrderLine.createdDate( dateMapper.asTimestamp( dto.getCreatedDate() ) );
        beerOrderLine.lastModifiedDate( dateMapper.asTimestamp( dto.getLastModifiedDate() ) );
        beerOrderLine.beerId( dto.getBeerId() );
        beerOrderLine.upc( dto.getUpc() );
        beerOrderLine.orderQuantity( dto.getOrderQuantity() );
        beerOrderLine.quantityAllocated( dto.getQuantityAllocated() );

        return beerOrderLine.build();
    }
}
