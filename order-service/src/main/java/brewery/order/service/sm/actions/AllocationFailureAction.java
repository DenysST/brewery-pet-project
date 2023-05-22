package brewery.order.service.sm.actions;

import brewery.order.service.config.RabbitConfig;
import brewery.order.service.domain.BeerOrderEventEnum;
import brewery.order.service.domain.BeerOrderStatusEnum;
import brewery.order.service.services.BeerOrderManagerImpl;
import brewery.model.events.AllocationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);

        rabbitTemplate.convertAndSend(RabbitConfig.ALLOCATE_FAILURE_QUEUE, AllocationFailureEvent.builder()
            .orderId(UUID.fromString(beerOrderId))
                    .build());

        log.debug("Sent Allocation Failure Message to queue for order id " + beerOrderId);
    }
}
