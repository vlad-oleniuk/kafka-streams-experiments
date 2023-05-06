package me.oleniuk.learn.kafkastreamstest;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

public class PersonSerde extends Serdes.WrapperSerde<Person> {

    public PersonSerde() {
        super(new PersonSerializer(), new PersonDeserializer());
    }

    public static class PersonSerializer implements Serializer<Person> {

        @Override
        public byte[] serialize(String topic, Person data) {
            throw new UnsupportedOperationException("Why are you calling me?");
        }
    }


    public static class PersonDeserializer implements Deserializer<Person> {

        @Override
        public Person deserialize(String topic, byte[] data) {
            return new Person(new String(data));
        }
    }

}
