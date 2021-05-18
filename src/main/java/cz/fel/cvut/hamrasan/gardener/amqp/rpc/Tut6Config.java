package cz.fel.cvut.hamrasan.gardener.amqp.rpc;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author Gary Russell
 * @author Scott Deeg
 *
 */
@Profile({"tut6","rpc"})
@Configuration
public class Tut6Config {

//@Bean
//public TopicExchange topic() {
//    return new TopicExchange("amq.topic");
//}

    @Profile("client")
    private static class ClientConfig {

//        @Bean(name="QueueCommmand")
//        @Primary
//        public Queue queueComm() {
////            mqtt-subscription-ESP_Gardenqos0
//            return new Queue("mqtt-subscription-ESP_Gardenqos0");
//        }

        @Bean(name="ExchangeCommmand")
        public TopicExchange exchangeComm() {
            return new TopicExchange("tut.topic");
        }
//
//        @Bean
//        public Binding binding2(TopicExchange exchange,
//                                Queue queue) {
//            return BindingBuilder.bind(queue).to(exchange).with("tut.commands");
//        }

        @Bean
        public Tut6Client client() {
            return new Tut6Client();
        }
    }

    @Profile("server")
    private static class ServerConfig {

        @Bean(name="QueueTemperature")
        public Queue queueTemp() {
            return new Queue("tut.rpc.temperature");
        }

        @Bean(name="QueueRain")
        public Queue queueRain() {
            return new Queue("tut.rpc.rain");
        }

        @Bean(name="QueueRes")
        public Queue queueRes() {
            return new Queue("tut.rpc.response");
        }

        @Bean(name="QueueHumidity")
        public Queue queueHum() {
            return new Queue("tut.rpc.humidity");
        }

        @Bean(name="QueuePressure")
        public Queue queuePress() {
            return new Queue("tut.rpc.pressure");
        }

        @Bean(name="QueueSoil")
        public Queue queueSoil() {
            return new Queue("tut.rpc.soil");
        }

        @Bean
        public Tut6Server server() {
            return new Tut6Server();
        }

    }



}