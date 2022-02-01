package ir.ac.kntu;

import com.sun.source.tree.CaseTree;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Scheduler {
    private static final Scheduler instance = new Scheduler();
    private ThreadPoolExecutor threadPoolExecutor;

    private PriorityQueueWrapper readyQ;
    private CPU cpu;

    private Scheduler() {
        readyQ = new PriorityQueueWrapper();
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public static Scheduler getInstance() {
        return instance;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void shutdown(){
        threadPoolExecutor.shutdown();
    }

    public void schedule(Process process) {
        State s = process.getState();
        if(s == State.READY) {
            readyQ.add(process);
        }
        if (s == State.TERMINATED || s == State.WAITING){
            cpu.release(process);
        }
        Boolean flag = false;
        synchronized (flag) {
            flag = false;
        }
        while (readyQ.size()!= 0 && cpu.acquire(readyQ.peek())) {
            Process p = readyQ.poll();
            p.setState(State.RUNNING);
            if (process == p){
                synchronized (flag) {
                    flag = true;
                }
            }
        }
        if(!flag && s == State.READY){
            System.out.println("Sorry process"  + process.getId() + " CPU is busy now!");
        }
    }

    public void executeProcess(Process p){
        threadPoolExecutor.execute(p);
    }

    public PriorityQueueWrapper getReadyQ() {
        synchronized (readyQ) {
            return readyQ;
        }
    }
}
