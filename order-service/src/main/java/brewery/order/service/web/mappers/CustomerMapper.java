package brewery.order.service.web.mappers;

import brewery.model.CustomerDto;
import brewery.order.service.domain.BeerOrder;
import brewery.order.service.domain.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CustomerMapper {
    private DateMapper dateMapper;

    public CustomerDto customerToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( customer.getId() );
        if ( customer.getVersion() != null ) {
            customerDto.version( customer.getVersion().intValue() );
        }
        customerDto.createdDate( dateMapper.asOffsetDateTime( customer.getCreatedDate() ) );
        customerDto.lastModifiedDate( dateMapper.asOffsetDateTime( customer.getLastModifiedDate() ) );
        customerDto.customerName( customer.getCustomerName() );

        return customerDto.build();
    }

    public Customer dtoToCustomer(Customer dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( dto.getId() );
        customer.version( dto.getVersion() );
        customer.createdDate( dto.getCreatedDate() );
        customer.lastModifiedDate( dto.getLastModifiedDate() );
        customer.customerName( dto.getCustomerName() );
        customer.apiKey( dto.getApiKey() );
        Set<BeerOrder> set = dto.getBeerOrders();
        if ( set != null ) {
            customer.beerOrders( new HashSet<BeerOrder>( set ) );
        }

        return customer.build();
    }
}
