package ir.ac.kntu;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler {
    private static final Scheduler instance = new Scheduler();

    private PriorityQueueWrapper readyQ;
    private CPU cpu;

    private Scheduler() {
        readyQ = new PriorityQueueWrapper();
    }

    public static Scheduler getInstance() {
        return instance;
    }

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

    public void schedule(Process process) {
        State s = process.getState();
        if(s == State.READY) {
            readyQ.add(process);
            while (cpu.acquire(readyQ.peek())) {
                Process p = readyQ.poll();
                p.setState(State.RUNNING);
            }
        }
        if (s == State.TERMINATED){
            cpu.release(process);
        }
    }
}
