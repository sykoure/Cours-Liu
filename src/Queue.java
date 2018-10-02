import java.util.LinkedList;

public class Queue  {

    private LinkedList<Client> list = new LinkedList<Client>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void addQueueLast(Client client) {
        list.addLast(client);
    }

    public Client getQueueFirst() {
        return list.removeFirst();
    }
}