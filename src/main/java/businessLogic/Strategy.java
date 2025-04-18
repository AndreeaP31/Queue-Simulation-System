package businessLogic;

import model.Client;
import model.MyQueue;

import java.util.List;

public interface Strategy {
    public void addClient(List<MyQueue> queues, Client c);
}
