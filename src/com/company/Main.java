package com.company;

import com.processes.CosineIntegralProcess;
import com.processes.LinearIntegralProcess;
import com.processes.Process;
import com.processes.QuadraticIntegralProcess;

import java.sql.Time;
import java.util.ArrayList;

public class Main {
    
    public static ArrayList<Process> createRandomProcesses(int numProcesses){
        ArrayList<Process> processArrayList = new ArrayList<>();
        while (numProcesses!=0){
            double randomNum = Math.random()*10;
            if (randomNum<3){
                processArrayList.add(new QuadraticIntegralProcess(Math.random()*10,Math.random()*10,Math.random()*10,1));
            }
            else if (randomNum<6){
                processArrayList.add(new LinearIntegralProcess(Math.random()*10,Math.random()*10,1));

            }else {
                processArrayList.add(new CosineIntegralProcess(Math.random()*100,2));
            }
            numProcesses--;
        }
        return processArrayList;
    }
 static    public ArrayList<Process> createEqualChargeProcesses(int numProcesses){
        ArrayList<Process> processArrayList = new ArrayList<>();
        int numProcCounter = 0;
        while (numProcesses!=numProcCounter){
            double randomNum = Math.random()*numProcesses;
            if (randomNum<numProcesses/3){
                processArrayList.add(new QuadraticIntegralProcess(Math.random()*10,Math.random()*10,Math.random()*10,1));
            }
            else if (randomNum<numProcesses*2/3){
                processArrayList.add(new LinearIntegralProcess(Math.random()*10,Math.random()*10,1));

            }else {
                processArrayList.add(new CosineIntegralProcess(Math.random()*100,2));
            }
            numProcCounter++;
        }
        return processArrayList;
    }
  static   public ArrayList<Process> createEqualProcess(int processType,int numProcesses){
        ArrayList<Process> processArrayList = new ArrayList<>();
        while (numProcesses!=0){
            double randomNum = Math.random()*10;
            if (processType==1){
                processArrayList.add(new QuadraticIntegralProcess(Math.random()*10,Math.random()*10,Math.random()*10,1));
            }
            else if (processType==2){
                processArrayList.add(new LinearIntegralProcess(Math.random()*10,Math.random()*10,1));

            }else {
                processArrayList.add(new CosineIntegralProcess(Math.random()*100,2));
            }
            numProcesses--;
        }
        return processArrayList;

    }
  static   public void FIFORegular(ArrayList<Process> processes){
        ExecutionUnit unit = new ExecutionUnit();
        long startTime = System.currentTimeMillis();
        for (Process proc:processes
             ) {
            unit.generalExecution(proc,-200,200,10000000);
        }
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("Cantidad de procesos no optimizado:" + processes.size() + "  tiempo:"+finishTime);
    }

   static public void FIFOOptimized(ArrayList<Process> processes){
        ExecutionUnit unit = new ExecutionUnit();
        long startTime = System.currentTimeMillis();

        for (Process proc: processes
        ) {
//            unit.generalExecution(proc,-110,234,10000000);
            if (proc instanceof QuadraticIntegralProcess){
//                System.out.println("Ejecutando quadrtic");
                unit.integralQuadratic(-110,234,10000000,proc);
//                            unit.generalExecution(proc,-110,234,10000000);

            }

            if (proc instanceof LinearIntegralProcess){
//                System.out.println("Ejecutando linear");
//                unit.generalExecution(proc,-110,234,10000000);

                unit.integralLinear(-110,234,10000000,proc);
            }
            if (proc instanceof CosineIntegralProcess){
//                System.out.println("Ejecutando cosine");

                unit.integralCosine(-110,234,10000000,proc);
            }
        }
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("Cantidad de procesos optimizado:" + processes.size() + "  tiempo:"+finishTime);
    }
    public static void main(String[] args) {
        System.out.println("Ejecucion con procesos random");
        int numproc = 100;
        for (int i = numproc; i <= 500; i+=50) {
            ArrayList<Process> processes = createRandomProcesses(i);
            FIFOOptimized(processes);
            FIFORegular(processes);
        }
        System.out.println("Ejecucion con carga igual");
        for (int i = numproc; i <= 500; i+=50) {
            ArrayList<Process> processes = createEqualChargeProcesses(i);
            FIFOOptimized(processes);
            FIFORegular(processes);
        }
        System.out.println("Ejecucion con cosenos");

        for (int i = numproc; i <= 500; i+=50) {
            ArrayList<Process> processes = createEqualProcess(3,i);
            FIFOOptimized(processes);
            FIFORegular(processes);
        }
        System.out.println("Ejecucion con cuadraticas");

        for(int i = numproc; i <= 500; i+=50){
            ArrayList<Process> processes = createEqualProcess(1,i);
            FIFOOptimized(processes);
            FIFORegular(processes);
        }
        System.out.println("Ejecucion con lineales");

        for (int i = numproc; i <= 500; i+=50) {
            ArrayList<Process> processes = createEqualProcess(2,i);
            FIFOOptimized(processes);
            FIFORegular(processes);
        }
    }
}
