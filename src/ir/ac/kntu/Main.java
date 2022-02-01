package ir.ac.kntu;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CPU cpu = new CPU(4);
        Scheduler.getInstance().setCpu(cpu);
        Process process1 = new Process(1, LocalTime.now().getNano(), 4000, 3, 1);
        Scheduler.getInstance().executeProcess(process1);
        Process process2 = new Process(2, LocalTime.now().getNano(), 5000, 2, 3);
        Scheduler.getInstance().executeProcess(process2);
        Process process3 = new Process(3, LocalTime.now().getNano(), 3000, 1, 2);
        Scheduler.getInstance().executeProcess(process3);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(cpu);
                System.out.println(Scheduler.getInstance().getReadyQ().toString() + "\n");
            }
        }, 0, 500);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        Scheduler.getInstance().shutdown();
    }
}
