import models.Dependence;
import models.ItemPrice;
import models.ItemPriceWithDepence;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import serdes.Deserializable;
import serdes.JsonSerdesFactory;
import serdes.Serializable;

import java.util.Properties;

public class Main {

    public static String inputTopic=EnvTools.getEnvValue(EnvTools.INPUT_TOPIC , "topicItem");
    public static String inputTopic2=EnvTools.getEnvValue(EnvTools.INPUT_TOPIC2 , "topicDependence");
    public static String outputTopic=EnvTools.getEnvValue(EnvTools.OUTPUT_TOPIC , "outTopic");

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "stream-join-objects-5");
        props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");






    Serde<ItemPrice> itemPriceSerde= new JsonSerdesFactory<ItemPrice>().getSerde(ItemPrice.class);
    Serde<Dependence> DependenceSerde= new JsonSerdesFactory<Dependence>().getSerde(Dependence.class);
    Serde<ItemPriceWithDepence> JoindSerde= new JsonSerdesFactory<ItemPriceWithDepence>().getSerde(ItemPriceWithDepence.class);

    StreamsBuilder streamsBuilder= new StreamsBuilder();

    KStream <String,ItemPrice> streamItems= streamsBuilder.stream(inputTopic,
            Consumed.with(Serdes.String(),itemPriceSerde));

    GlobalKTable<String,Dependence> gTable= streamsBuilder.globalTable(inputTopic2,
            Consumed.with(Serdes.String(), DependenceSerde));

    KStream<String,ItemPriceWithDepence> gTable2= streamsBuilder.stream(outputTopic,
            Consumed.with(Serdes.String(), JoindSerde));


    KStream<String,ItemPriceWithDepence> joined=
            streamItems.join(gTable,
                    (key,value) ->key,
                    (value1,value2)-> {
                return new ItemPriceWithDepence(value1.getPlu(),value1.getDependenceId(),value1.getPrice(),value2.getStrategy());
                    }
            );
        joined.to(outputTopic,Produced.with(Serdes.String(),JoindSerde));
    KafkaStreams streams= new KafkaStreams(streamsBuilder.build(),props);
        streams.start();

}









}

