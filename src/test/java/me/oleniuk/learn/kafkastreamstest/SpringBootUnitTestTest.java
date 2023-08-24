package me.oleniuk.learn.kafkastreamstest;


import me.oleniuk.learn.kafkastreamstest.domain.MyBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@UnitTest("me.oleniuk.learn.kafkastreamstest.domain")
public class SpringBootUnitTestTest {

    @Autowired
    private MyBean myBean;

    @Test
    public void testUnitTestAnnotation() {
        Assertions.assertNotNull(myBean);
    }



}
