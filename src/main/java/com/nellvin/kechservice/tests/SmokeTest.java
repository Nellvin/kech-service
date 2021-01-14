package com.nellvin.kechservice.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.nellvin.kechservice.controler.EventController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private EventController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}