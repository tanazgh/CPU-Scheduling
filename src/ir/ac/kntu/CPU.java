package ir.ac.kntu;

import java.util.List;

public class CPU {
    private List<Core> cores;
    private int lock;
    private Process CS;

    public CPU(List<Core> c) {
        cores = c;
    }

    public boolean lock(Process p) {
        if (lock == 0) {
            CS = p;
            lock = 1;
            return true;
        }
        return false;
    }

    public boolean release(Process p) {
        if (p.equals(CS)) {
            lock = 0;
            return true;
        }
        return false;
    }

}
