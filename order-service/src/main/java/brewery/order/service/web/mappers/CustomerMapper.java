package brewery.order.service.web.mappers;

import brewery.model.CustomerDto;
import brewery.order.service.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}