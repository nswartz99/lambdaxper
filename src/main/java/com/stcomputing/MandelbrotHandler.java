package com.stcomputing;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

// Handler value: example.Handler
public class MandelbrotHandler implements RequestHandler<Map<String,String>, String> {
  static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  LambdaLogger logger;
  @Override
  public String handleRequest(Map<String,String> event, Context context) {
    logger = context.getLogger();
    int iterations = Integer.parseInt(event.get("iterations"));
    int dimension = Integer.parseInt(event.get("dimension"));
    double xStart = Double.parseDouble(event.get("xStart"));
    double yStart = Double.parseDouble(event.get("yStart"));
    double xEnd = Double.parseDouble(event.get("xEnd"));
    double yEnd = Double.parseDouble(event.get("yEnd"));
    logger.log("Parameters: " + gson.toJson(event));
    logger.log("Iterations:" + iterations + ", Dimension:" + dimension + ", xStart:" + xStart + ", yStart:" + yStart + ", xEnd:" + xEnd + ", yEnd:" + yEnd);
    return iterate(iterations, dimension, xStart, yStart, xEnd, yEnd);
  }

  public String iterate(int count, int dim, Double xStart, Double yStart, Double xEnd, Double yEnd) {
    Double domain = xEnd - xStart;
    Double range = yEnd - yStart;
    Double[][] result = new Double[dim+1][dim+1];
    StringBuilder sr = new StringBuilder();
    for (int i=0; i < result.length; i++) {
        for (int j=0; j < result[i].length; j++) {
            Double ii = 0.0;
            Double jj = 0.0;
            int k = 0;
            for (; k < count && ii < 1.0E10 && jj < 1.0E10; k++) {
//                logger.log(" i:" + ii + " j:" + jj + " cX:" + i*domain/dim + " cY:" + j*range/dim + "//");
                Double iiSave = ii;
                ii = ii*ii - jj*jj + xStart + i*domain/dim;
                jj = 2*iiSave*jj + yStart + j*range/dim;
            }
//                    result[i][j] = Math.sqrt(ii*ii + jj*jj);
            result[i][j] = k*1.0/count;
            sr.append(result[i][j] + " ");
//                    System.out.print("\n" + i+","+j + " i:" + ii + " j:" + jj + " cX:" + i*domain/dim + " cY:" + j*range/dim);
//            logger.log(" res:" + result[i][j]);
        }
        sr.deleteCharAt(sr.length()-1);
        sr.append(",");
    }
    sr.deleteCharAt(sr.length()-1);
    return sr.toString();
  }
}