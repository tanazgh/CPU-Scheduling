package ir.ac.kntu;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {
    private static Scheduler instance = new Scheduler();

    private Scheduler() {
        readyQ = new PriorityQueue<>(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.getPriority()-o2.getPriority();
            }
        });
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
            readyQ.poll();
            return true;
        }
        return false;
    }

    public void schedule() {
        while (true) {
            if (cpu.acquire(readyQ.peek())) {
                Process p = readyQ.poll();

            }
        }
    }
}
