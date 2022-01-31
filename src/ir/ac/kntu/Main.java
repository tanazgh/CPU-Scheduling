package ir.ac.kntu;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU(4);
        Scheduler.getInstance().setCpu(cpu);
        Process process = new Process(LocalTime.now().getNano(), 300, 3, 1);
        Process process1 = new Process(LocalTime.now().getNano(), 200, 2, 2);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Available Cores: "+ cpu.getAvailableCores());
                System.out.println("Busy Cores: " + (cpu.getCores()-cpu.getAvailableCores()));
                System.out.println("Critical Section: " + cpu.getCS().toString());
            }
        }, 100);
    }
}
