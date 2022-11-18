import java.util.ArrayList;

public class PrintAllTypes {
    public void printArrayList(ArrayList a){
        System.out.println("size: "+a.size());
        for(int i=0; i<a.size();i++){
            System.out.println("data at index "+i+" is: ");
            //System.out.println(a.get(i));
        }
    }

}
