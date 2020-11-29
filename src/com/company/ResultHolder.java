package com.company;

public class ResultHolder {
    public double result;
    public ResultHolder(){
        this.result = 0;
    }
    public synchronized void increaseResult( double value){
        this.result+=value;
    }

}
