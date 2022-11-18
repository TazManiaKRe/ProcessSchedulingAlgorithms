
import java.util.Comparator;

public class DataComperator implements Comparator<Data> {
    @Override
    public int compare(Data d1, Data d2){
        return d1.getArrivalsTime()- d2.getArrivalsTime();
    }

}
