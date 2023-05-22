package brewery.order.service.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String VALIDATE_ORDER_QUEUE = "validate-order";
    public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-response";
    public static final String ALLOCATE_ORDER_QUEUE = "allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate-order-response";
    public static final String ALLOCATE_FAILURE_QUEUE = "allocation-failure";
    public static final String DEALLOCATE_ORDER_QUEUE = "deallocate-order" ;

    @Bean
    public Queue queueAllocateOrder() {
        return new Queue(ALLOCATE_ORDER_QUEUE);
    }

    @Bean
    public Queue queueAllocateOrderResponse() {
        return new Queue(ALLOCATE_ORDER_RESPONSE_QUEUE);
    }

    @Bean
    public Queue queueValidateOrder() {
        return new Queue(VALIDATE_ORDER_QUEUE);
    }

    @Bean
    public Queue queueValidateOrderResponse() {
        return new Queue(VALIDATE_ORDER_RESPONSE_QUEUE);
    }

    @Bean
    public Queue queueAllocateFailure() {
        return new Queue(ALLOCATE_FAILURE_QUEUE);
    }

    @Bean
    public Queue queueDeallocateOrder() {
        return new Queue(DEALLOCATE_ORDER_QUEUE);
    }


    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
