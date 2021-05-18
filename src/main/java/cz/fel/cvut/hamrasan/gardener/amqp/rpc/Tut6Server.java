package cz.fel.cvut.hamrasan.gardener.amqp.rpc;

import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.service.SensorsService;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * @author Gary Russell
 * @author Scott Deeg
 */
public class Tut6Server {

    @Autowired
    private SensorsService sensorsService;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Tut6Server.class);


    @RabbitListener(queues = "tut.rpc.temperature")
//    @SendTo("tut.rpc.commands")
//    used when the client doesn't set replyTo.
    public void temperature(@Payload String n,
                              @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key) throws NotFoundException {
        System.out.println(" [x] Received temperature for " + n);
        System.out.println(" [x] Received temperature is " + key);
//        String result = fib(n);
        try {
            sensorsService.saveTemperatue(n,key);
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "tut.rpc.humidity")
    public void humidity(@Payload String n,
                         @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key) {
        System.out.println(" [x] Received humidity is " + n);
        System.out.println(" [x] Received humidity is " + key);
        try {
            sensorsService.saveHumidity(n,key);
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "tut.rpc.soil")
    public void soilMoisture(@Payload String n,
                         @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key) {
        System.out.println(" [x] Received soil is " + n);
        System.out.println(" [x] Received soil is " + key);
        try {
            sensorsService.saveSoil(n,key);
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "tut.rpc.pressure")
    public void pressure(@Payload String n ,@Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key) {
        System.out.println(" [x] Received pressure in hPa is " + n);
        System.out.println(" [x] Received pressure is in" + key);
        try {
            sensorsService.savePress(n,key);
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "tut.rpc.rain")
    public void raining(@Payload String n ,@Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key) {
        System.out.println(" [x] It is " + n + " raining in " + key);
        try {
            sensorsService.saveRain(n,key);
        }
        catch (Exception e){
            LOG.error(e.getMessage());
        }
    }

    @RabbitListener(queues = "tut.rpc.response")
//    @SendTo("tut.rpc.commands")
//    used when the client doesn't set replyTo.
    public void responses(String n) {
        System.out.println(" [x] Received request for " + n);
    }


    private void savePress(String n, String key){
//        float press = Float.parseFloat(n);
        String s = "";
        for (int i = 7; i < key.length(); i++) {
            s = s + n.charAt(i);
        }
//        long id = Long.parseLong(s);
    }

}