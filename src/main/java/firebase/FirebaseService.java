package firebase;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;


/**
 * This class is used for getting and storing users. It is also used for getting game data and storing game data.
 *
 *
 * @author ryan
 */
public class FirebaseService{

    private Firestore firestore;
    private static final String GEBRUIKERS_PATH = "games";
    private CollectionReference colRef;
//    private Controller controller;


    public FirebaseService() {
        Database db = new Database();
        this.firestore = db.getFirestoreDatabase();

        this.colRef = this.firestore.collection(GEBRUIKERS_PATH);		// Een generieke referentie naar de games documents.
    }



    /**
     * Geeft een update naar de meegeleverde controller
     * op het moment dat er een wijziging in het firebase document plaatsvindt.
     * @param roomId Dit is de roomId/ het spel wat gebruikt wordt.
     *
     * @author ryan
     */
    public void listen(String roomId) {

        DocumentReference docRef = this.colRef.document(roomId);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
//                    controller.updateFromFirebase(snapshot);

                    System.out.println("Current data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }


    /**
     * Update een gebruiker met inlognaam, nickname en wachtwoord.
     *
     * @param docData Hashmap in which you put data with a key and value pair.
     *
     * @author ryan
     */
    public void addGebruiker(Map<String, Object> docData) {

        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = this.colRef.document("gebruikers").update(docData);


        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * Verkrijgen van een gebruiker op basis van zijn inlognaam
     * @param gebruiker De naam van een gebruiker
     * @return Een speler object
     *
     * @author ryan
     */
    public Object getGebruiker(String gebruiker) {

        DocumentReference docRef = this.colRef.document("gebruikers");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;

        try {
            document = future.get();

            if (document.exists()) {
                System.out.println(document.get(gebruiker));
                return document.get(gebruiker);
            } else {

                System.out.println("No such document!");
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
        return null;

    }


    /**
     * Deze methode word gebruikt om een spel toe te voegen aan de firestore db
     *
     * @param roomID De naam van het spel/roomID
     * @param docData De informatie die in het spel zit, dus spelers enz.
     *
     * @author ryan
     */
    public void addSpel(String roomID, Map<String, Object> docData){

        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = this.colRef.document(roomID).set(docData);

        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode wordt gebruikt om alle roomId's op te halen van de db
     *
     * @return een list met alle roomids
     *
     * @author ryan
     */
    public List<QueryDocumentSnapshot> getAllRooms(){
        try{
            ApiFuture<QuerySnapshot> future = colRef.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for(DocumentSnapshot doc : documents){
                System.out.println(doc.getId());
            }
            return documents;
        }catch (InterruptedException ie){
            System.out.println("Interrupt: " + ie);
        }catch (ExecutionException ee){
            System.out.println("Execution: " + ee);
        }
        return null;
    }

    /**
     * Deze methode is gebruikt om een spel op te vragen uit de database
     *
     * @param roomID De naam van het spel dat je wilt krijgen
     * @return Een spel
     *
     * @author ryan
     */
    public DocumentSnapshot getSpel(String roomID){
        DocumentReference docRef = this.colRef.document(roomID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;

        try {
            document = future.get();

            if (document.exists()) {
                return document;
            } else {

                System.out.println("No such document!");
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
        return null;
    }


    /**
     * Verwijdert een document op basis van het documentId.
     * @param documentId
     */
    public void delete(String documentId) {
        ApiFuture<WriteResult> writeResult = this.colRef.document(documentId).delete();
    }

}