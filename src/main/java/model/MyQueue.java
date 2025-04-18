package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue implements Runnable {
    private LinkedBlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    public MyQueue() {

        clients = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }


    public void addClientToQueue(Client client) throws InterruptedException {
        try {
            clients.put(client);
            waitingPeriod.getAndAdd(client.getServiceTime());
            client.setTotalWaitingTime(waitingPeriod.get());

        } catch (InterruptedException e) {
            System.err.println("Eroare la adăugarea clientului în coadă: " + e.getMessage());
        }
    }



    @Override
    public void run() {
        while (true) {
            try {
                Client nextClient = clients.peek();
                if(nextClient!=null) {

                    waitingPeriod.getAndAdd(-1);
                    nextClient.setServiceTime(nextClient.getServiceTime() - 1);
                    if (clients.peek().getServiceTime() == 0) {
                        clients.poll();
                    }
                }
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public LinkedBlockingQueue<Client> getClients() {
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    @Override
    public String toString() {
        String result = "";
        for (Client client : clients) {
            result += client.toString() + " ";
        }
        return result;
    }

}
