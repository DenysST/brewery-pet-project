package brewery.order.service.sm.actions;

import brewery.order.service.config.JmsConfig;
import brewery.order.service.domain.BeerOrder;
import brewery.order.service.domain.BeerOrderEventEnum;
import brewery.order.service.domain.BeerOrderStatusEnum;
import brewery.order.service.repositories.BeerOrderRepository;
import brewery.order.service.services.BeerOrderManagerImpl;
import brewery.order.service.web.mappers.BeerOrderMapper;
import brewery.model.events.ValidateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
                    .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                    .build());

        log.debug("Sent Validation request to queue for order id " + beerOrderId);
    }
}
