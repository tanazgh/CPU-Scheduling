package ir.ac.kntu;

import java.util.List;
import java.util.Objects;

public class Process implements Runnable{
    private int arrivalTime;
    private int burstTime;
    private List<Core> coreNeeds;
    private String state;

    public Process(int arrivalTime, int burstTime, List<Core> CN) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        coreNeeds = CN;
        state = "waiting";
    }

    @Override
    public void run() {
        state = "ready";
        Scheduler.getInstance().enterReadyQ(this);
    }

    public void setState(String state) {
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

    public List<Core> getCoreNeeds() {
        return coreNeeds;
    }

    public void setCoreNeeds(List<Core> coreNeeds) {
        this.coreNeeds = coreNeeds;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return arrivalTime == process.arrivalTime && burstTime == process.burstTime && coreNeeds.equals(process.coreNeeds) && state.equals(process.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, burstTime, coreNeeds, state);
    }
}
