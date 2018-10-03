public class Client {

    private int arrivalTime;
    private int serviceStartTime;
    private int departureTime;

    public Client(int arrivalTime, int serviceStartTime, int departureTime) {
        this.arrivalTime = arrivalTime;
        this.serviceStartTime = serviceStartTime;
        this.departureTime = departureTime;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getServiceStartTime() {
        return serviceStartTime;
    }
    public void setServiceStartTime(int serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }
    public int getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }


}