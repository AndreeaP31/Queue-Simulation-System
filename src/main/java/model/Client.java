package model;

import java.util.Random;

public class Client implements Comparable<Client> {
    private int ID;
    private int arrivalTime;
    private int serviceTime;
    private int totalWaitingTime=0;

    public Client(int id,int arrivalTime, int serviceTime){
        this.ID=id;
        this.arrivalTime=arrivalTime;
        this.serviceTime=serviceTime;
    }
    public void decreaseServiceTime(){
        this.serviceTime--;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "(" + ID +
                "," + arrivalTime +
                "," + serviceTime +
                ')';
    }

    @Override
    public int compareTo(Client o) {
        return Integer.compare(this.arrivalTime, o.arrivalTime);
    }

    public int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(int totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }
}
