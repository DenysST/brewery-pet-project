package brewery.inventory.service.services;

import brewery.model.BeerOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order-service")
public interface OrderServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET,  value = "/api/v1/customers/{customerId}/orders/{orderId}")
    BeerOrderDto getOrderById(@PathVariable String customerId, @PathVariable String orderId);
}