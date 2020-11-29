package com.company;

import com.processes.CosineIntegralProcess;
import com.processes.LinearIntegralProcess;
import com.processes.Process;
import com.processes.QuadraticIntegralProcess;

import java.sql.Time;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            if (i<=99){
                processes.add(new QuadraticIntegralProcess(Math.random()*10,Math.random()*10,Math.random()*10,1));
            }
            if (i>99&&i<=199){
                processes.add(new LinearIntegralProcess(Math.random()*10,Math.random()*10,1));

            }
            if (i>199){
                processes.add(new CosineIntegralProcess(Math.random()*100,2));
            }
        }
ExecutionUnit unit = new ExecutionUnit();
        long startTime = System.currentTimeMillis();

        for (Process proc: processes
             ) {
//            unit.generalExecution(proc,-110,234,50000000);
            if (proc instanceof QuadraticIntegralProcess){
                System.out.println("Ejecutando quadrtic");
                unit.integralQuadratic(-110,234,50000000,proc);
//                            unit.generalExecution(proc,-110,234,50000000);

            }

            if (proc instanceof LinearIntegralProcess){
                System.out.println("Ejecutando linear");
//                unit.generalExecution(proc,-110,234,50000000);

                unit.integralLinear(-110,234,50000000,proc);
            }
            if (proc instanceof CosineIntegralProcess){
                System.out.println("Ejecutando cosine");

                unit.integralCosine(-110,234,50000000,proc);
            }
        }
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println(finishTime);
//68852
    }
}
