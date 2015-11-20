import java.util.ArrayList;
import java.util.Collections;

public class Convoy extends Obj_List{

    boolean assigned;
    double startTime, endTime;
    int lifetime;

    public Convoy( boolean assigned, int lifetime) {
        this.assigned = assigned;
        this.lifetime = lifetime;
    }

    public Convoy() {
        this(false, 1);
    }


    public Convoy(Cluster c) {

        this();
        super.addAll(c);

    }

    public boolean isAssigned() {return assigned;}
    public void setAssigned(boolean assigned) {this.assigned = assigned;}
    public double getStartTime() { return startTime; }
    public void setStartTime(double startTime) {this.startTime = startTime; }
    public double getEndTime() { return endTime; }
    public void setEndTime(double endTime) { this.endTime = endTime; }
    public int getLifetime() { return lifetime; }
    public void setLifetime(int lifetime) { this.lifetime = lifetime; }

    @Override
    public String toString() {

        String res = "Start_time: " + getStartTime() + ",\tEnd_Time: " + getEndTime() +"\t(" + getLifetime()+")\nobj_list: ";

        Collections.sort(this);

        res+= this.get(0);

        for (int i = 1; i < this.size(); i++) {
            Integer oid = this.get(i);
            res += ", "+ oid;
        }

        res+='\n';

        return res;
    }
}
