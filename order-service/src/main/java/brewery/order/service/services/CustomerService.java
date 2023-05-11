package brewery.order.service.services;

import brewery.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);

}