package com.processes;

import java.util.ArrayList;

public class LinearIntegralProcess extends Process {
    double constantA;
    double constantB;
    public LinearIntegralProcess(double constantA, double constantB, int procResource){
        this.constantA = constantA;
        this.constantB = constantB;
        this.procResource = procResource;
    }
    public double evaluateFunction(double x){
        return x*constantA +constantB;
    }
    public ArrayList<Double> getZeros(){
        ArrayList<Double>integers =  new ArrayList<>( );
        integers.add((double)-constantB/constantA);
        return integers;
    }
}
