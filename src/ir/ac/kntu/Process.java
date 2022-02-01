package ir.ac.kntu;

import java.util.Objects;
import java.util.Random;

public class Process implements Runnable{
    private int id;
    private long arrivalTime;
    private int burstTime;
    private int coreNeeds;
    private State state;
    private int priority;

    public Process(int id, long arrivalTime, int burstTime, int CN, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        coreNeeds = CN;
        this.priority = priority;
        setState(State.NEW);
        //Scheduler.getInstance().schedule(this);
    }

    @Override
    public void run() {
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        System.out.println("process" + id + " : " + n + " times");
        for (int i = 0; i < n; i++) {
            System.out.println("process" + id + " : " + "time" + i);
            int delay = rand.nextInt(1000) + 1000;
            setState(State.READY);
            Scheduler.getInstance().schedule(this);
            while (getState() != State.RUNNING);
            try {
                Thread.sleep(burstTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setState(State.WAITING);
            Scheduler.getInstance().schedule(this);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setState(State.TERMINATED);
        Scheduler.getInstance().schedule(this);
    }

    public void setState(State state) {
        synchronized (state) {
            this.state = state;
        }
    }

    public int getCoreNeeds() {
        return coreNeeds;
    }

    public State getState() {
        synchronized (state) {
            return state;
        }
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
        return "{" +
                "id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", coreNeeds=" + coreNeeds +
                ", state=" + state +
                ", priority=" + priority +
                '}';
    }
}
