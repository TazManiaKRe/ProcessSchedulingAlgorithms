public class Data {
    //data from file
    private int arrivalsTime;
    private int needTime;

    //final int numOfProcess;
    public Data(int come, int need){
        this.setArrivalsTime(come);
        this.setNeedTime(need);
    }

    //getters and setters
    public int getArrivalsTime() {
        return arrivalsTime;
    }

    public void setArrivalsTime(int arrivalsTime) {
        this.arrivalsTime = arrivalsTime;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public void printData(){
        System.out.println("arrival time: "+this.getArrivalsTime());
        System.out.println("quantum time: "+ this.getNeedTime());
    }


    @Override
    public String toString() {
        return " Data{" + " arrivalsTime= " + arrivalsTime + ", needTime=" + needTime + "} \n" ;
    }
}














