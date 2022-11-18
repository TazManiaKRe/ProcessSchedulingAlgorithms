import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.Stack;
public class Algorithm {

    private double firstComeFirstServe;
    private double lastComeLastServeNP;
    private double lastComeLastServeP;
    private double RR;
    private double shortestJobFirst;

    public double getFirstComeFirstServe() {
        return firstComeFirstServe;
    }

    public void setFirstComeFirstServe(double firstComeFirstServe) {
        this.firstComeFirstServe = firstComeFirstServe;
    }

    public double getLastComeLastServeNP() {
        return lastComeLastServeNP;
    }

    public void setLastComeLastServeNP(double lastComeLastServeNP) {
        this.lastComeLastServeNP = lastComeLastServeNP;
    }

    public double getLastComeLastServeP() {
        return lastComeLastServeP;
    }

    public void setLastComeLastServeP(double lastComeLastServeP) {
        this.lastComeLastServeP = lastComeLastServeP;
    }

    public double getRR() {
        return RR;
    }

    public void setRR(double RR) {
        this.RR = RR;
    }

    public double getShortestJobFirst() {
        return shortestJobFirst;
    }

    public void setShortestJobFirst(double shortestJobFirst) {
        this.shortestJobFirst = shortestJobFirst;
    }
    //first come first server
    public void FCFS(ArrayList<Data> arraylist){
        int waitingTime[]=new int[arraylist.size()];
        int turnAroundTime[]=new int[arraylist.size()];

        findWaitingTime(arraylist, waitingTime);
        findTurnAroundTime(arraylist,waitingTime,turnAroundTime);

        if(arraylist.size()==0){
            System.out.println("-------------------");
            System.out.println("no process are in the file");
            System.out.println("-------------------");
        }
        if(arraylist.size()==1){
            System.out.println("-------------------");
            System.out.println("FCFS: mean turnaround = "+arraylist.get(0).getNeedTime());
            System.out.println("-------------------");
        }
        float avg = findAvg(turnAroundTime, arraylist.size());
        this.setFirstComeFirstServe(avg);

    }
    //last come first serve NP
    public void LCFSNP(ArrayList<Data> arraylist){
        int waitingTime[]= new int[arraylist.size()];
        waitingTimeForLCFSNP(arraylist,waitingTime);
        int turnAroundTime[] = new int [arraylist.size()];
        findTurnAroundTime(arraylist, waitingTime,turnAroundTime);
        float avg = findAvg(turnAroundTime, arraylist.size());
        this.setLastComeLastServeNP(avg);
    }

    //last come first serve P
    public void LCFSP(ArrayList<Data> arrayList){
        int waitingTime[] = new int [ arrayList.size()];
        int turnAroundTime[] = new int [ arrayList.size()];
        waitingTime[arrayList.size()-1] = 0;
        for (int i = arrayList.size()-2 ; i>=0; i--){
            if(arrayList.get(i).getArrivalsTime() + arrayList.get(i).getNeedTime() < arrayList.get(i+1).getArrivalsTime())
                waitingTime[i] = 0;
            else
                waitingTime[i] = waitingTime[i+1] + arrayList.get(i+1).getNeedTime();
        }
        findTurnAroundTime(arrayList,waitingTime, turnAroundTime);
        float avg = findAvg(turnAroundTime, arrayList.size());
        this.setLastComeLastServeP(avg);
    }

    //Round Robin
    public void RoundRobin(ArrayList<Data> arrayList){
       int waitingTime[] = new int [arrayList.size()];
       int turnAround[] = new int[arrayList.size()];
       int quantum = 2, i =0, totalE =0, totalW =0, flag =0, index =0, tempFlag=0;
       int reProc = arrayList.size();
       int remainTime[] = new int [arrayList.size()];

       for(int j = 0; i<arrayList.size(); i++)
           remainTime[i] = arrayList.get(i).getNeedTime();
       while(remainTime[index] ==0){
           if (totalE ==0)
               totalE++;
           totalE += arrayList.get(index+1).getArrivalsTime() - arrayList.get(index).getArrivalsTime();
           index++;
           reProc--;
           waitingTime[index]=0;
       }
       for (i =index; reProc!=0;){
           tempFlag=0;
           if(remainTime[i] != 0){
               if (remainTime[i] <= quantum && remainTime[i] > 0) {
                   totalE += remainTime[i];
                   tempFlag =1;
                   remainTime[i]=0;
                   flag=1;
               }
               else if (remainTime[i] >0){
                   remainTime[i] -= quantum;
                   totalE += quantum;
                   tempFlag=1;
               }
               if(flag ==1 && remainTime[i] ==0){
                   if (totalE - arrayList.get(i).getArrivalsTime() - arrayList.get(i).getNeedTime()<=0){
                       waitingTime[i] =0;
                       totalW +=0;
                   }
                   else{
                       waitingTime[i] = totalE - arrayList.get(i).getArrivalsTime() -arrayList.get(i).getNeedTime();
                       totalW += totalE - arrayList.get(i).getArrivalsTime() -arrayList.get(i).getNeedTime();
                   }
                   flag =0;
                   reProc--;
               }
           }
           if(i == arrayList.size()-1)
               i=0;
           else if (arrayList.get(i+1).getArrivalsTime() <= totalE)
               i++;
           else if (tempFlag ==0)
               totalE += 1;
           else
               i=0;
       }
        turnAroungRR(arrayList,waitingTime, turnAround);
        float avg =  (float)turnAround[0]/arrayList.size();
        this.setRR(avg);
    }

