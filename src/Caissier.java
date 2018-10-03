public class Caissier {

    private int remainingServiceTime =0;
    private Client client = null;
    private Boolean hasClient;
    private SimulationEntry simulationEntry;

    public Caissier(int remainingServiceTime, Client client) {
        this.remainingServiceTime = remainingServiceTime;
        this.client = client;
        this.hasClient = false;
    }

    public int getRemainingServiceTime() {
        return remainingServiceTime;
    }

    public Client getClient() {
        return client;
    }

    public void setRemainingServiceTime(int remainingServiceTime) {
        this.remainingServiceTime = remainingServiceTime;
    }

    public Boolean isFree() {
        return hasClient == false && remainingServiceTime == 0;
    }

    public void serve(Client client,int serviceTime) {
        this.client = client;
        this.remainingServiceTime = serviceTime;
        this.hasClient = true;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getHasClient() {
        return hasClient;
    }

    public void setHasClient(Boolean hasClient) {
        this.hasClient = hasClient;
    }
}