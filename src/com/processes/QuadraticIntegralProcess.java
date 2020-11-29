package com.processes;

import java.util.ArrayList;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static java.lang.Math.sqrt;

public class QuadraticIntegralProcess extends Process{
    double constantC;
    double constantB;
    double constantA;
    public QuadraticIntegralProcess(double constantA, double constantB, double constantC, int procResource) {
        this.constantB = constantB;
        this.constantA = constantA;
        this.constantC = constantC;
        this.procResource = procResource;

    }
    public double evaluateFunction(double x){
        return x*x*constantA +x*constantB + constantC;
    }
    public ArrayList<Double> getZeros(){
        ArrayList<Double> integers = new ArrayList<>();
        try{
            double firstx = (-constantB + sqrt(constantB*constantB - 4*constantA*constantC))/(2*constantA);
            if (!isNaN(firstx)) {
                integers.add(firstx);
            }}
        catch (Exception e){}
        try{double secondx = (-constantB - sqrt(constantB*constantB - 4*constantA*constantC))/(2*constantA);
            if (!isNaN(secondx)){
                integers.add(secondx);};}
        catch (Exception e){}
        return integers;

    }
}
