package com.company;

import com.processes.*;
import com.processes.Process;
import com.processes.LinearIntegralProcess;
import com.processes.CosineIntegralProcess;
import com.processes.QuadraticIntegralProcess;

import java.util.ArrayList;

import static java.lang.Double.NaN;

public class ExecutionUnit {
    public int resources;
    public void executeGeneralThread(ResultHolder holder, Process process, double beginning, double end, double divisions) {
        holder.increaseResult(generalExecution(process, beginning, end, divisions));
    }
    public double integralQuadratic(double beginning, double ending, int divisions, Process process) {
        QuadraticIntegralProcess quadraticIntegralProcess = (QuadraticIntegralProcess) process;
        ArrayList<Double> zeros = quadraticIntegralProcess.getZeros();
        if (zeros.size() == 0) {
            return generalExecution(process, beginning, ending, divisions);
        } else if (zeros.size() == 1) {
            if (zeros.get(0) > ending || zeros.get(0) < beginning) {
                return generalExecution(process, beginning, ending, divisions);
            }
            if (zeros.get(0) == ending || zeros.get(0) == beginning) {
                return generalExecution(process, beginning, ending, divisions);
            }
            ResultHolder holder = new ResultHolder();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process, beginning, zeros.get(0), divisions * (Math.abs(zeros.get(0) - beginning)/Math.abs(ending - beginning) ));
                }
            };
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process, zeros.get(0), ending, divisions * (Math.abs(ending - zeros.get(0))/Math.abs(ending - beginning) ));
                }
            };
            Thread thread = new Thread(runnable);
            Thread thread1 = new Thread(runnable1);
            thread.start();
            thread1.start();
            try {
                thread.join();
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return holder.result;

        } else {
            ResultHolder holder = new ResultHolder();
            if ((zeros.get(0) == ending  )&&(zeros.get(1) == beginning)){
                return generalExecution(process, beginning, ending, divisions);
            }
            if (zeros.get(0)==ending){
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        executeGeneralThread(holder, process, beginning, zeros.get(1), divisions * ( Math.abs(zeros.get(1) - beginning)/Math.abs(ending - beginning) ));
                    }
                };
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        executeGeneralThread(holder, process, zeros.get(1), ending, divisions * (Math.abs(ending - zeros.get(1))/Math.abs(ending - beginning)  ));
                    }
                };
                Thread thread = new Thread(runnable);
                Thread thread1 = new Thread(runnable1);
                thread.start();
                thread1.start();
                try {
                    thread.join();
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return holder.result;
            }
            else if (zeros.get(1)==beginning){
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        executeGeneralThread(holder, process, beginning, zeros.get(0), divisions * ( Math.abs(zeros.get(0) - beginning)/Math.abs(ending - beginning) ));
                    }
                };
                Runnable runnable1 = new Runnable() {
                    @Override
                    public void run() {
                        executeGeneralThread(holder, process, zeros.get(0), ending, divisions * (Math.abs(ending - zeros.get(0))/Math.abs(ending - beginning)));
                    }
                };
                Thread thread = new Thread(runnable);
                Thread thread1 = new Thread(runnable1);
                thread.start();
                thread1.start();
                try {
                    thread.join();
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return holder.result;
            }

//_____________________________________________________________
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process, beginning, zeros.get(1), divisions * (Math.abs(zeros.get(1) - beginning)/Math.abs(ending - beginning) ));
                }
            };
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process,zeros.get(1) , zeros.get(0), divisions * (Math.abs(zeros.get(0) - zeros.get(1))/Math.abs(ending - beginning) ));
                }
            };
            Runnable runnable2 = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process, zeros.get(0), ending, divisions * ( Math.abs(ending - zeros.get(0))/Math.abs(ending - beginning) ));
                }
            };
            Thread thread = new Thread(runnable);
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);
            //System.out.println("zero");
            //System.out.println( zeros.get(0));
            try {
                thread.start();
                thread1.start();
                thread2.start();
                thread2.join();
                thread.join();
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return holder.result;

        }
    }
    public double integralLinear(double beginning, double ending, int divisions, Process process){
        //System.out.println("Ejecutando linear");
        LinearIntegralProcess linearIntegralProcess = (LinearIntegralProcess) process;
        ArrayList<Double> zeros = linearIntegralProcess.getZeros();
        if (zeros.get(0)<beginning || zeros.get(0 ) > ending){
            return generalExecution( process,  beginning,  ending,  divisions);
        }
        else {
            ResultHolder holder = new ResultHolder();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process, beginning, zeros.get(0), divisions * (Math.abs(zeros.get(0) - beginning)/ Math.abs(ending - beginning) ));
                }
            };
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    executeGeneralThread(holder, process, zeros.get(0), ending, divisions * (Math.abs(ending - zeros.get(0))/Math.abs(ending - beginning)  ));
                }
            };
            //System.out.println("fin linear");

            Thread thread = new Thread(runnable);
            Thread thread1 = new Thread(runnable1);
            thread.start();
            thread1.start();
            try {
//                //System.out.println( (Math.abs(ending - zeros.get(0))/Math.abs(ending - beginning)  ));
//                //System.out.println("El factor es :" + Math.abs(ending - zeros.get(0)) );
//                //System.out.println(beginning);
//                //System.out.println(ending);
//                //System.out.println(zeros.get(0));
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            //System.out.println("fin linear");

            return holder.result;
        }
    }

    public double integralCosine(double beginning, double ending, int divisions, Process process) {
        CosineIntegralProcess cosineIntegralProcess = (CosineIntegralProcess) process;
        double beginning2 = cosineIntegralProcess.returnPeriodDown(beginning, ending);
        double interval = Math.abs(ending - beginning);
        double newInterval = Math.abs(ending - beginning2);
        double newDivisions = (newInterval / interval) * divisions;
        return generalExecution(process, beginning2, ending, newDivisions);


    }

    public double generalExecution(Process process, double beginning, double ending, double divisions) {
        if (beginning>ending){
//            //System.out.println("Error ending menor que inicio");
            return 0;
        }
        double sum = 0;
        double divisionLen = (ending - beginning) / divisions;
        while (beginning < ending) {
            sum += ((divisionLen) * process.evaluateFunction(beginning));
            beginning += divisionLen;
        }
        return sum;
    }
    public double generalExecutionThreaded(Process process, double beginning, double ending, double divisions, int resources) {
        if (beginning>ending){
//            //System.out.println("Error ending menor que inicio");
            return 0;
        }
        double sum = 0;
        double divisionLen = (ending - beginning) / divisions;
        while (beginning < ending) {
            sum += ((divisionLen) * process.evaluateFunction(beginning));
            beginning += divisionLen;
        }
        return sum;
    }
}
