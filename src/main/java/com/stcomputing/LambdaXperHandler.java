package com.stcomputing;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

// Handler value: example.Handler
public class LambdaXperHandler implements RequestHandler<Map<String,String>, String>{
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  @Override
  public String handleRequest(Map<String,String> event, Context context)
  {
    LambdaLogger logger = context.getLogger();
    int iterations = Integer.parseInt(event.get("iterations"));
    int dimension = Integer.parseInt(event.get("dimension"));
    double xStart = Double.parseDouble(event.get("xStart"));
    double yStart = Double.parseDouble(event.get("yStart"));
    double xEnd = Double.parseDouble(event.get("xEnd"));
    double yEnd = Double.parseDouble(event.get("yEnd"));
    logger.log("Parameters: " + gson.toJson(event));
    return "Iterations:" + iterations + ", Dimension:" + dimension + ", xStart:" + xStart + ", yStart:" + yStart + ", xEnd:" + xEnd + ", yEnd:" + yEnd;
  }
}