    //Shortest Job First
    public void SJF (ArrayList<Data> arrayList){
        int waitTime[] = new int[arrayList.size()];
        int turnAround[] = new int [arrayList.size()];
        int smallEst = 0, count =0, time =0, end, i=0;
        int reTime[] = new int [arrayList.size()+1];

        for (i =0; i<arrayList.size(); i++)
            reTime[i] = arrayList.get(i).getNeedTime();

        reTime[arrayList.size()] = 9999;

        while (arrayList.get(time).getNeedTime() ==0){
            count ++;
            time++;
        }
        for (;count!=arrayList.size(); time++){
            smallEst = arrayList.size();
            for (i =0; i<arrayList.size(); i++){
                if (arrayList.get(i).getArrivalsTime() <= time && reTime[i] < reTime[smallEst] && reTime[i] >0){
                    smallEst =i;}
            }
            reTime[smallEst]--;
            if (reTime[smallEst] ==0){
                count++;
                end = time +1;
                waitTime[smallEst] = end - arrayList.get(smallEst).getArrivalsTime() - arrayList.get(smallEst).getNeedTime();
            }
        }
        findTurnAroundTime(arrayList,waitTime,turnAround);
        float avg = findAvg(turnAround, arrayList.size());
        this.setShortestJobFirst(avg);
    }

    public void findWaitingTime(ArrayList<Data> arraylist,int waitTime[]){
        waitTime[0]=0;
        for(int i=1;i<arraylist.size();i++){
           if (waitTime[i-1] + arraylist.get(i-1).getNeedTime()+arraylist.get(i-1).getArrivalsTime() < arraylist.get(i).getArrivalsTime())
               waitTime[i] = 0;
           else
               waitTime[i] = arraylist.get(i-1).getNeedTime()+waitTime[i-1]-(arraylist.get(i).getArrivalsTime()-arraylist.get(i-1).getArrivalsTime());
        }
    }
    public void findTurnAroundTime(ArrayList<Data> arraylist,int wt[], int tat[]){
        for(int i=0;i<arraylist.size();i++){
            tat[i] = arraylist.get(i).getNeedTime() + wt[i];
        }
    }

    public float findAvg (int[]turnAround, int size){
        float totalTat = 0;
        for(int i = 0; i<size; i++)
            totalTat += turnAround[i];
        float avg = (float)totalTat / (float) size;
        return avg;
    }
    public void turnAroungRR(ArrayList<Data> arrayList, int[] wt, int[] tr){
        int sumForW =0, sumForC =0;
        for(int i =0; i<arrayList.size(); i++){
            sumForW += wt[i];
            sumForC+= arrayList.get(i).getNeedTime();
        }
        tr[0] = sumForW + sumForC;
    }
    public void waitingTimeForLCFSNP(ArrayList<Data> arraylist, int waitingTime[]){
        //int waitingTime[] = new int [arraylist.size()];
        waitingTime[0]=0;
        int arrIns[] = new int [arraylist.size()];
        int arrExe [] = new int [arraylist.size()];
        int index=1, insert =1, waitIndex =1;

        Stack<Integer> data1 = new Stack<Integer>();
        Stack<Integer> data2 = new Stack<Integer>();

        arrIns[0] = arraylist.get(0).getArrivalsTime();
        arrExe[0] = arraylist.get(0).getNeedTime();

        while (index < arraylist.size()){
            if (insert < arraylist.size()){
                if (arraylist.get(insert).getArrivalsTime() <=
                        waitingTime[waitIndex-1]+arrExe[index-1]+arrIns[index-1]){
                    data1.push((arraylist.get(insert).getArrivalsTime()));
                    data2.push(arraylist.get(insert).getNeedTime());
                    insert++;
                }
                else {
                    if (data1.isEmpty()){
                        arrIns[index] = arraylist.get(insert).getArrivalsTime();
                        arrExe[index] = arraylist.get(insert).getNeedTime();
                        index++;
                        insert++;
                    }
                    else{
                        int temp = data1.pop();
                        arrIns[index] = temp;
                        temp = data2.pop();
                        arrExe[index]=temp;
                        waitingTime[index] = waitingTime[index-1]+arrExe[index-1]
                                +arrIns[index-1]-arrIns[index];
                        if (waitingTime[index]<= 0)
                            waitingTime[index] =0;
                        index++;
                    }
                }
            }
            else{
                while (!data1.isEmpty()){
                    int temp = data1.pop();
                    arrIns[index] = temp;
                    temp = data2.pop();
                    arrExe[index] = temp;
                    waitingTime[index] = waitingTime[index-1] + arrExe[index-1]
                            + arrIns[index-1] - arrIns[index];
                    if (waitingTime[index]<=0)
                        waitingTime[index] = 0;
                    index++;
                }
            }
        }
    }
}