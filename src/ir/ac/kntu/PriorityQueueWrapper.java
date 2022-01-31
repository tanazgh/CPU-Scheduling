package ir.ac.kntu;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueWrapper {
    private final PriorityQueue<Process> processes;

    public PriorityQueueWrapper(){
        processes = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
    }

    public boolean add(Process p){
        synchronized (processes) {
            return processes.add(p);
        }
    }

    public Process remove(){
        synchronized (processes) {
            return processes.remove();
        }
    }

    public boolean remove(Process p){
        synchronized (processes) {
            return processes.remove(p);
        }
    }

    public Process poll(){
        synchronized (processes) {
            return processes.poll();
        }
    }

    public Process peek(){
        synchronized (processes) {
            return processes.peek();
        }
    }

    public int size(){
        synchronized (processes) {
            return processes.size();
        }
    }

    @Override
    public String toString() {
        synchronized (processes) {
            return "ReadyQueue{" +
                    "processes=" + processes +
                    '}';
        }
    }
}
