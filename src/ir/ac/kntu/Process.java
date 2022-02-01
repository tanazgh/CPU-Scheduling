package ir.ac.kntu;

import java.util.Objects;
import java.util.Random;

public class Process implements Runnable{
    private long arrivalTime;
    private int burstTime;
    private int coreNeeds;
    private State state;
    private int priority;

    public Process(long arrivalTime, int burstTime, int CN, int priority) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        coreNeeds = CN;
        this.priority = priority;
        setState(State.READY);
        Scheduler.getInstance().schedule(this);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(burstTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state = State.TERMINATED;
        Scheduler.getInstance().schedule(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCoreNeeds() {
        return coreNeeds;
    }

    public State getState() {
        return state;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return arrivalTime == process.arrivalTime && burstTime == process.burstTime &&
                coreNeeds == process.coreNeeds && state.equals(process.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, burstTime, coreNeeds, state);
    }

    @Override
    public String toString() {
        return "Process{" +
                "arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", coreNeeds=" + coreNeeds +
                ", state=" + state +
                ", priority=" + priority +
                '}';
    }
}
