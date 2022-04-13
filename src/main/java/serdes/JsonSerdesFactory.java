package serdes;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.Map;

public class JsonSerdesFactory<T> {

    public Serde<T> getSerde(Class<T> t) {
        Map<String, Object> serdeProps = new HashMap<>();
        serdeProps.put("JsonPOJOClass", t);
        final Serializer<T> serializer = new Serializable<>();
        serializer.configure(serdeProps, false);
        final Deserializer<T> deserializer = new Deserializable<>();
        deserializer.configure(serdeProps, false);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}

