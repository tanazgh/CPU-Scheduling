package ir.ac.kntu;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {
    private static Scheduler instance = new Scheduler();

    private Scheduler() {
        readyQ = new PriorityQueue<>();
    }

    public static Scheduler getInstance() {
        return instance;
    }

    private Queue<Process> readyQ;
    private CPU cpu;

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public CPU getCpu() {
        return cpu;
    }

    public boolean enterReadyQ(Process p) {
        return readyQ.add(p);
    }

    public boolean exitReadyQ(Process p) {
        if (p.equals(readyQ.peek())) {
            return true;
        }
        return false;
    }

    public void schedule() {
        while (true) {
            if (cpu.lock(readyQ.peek())) {
                Process p = readyQ.poll();

            }
        }
    }
}
