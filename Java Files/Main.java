import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args){
        //path for the input files
        String st = "D:\\test\\input5.txt";

        //crate new file Reader and send the string path for
        //reading the file and the procceses and  the data and putting it into arraylist
        fileReader fr=new fileReader();
        fr.readNumProcess(st);
        ArrayList<Data> arrayList= fr.readProcess(st);

        //print and sort the arraylist
        System.out.println(arrayList);
        Collections.sort(arrayList, new DataComperator());
        System.out.println(arrayList);

        // space
        System.out.println();
        System.out.println();
        System.out.println();
        //doing the first Algorithm
        Algorithm ar=new Algorithm();
        //first come first serve
        ar.FCFS(arrayList);
        System.out.println("FCFS: " + ar.getFirstComeFirstServe());

        //last come first serve NP
        ar.LCFSNP(arrayList);
        System.out.println("LCFSNP: " +ar.getLastComeLastServeNP());
        //last come first serve P
        ar.LCFSP(arrayList);
        System.out.println("LCFSP: " + ar.getLastComeLastServeP());

        //Round Robin
        ar.RoundRobin(arrayList);
        System.out.println("Round Robin: " + ar.getRR());

        //Shortest Job First
        ar.SJF(arrayList);
        System.out.println("ShortEst Job First: " + ar.getShortestJobFirst());
    }
}
