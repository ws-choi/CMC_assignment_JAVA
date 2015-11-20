import java.util.List;

public class CMC {

    public static List<Convoy> cm_clustering(List<Trajectory> o, int m, int k, double e) throws Exception {

        //TODO
        return null;
    }

    public static void main(String[] args) throws Exception {

        TrajectoryParser parser = new TrajectoryParser("resource/data.csv");
        List<Trajectory> traj_set = parser.get_traj_set();
        List<Convoy> res = CMC.cm_clustering(traj_set, 4, 4, 5);

        for (Convoy convoy : res)
            System.out.println(convoy);

    }

}

