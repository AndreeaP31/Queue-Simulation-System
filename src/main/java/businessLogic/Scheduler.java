package businessLogic;

import model.Client;
import model.MyQueue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<MyQueue> queues;
    private Strategy strategy;
    private int nrQueues;
    private int nrClients;

    public Scheduler(int nrQueues, int nrClients, SelectionPolicy policy) {
        this.nrClients = nrClients;
        this.nrQueues = nrQueues;
        this.queues = new ArrayList<>();
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
        for (int i = 0; i < nrQueues; i++) {
            MyQueue queue = new MyQueue();
            Thread thread = new Thread(queue);
            thread.start();
            queues.add(queue);
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        //apply strategy patter to instantiate the strategy with the concrete strategy correspondinh to policy
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchClient(Client c) {
        if (strategy != null) {
            strategy.addClient(queues, c);
        } else {
            System.out.println("Nu a fost setata nicio strategie");
        }
    }

    public List<MyQueue> getQueues() {
        return queues;
    }
}
