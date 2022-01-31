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

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public Integer getAvailableCores() {
        return availableCores;
    }

    public void setAvailableCores(Integer availableCores) {
        this.availableCores = availableCores;
    }

    public List<Process> getCS() {
        return CS;
    }

    public void setCS(List<Process> CS) {
        this.CS = CS;
    }
}
