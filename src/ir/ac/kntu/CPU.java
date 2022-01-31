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
        synchronized (availableCores) {
            if (availableCores > 0 && availableCores - p.getCoreNeeds()>0) {
                availableCores = availableCores - p.getCoreNeeds();
                synchronized (CS) {
                    CS.add(p);
                }
                return true;
            }
            return false;
        }
    }

    public boolean release(Process p) {
        synchronized (availableCores) {
            if (CS.contains(p)) {
                availableCores = availableCores + p.getCoreNeeds();
                synchronized (CS) {
                    CS.remove(p);
                }
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
        synchronized (availableCores) {
            return availableCores;
        }
    }

    public void setAvailableCores(Integer availableCores) {
        this.availableCores = availableCores;
    }

    public List<Process> getCS() {
        synchronized (CS) {
            return CS;
        }
    }

    public void setCS(List<Process> CS) {
        this.CS = CS;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "\ncores=" + cores +
                "\navailableCores=" + getAvailableCores() +
                "\nbusyCores=" + (cores-getAvailableCores()) +
                "\nCS=" + printCS() +
                '}';
    }

    private synchronized String printCS() {
        synchronized (CS) {
            return getCS().toString();
        }
    }
}
