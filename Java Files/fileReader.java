import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;



public class fileReader {

    private int numOfProcess;

    public int getNumOfProcess() {
        return numOfProcess;
    }

    public void setNumOfProcess(int numOfProcess) {
        this.numOfProcess = numOfProcess;
    }

    public void readNumProcess(String path) {
        try {
            File process = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(process));

                String proc = br.readLine();
                br.close();

           numOfProcess=Integer.parseInt(proc);
            System.out.println(proc+numOfProcess);

        } catch (FileNotFoundException exception) {
            System.out.println("Error exception fileReader");
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Data> readProcess(String s){
        ArrayList<Data> arr=new ArrayList<Data>();
        if(this.getNumOfProcess()==0){
            System.out.println("the file is empty");
            System.exit(-1);
        }
        try {
            File obj=new File(s);
            BufferedReader br=new BufferedReader(new FileReader(obj));
            String st=br.readLine();
            while((st=br.readLine())!= null){
                //System.out.println(st);
                String[] num=st.split(",", 2);
                //System.out.println("string 0= "+num[0]+"," +"String 1="+num[1]);
                Data data=new Data(Integer.parseInt(num[0]),Integer.parseInt(num[1]));
                arr.add(data);

            }
            br.close();

        }
        catch (FileNotFoundException exception) {
            System.out.println("Error exception fileReader");
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
