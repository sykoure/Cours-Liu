public class Caissier {

    private int remainingServiceTime =0;
    private Client client = null;
    private SimulationEntry simulationEntry;


    public int getRemainingServiceTime() {
        return remainingServiceTime;
    }

    public void setRemainingServiceTime(int remainingServiceTime) {
        this.remainingServiceTime = remainingServiceTime;
    }

    public Boolean isFree() {
        return client == null && remainingServiceTime == 0;
    }

    public void Serve(Client client,int serviceTime) {
        this.client = client;
        this.remainingServiceTime = serviceTime;
    }
}