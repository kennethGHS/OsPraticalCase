package com.processes;
import java.lang.*;

public class CosineIntegralProcess extends Process {
double multiplyConstant;
final int resources = 2;

public CosineIntegralProcess(double multiplyConstant, int procResource){
        this.procResource = procResource;
        this.multiplyConstant = multiplyConstant;
    }
    public double evaluateFunction(double x){
        return multiplyConstant* Math.cos(x);

    }
    public double returnPeriodDown(double x,double x2){
    while (x+2*Math.PI<x2){
        x = x +2*Math.PI;
    }
    return x;
    }
}
