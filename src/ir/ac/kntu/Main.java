package ir.ac.kntu;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU(4);
        Scheduler.getInstance().setCpu(cpu);
        Process process = new Process(LocalTime.now().getNano(), 3000, 3, 1);
        Process process1 = new Process(LocalTime.now().getNano(), 2000, 2, 2);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(cpu.toString());
            }
        }, 0, 1000);

    }
}
