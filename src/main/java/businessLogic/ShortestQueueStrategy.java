package businessLogic;

import model.Client;
import model.MyQueue;

import java.util.List;

public class ShortestQueueStrategy implements Strategy{
    @Override
    public void addClient(List<MyQueue> queues, Client c) {
    MyQueue shortestQueue=null;
    int minLength=0;
    if(!queues.isEmpty()){
        minLength=queues.get(0).getClients().size();
        shortestQueue=queues.get(0);

        for(MyQueue queue:queues){
            int queueSize=queue.getClients().size();
            if(queueSize<minLength){
                minLength=queueSize;
                shortestQueue=queue;
            }
        }
    }
    if(shortestQueue!=null){
        try {
            shortestQueue.addClientToQueue(c);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    }
}
