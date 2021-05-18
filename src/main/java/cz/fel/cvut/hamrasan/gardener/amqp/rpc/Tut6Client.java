package cz.fel.cvut.hamrasan.gardener.amqp.rpc;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
@Service
public class Tut6Client {

    @Autowired
    private RabbitTemplate template;

//    @Autowired
//    @Qualifier("QueueCommmand")
//    private Queue queue;

//    @Autowired
//    @Qualifier("ExchangeCommmand")
//    private TopicExchange exchange;


//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
//    public void send() {
//        System.out.println(" [x] Requesting fib(" + start + ")");
//        String message = "measure";
//        template.convertAndSend("amq.topic","commands", message );
//        start++;

        //this.template.convertAndSend(queue.getName(), message);
       // Integer response = (Integer) template.convertSendAndReceive(exchange.getName(), "rpc", start++);
        //System.out.println(" [.] Got '" + response + "'");
//    }

    public void setMeassureMinutes(String minutes) {
        System.out.println(" [x] Requesting to rabbit minutes(" + minutes + ")");
        String message = "M" + minutes;
        template.convertAndSend("amq.topic","commands", message );
    }

}