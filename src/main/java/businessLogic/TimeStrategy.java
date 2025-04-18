package businessLogic;

import model.Client;
import model.MyQueue;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeStrategy implements Strategy{
    @Override
    public void addClient(List<MyQueue> queues, Client c) {
        MyQueue fastestQueue=null;
        AtomicInteger minPeriod= new AtomicInteger();
        if(!queues.isEmpty()){
            minPeriod=queues.get(0).getWaitingPeriod();
            fastestQueue=queues.get(0);

            for(MyQueue queue:queues){
                AtomicInteger queueWaitingPeriod= queue.getWaitingPeriod();
                if(queueWaitingPeriod.get() < minPeriod.get()){
                    minPeriod=queueWaitingPeriod;
                    fastestQueue=queue;
                }
            }
        }
        if(fastestQueue!=null){
            try {
                fastestQueue.addClientToQueue(c);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
