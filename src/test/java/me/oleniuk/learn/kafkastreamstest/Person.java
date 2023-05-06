package me.oleniuk.learn.kafkastreamstest;

public record Person(String id) {

    public String anonymize() {
        return "anon_"+id;
    }

}
