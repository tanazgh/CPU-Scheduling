package ir.ac.kntu;

import java.util.List;
import java.util.Queue;

public class Scheduler {
    private Scheduler instance = new Scheduler();

    public Scheduler getInstance() {
        return instance;
    }

    private Scheduler() {

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
        if (p.equals(readyQ.poll())) {
            return true;
        }
        readyQ.add(p);
        return false;
    }
}
