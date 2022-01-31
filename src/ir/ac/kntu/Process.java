package ir.ac.kntu;

import java.util.Objects;

public class Process implements Runnable{
    private int arrivalTime;
    private int burstTime;
    private int coreNeeds;
    private State state;
    private int priority;

    public Process(int arrivalTime, int burstTime, int CN, int priority) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        coreNeeds = CN;
        state = State.READY;
        this.priority = priority;
    }

    @Override
    public void run() {
        state = State.RUNNING;
        //Scheduler.getInstance().enterReadyQ(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getCoreNeeds() {
        return coreNeeds;
    }

    public void setCoreNeeds(int coreNeeds) {
        this.coreNeeds = coreNeeds;
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
}
