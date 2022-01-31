package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class CPU {
    private int cores;
    private Integer availableCores;
    private List<Process> CS;

    public CPU(int cores) {
        this.cores = cores;
        availableCores = cores;
        CS = new ArrayList<>();
    }

    public boolean acquire(Process p) {
        CS.add(p);
        synchronized (availableCores) {
            if (availableCores > 0 && availableCores - p.getCoreNeeds()>0) {
                availableCores = availableCores - p.getCoreNeeds();
                return true;
            }
            return false;
        }
    }

    public boolean release(Process p) {
        synchronized (availableCores) {
            if (CS.contains(p)) {
                availableCores = availableCores + p.getCoreNeeds();
                CS.remove(p);
                return true;
            }
            return false;
        }
    }

}
