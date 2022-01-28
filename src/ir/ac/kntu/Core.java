package ir.ac.kntu;

public class Core {
    private int num;
    private Process assigned;

    public Core(int num) {
        assigned = null;
        this.num = num;
    }

    public void setAssigned(Process assigned) {
        this.assigned = assigned;
    }

    public Process getAssigned() {
        return assigned;
    }

    public boolean isBusy() {
        if (assigned!=null) {
            return true;
        }
        return false;
    }

    public boolean release(Process p) {
        if (p.equals(assigned)) {
            return true;
        }
        return false;
    }

    public int getNum() {
        return num;
    }

}
