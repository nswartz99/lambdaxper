package com.stcomputing;

import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

class InvokeTest {
  private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);

  @Test
  void invokeTest() {
    logger.info("Invoke TEST");
    HashMap<String,String> event = new HashMap<String,String>();
    event.put("iterations", "3");
    event.put("dimension", "5");
    event.put("xStart", "0.344");
    event.put("yStart", "0.34");
    event.put("xEnd", "0.39");
    event.put("yEnd", "0.39");
    Context context = new TestContext();
    LambdaXperHandler handler = new LambdaXperHandler();
    String result = handler.handleRequest(event, context);
    logger.info("Result was {}", result);
    assertTrue(result.contains("Iterations"), "Result was incorrect:" + result);
  }

  @Test
  void invokeMandelbrotTest() {
    logger.info("Invoke mandelbrot");
    HashMap<String,String> event = new HashMap<String,String>();
    event.put("iterations", "50");
    event.put("dimension", "20");
    event.put("xStart", "0.344");
    event.put("yStart", "0.34");
    event.put("xEnd", "0.39");
    event.put("yEnd", "0.39");
    Context context = new TestContext();
    MandelbrotHandler handler = new MandelbrotHandler();
    String result = handler.handleRequest(event, context);
    logger.info("Result was {}", result);
    assertTrue(result.contains("1.0 1.0 1.0"), "Result was incorrect:" + result);
  }

}
