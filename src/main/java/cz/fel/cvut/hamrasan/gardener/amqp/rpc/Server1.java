package cz.fel.cvut.hamrasan.gardener.amqp.rpc;

import cz.fel.cvut.hamrasan.gardener.service.SensorsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@RabbitListener(queues = "tut.rpc.pressure")
public class Server1 {

    @Autowired
    private SensorsService sensorsService;

    @RabbitHandler
    public void pressure(String n ) {
        System.out.println(" [x] Received pressure in hPa is " + n);
//        System.out.println(" [x] Received pressure is " + key);
//        rpcService.savePress(n,key);
//         savePress(n,key);


    }
}
