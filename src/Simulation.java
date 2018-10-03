import sun.util.resources.cldr.is.CalendarData_is_IS;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Simulation {

    private ArrayList<Caissier> arrayListCaissier = new ArrayList<Caissier>();
    private LinkedList<Client> arrayListClient = new LinkedList<Client>();
    private SimulationEntry simulation = new SimulationEntry();
    private int clientInterval = simulation.getCLIENTARRIVALINTERVAL();
    private Queue queue ;
    private Client client;
    private Caissier caissier;
    private int nombreClientServi = 0;
    private int tourVideCaissier = 0;
    private int attenteClient = 0;


    public void Simulate() {
        init();

        // Start of the Simulation
        for(int compteurSimulation = simulation.getSIMULATIONDURATION();compteurSimulation >= 0;compteurSimulation-- ){
            System.out.println("Time remaining = " + compteurSimulation);

            // Add client ==> Queue
            if(clientInterval == 0){
                client = new Client(compteurSimulation,0,0);
                System.out.println("A client has arrived at T = " + compteurSimulation);
                queue.addQueueLast(client);
                clientInterval = simulation.getCLIENTARRIVALINTERVAL();
            }
            else{
                clientInterval--;
            }

            // Queue ==> Caissier
            int compteur = 0;
            for(Caissier caissier : arrayListCaissier) {
                if (!queue.isEmpty()) {
                    if (getFreeCaisser(caissier) != null) {
                        client = queue.getQueueFirst();
                        client.setServiceStartTime(compteurSimulation);
                        int nombreAleatoire = simulation.getMINSERVICETIME() + (int)(Math.random() * ((simulation.getMAXSERVICETIME() - simulation.getMINSERVICETIME()) + 1));
                        caissier.serve(client, nombreAleatoire);
                        System.out.println("A client that arrived at T = " + caissier.getClient().getArrivalTime() + " has been taken by a Caissier at T = " + compteurSimulation);
                        compteur++;
                    }
                }
            }
            if((!queue.isEmpty())&&(compteur ==0)){
                attenteClient++;
            }
            compteur = 0;

            // Mise à jour
            for(Caissier caissier : arrayListCaissier) {
                caissier.setRemainingServiceTime(caissier.getRemainingServiceTime() - 1);

                if(caissier.getRemainingServiceTime() < 0){
                    caissier.setRemainingServiceTime(0);
                }
                if((caissier.getRemainingServiceTime() == 0) && (caissier.getHasClient() ==true)) {
                    System.out.print("Caissier"+compteur +" : " + caissier.getRemainingServiceTime());
                }
                else{
                    System.out.println("Caissier"+compteur +" : " + caissier.getRemainingServiceTime());
                }

                if ((caissier.getRemainingServiceTime() == 0)&&(caissier.getHasClient() ==true)) {
                    caissier.getClient().setDepartureTime(compteurSimulation);
                    System.out.println(" ==> Un client a ete servi de T = " + caissier.getClient().getServiceStartTime() + " jusqu'à T = " + caissier.getClient().getDepartureTime());
                    caissier.setClient(null);
                    caissier.setHasClient(false);
                    nombreClientServi++;
                }

                if ((caissier.getRemainingServiceTime() == 0)&&(caissier.getHasClient() ==false)) {
                    tourVideCaissier++;
                }
                compteur++;
            }
            compteur = 0;
            System.out.println(" ");
        }

        afficheStat();
    }


    private void init() {

        System.out.println("------------ DEBUT DE LA SIMULATION -------------");

        // Initialisation of the Clients
        queue = new Queue(arrayListClient);

        // Initialisation of the Caissier
        for(int compteur = 0;compteur < simulation.getCAISSIERCOUNT();compteur++){
            Caissier caissier = new Caissier(0,null);
            arrayListCaissier.add(caissier);
        }
    }


    public Caissier getFreeCaisser(Caissier caissier){
        if(caissier.getHasClient() == Boolean.FALSE){
            return caissier;
        }
        else {
            return null;
        }
    }

    private void afficheStat() {
        System.out.println("------------ STATISTIQUES -------------");
        System.out.println("Nombre de clients servis : " + nombreClientServi);
        System.out.println("Nombre de fois où un caissier n'a rien fait : " + tourVideCaissier);
        System.out.println("Nombre de tour où au moins un client dans la file a attendu : " + attenteClient);
    }
}