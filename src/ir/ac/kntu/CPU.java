package ir.ac.kntu;

import java.util.List;

public class CPU {
    private List<Core> cores;
    private int lock;
    private Process CS;

    public CPU(List<Core> c) {
        cores = c;
    }

    public void lock(Process p) {
        if (lock == 0) {
            CS = p;
            lock = 1;
        }
    }

    public void release(Process p) {
        if (p.equals(CS)) {
            lock = 0;
        }
    }

}
