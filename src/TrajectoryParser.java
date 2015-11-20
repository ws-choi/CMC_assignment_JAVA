import java.io.*;
import java.util.List;


public class TrajectoryParser {

    File inputFile;
    BufferedReader reader;

    public TrajectoryParser(String path) throws IOException {

        inputFile = new File(path);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Cannot find File.");
        }

    }

    public List<Trajectory> get_traj_set() {
        //TODO
        return null;
    }
}