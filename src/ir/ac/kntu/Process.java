package ir.ac.kntu;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;

public class Process implements Runnable{
    private Integer id;
    private long arrivalTime;
    private int burstTime;
    private int coreNeeds;
    private State state;
    private int priority;
    private Integer request;

    public Process(int id, long arrivalTime, int burstTime, int CN, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        coreNeeds = CN;
        this.priority = priority;
        this.request = 0;
        setState(State.NEW);
        //Scheduler.getInstance().schedule(this);
    }

    @Override
    public void run() {
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;
        System.out.println("process" + id + " : " + n + " requests");
        for (int i = 0; i < n; i++) {
            incRequest();
            int delay = rand.nextInt(1000) + 1000;
            arrivalTime = LocalTime.now().getNano();
            System.out.println("process" + id + " : " + "request" + i);
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

    public int getRequest(){
        synchronized (request) {
            return request;
        }
    }

    public void incRequest(){
        synchronized (request){
            request++;
        }
    }

    public int getPriority() {
        return priority;
    }

    public int getId(){
        synchronized (id){
            return id;
        }
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
                ", request=" + getRequest() +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", coreNeeds=" + coreNeeds +
                ", state=" + state +
                ", priority=" + priority +
                '}';
    }
}
