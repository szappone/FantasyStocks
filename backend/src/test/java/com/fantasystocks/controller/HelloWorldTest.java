package com.fantasystocks.controller;

import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class HelloWorldTest extends EasyMockSupport {

    @Test
    public void testHelloWorld() {
        assertEquals("Hello World", HelloWorldController.getHelloWorld());
    }
}
