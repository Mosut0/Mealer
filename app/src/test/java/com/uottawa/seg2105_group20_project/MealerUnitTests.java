package com.uottawa.seg2105_group20_project;

import org.junit.Test;

import java.net.PortUnreachableException;
import java.util.*;
import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.Toast;

public class MealerUnitTests {

    /**
     * HELPER METHODS
     */

    public static List<PurchaseRequest> generatePCDB(){
        List<PurchaseRequest> pcDB = new ArrayList<>();

        PurchaseRequest purchase1 = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");
        PurchaseRequest purchase2 = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");
        PurchaseRequest purchase3 = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");
        PurchaseRequest purchase4 = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");
        PurchaseRequest purchase5 = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");

        pcDB.add(purchase1);
        pcDB.add(purchase2);
        pcDB.add(purchase3);
        pcDB.add(purchase4);
        pcDB.add(purchase5);

        return pcDB;
    }

    /**
     * Generates list of clients that act as a database.
     * @return databaseClient
     */
    public static List<Client> generateClientDB(){
        List<Client> databaseClient = new ArrayList<>();

        Client clientTest1 = new Client("test1", "test1", "test1", "test1", "test1", "test1", "4865848", "334", "3241");
        Client clientTest2 = new Client("test2", "test2", "test2", "test2", "test2", "test2", "4865848", "333", "3421");
        Client clientTest3 = new Client("test3", "test3", "test3", "test3", "test3", "test3", "4865848", "343", "3214");
        Client clientTest4 = new Client("test4", "test4", "test4", "test4", "test4", "test4", "4865848", "333", "3241");
        Client clientTest5 = new Client("test5", "test5", "test5", "test5", "test5", "test5", "4865848", "343", "3241");
        Client clientTest6 = new Client("test6", "test6", "test6", "test6", "test6", "test6", "4865848", "343", "3241");
        Client clientTest7 = new Client("test7", "test7", "test7", "test7", "test7", "test7", "4865848", "343", "3421");

        databaseClient.add(clientTest1);
        databaseClient.add(clientTest2);
        databaseClient.add(clientTest3);
        databaseClient.add(clientTest4);
        databaseClient.add(clientTest5);
        databaseClient.add(clientTest6);
        databaseClient.add(clientTest7);

        return databaseClient;
    }


    /**
     * Generates list of cooks that act as a database.
     * @return databaseCook
     */
    public static List<Cook> generateCookDB(){
        List<Cook> databaseCook = new ArrayList<>();

        Cook cookTest1 = new Cook("test1", "test1", "test1", "test1", "test1", "test1", "test1", "test1", new ArrayList<>(), new ArrayList<>());
        Cook cookTest2 = new Cook("test2", "test2", "test2", "test2", "test2", "test2", "test2", "test2", new ArrayList<>(), new ArrayList<>());
        Cook cookTest3 = new Cook("test3", "test3", "test3", "test3", "test3", "test3", "test3", "test3", new ArrayList<>(), new ArrayList<>());
        Cook cookTest4 = new Cook("test4", "test4", "test4", "test4", "test4", "test4", "test4", "test4", new ArrayList<>(), new ArrayList<>());
        Cook cookTest5 = new Cook("test5", "test5", "test5", "test5", "test5", "test5", "test5", "test5", new ArrayList<>(), new ArrayList<>());
        Cook cookTest6 = new Cook("test6", "test6", "test6", "test6", "test6", "test6", "test6", "test6", new ArrayList<>(), new ArrayList<>());
        Cook cookTest7 = new Cook("test7", "test7", "test7", "test7", "test7", "test7", "test7", "test7", new ArrayList<>(), new ArrayList<>());

        databaseCook.add(cookTest1);
        databaseCook.add(cookTest2);
        databaseCook.add(cookTest3);
        databaseCook.add(cookTest4);
        databaseCook.add(cookTest5);
        databaseCook.add(cookTest6);
        databaseCook.add(cookTest7);

        return databaseCook;
    }

    /**
     * Generates list of meals that act as a database.
     * @return databaseMeal
     */

    public static List<Meal> generateMealDB(){
        List<Meal> databaseMeal = new ArrayList<>();

        Meal mealTest1 = new Meal("test1", "test1", "test1", "test1", "test1", "test1", "test1", "test1", "test1", true);
        Meal mealTest2 = new Meal("test2", "test2", "test2", "test2", "test2", "test2", "test2", "test2", "test2", true);
        Meal mealTest3 = new Meal("test3", "test3", "test3", "test3", "test3", "test3", "test3", "test3", "test3", true);
        Meal mealTest4 = new Meal("test4", "test4", "test4", "test4", "test4", "test4", "test4", "test4", "test4", true);
        Meal mealTest5 = new Meal("test5", "test5", "test5", "test5", "test5", "test5", "test5", "test5", "test5", true);
        Meal mealTest6 = new Meal("test6", "test6", "test6", "test6", "test6", "test6", "test6", "test6", "test6", true);
        Meal mealTest7 = new Meal("test7", "test7", "test7", "test7", "test7", "test7", "test7", "test7", "test7", true);

        databaseMeal.add(mealTest1);
        databaseMeal.add(mealTest2);
        databaseMeal.add(mealTest3);
        databaseMeal.add(mealTest4);
        databaseMeal.add(mealTest5);
        databaseMeal.add(mealTest6);
        databaseMeal.add(mealTest7);

        return databaseMeal;
    }

    /**
     * Generates list of complaints that act as a database.
     * @return databasecomplaint
     */

    public static List<Complaint> generateComplaintDB(){
        List<Complaint> databaseComplaint = new ArrayList<>();

        Complaint complaintTest1 = new Complaint("test1", "test1", "test1", "test1");
        Complaint complaintTest2 = new Complaint("test2", "test2", "test2", "test2");
        Complaint complaintTest3 = new Complaint("test3", "test3", "test3", "test3");
        Complaint complaintTest4 = new Complaint("test4", "test4", "test4", "test4");
        Complaint complaintTest5 = new Complaint("test5", "test5", "test5", "test5");
        Complaint complaintTest6 = new Complaint("test6", "test6", "test6", "test6");
        Complaint complaintTest7 = new Complaint("test7", "test7", "test7", "test7");

        databaseComplaint.add(complaintTest1);
        databaseComplaint.add(complaintTest2);
        databaseComplaint.add(complaintTest3);
        databaseComplaint.add(complaintTest4);
        databaseComplaint.add(complaintTest5);
        databaseComplaint.add(complaintTest6);
        databaseComplaint.add(complaintTest7);

        return databaseComplaint;
    }


    /**
     * Random string generator
     * @param n Size of string
     * @return sb
     */
    public  static String getAlphaNumericString(int n) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    /**
     *Generates a client
     * @return client
     */
    public static Client generateClient(){
        Random randInt = new Random();

        String id = getAlphaNumericString(10);
        String fName = getAlphaNumericString(10);
        String lName = getAlphaNumericString(10);
        String email = getAlphaNumericString(10);
        String password = getAlphaNumericString(10);
        String address = getAlphaNumericString(10);
        String creditCard = String.valueOf(randInt.nextInt(999999999));
        String expiry = "3343";
        String cvv = "321";

        Client client = new Client(id, fName, lName, email, password, address, creditCard, cvv, expiry);

        return client;
    }

    /**
     *Generates a cook
     * @return cook
     */
    public static Cook generateCook(){

        String id = getAlphaNumericString(10);
        String fName = getAlphaNumericString(10);
        String lName = getAlphaNumericString(10);
        String email = getAlphaNumericString(10);
        String password = getAlphaNumericString(10);
        String address = getAlphaNumericString(10);
        String description = getAlphaNumericString(10);
        String cheque = getAlphaNumericString(10);

        Cook cook = new Cook(id, fName, lName, email, password, address, description, cheque, new ArrayList<>(), new ArrayList<>());

        return cook;
    }

    /**
     *Generates a meal
     * @return meal
     */
    public static Meal generateMeal(){

        String id = getAlphaNumericString(10);
        String mName = getAlphaNumericString(10);
        String mType = getAlphaNumericString(10);
        String cuisType = getAlphaNumericString(10);
        String ingredients = getAlphaNumericString(10);
        String allergens = getAlphaNumericString(10);
        String price = getAlphaNumericString(10);
        String description = getAlphaNumericString(10);
        String cookID = getAlphaNumericString(10);

        Meal meal = new Meal(id, mName, mType, cuisType, ingredients, allergens, price, description, cookID, true);

        return meal;
    }

    /**
     *Generates a complaint
     * @return complaint
     */

    public static Complaint generateComplaint(){

        String complainee = getAlphaNumericString(10);
        String complaineeID = getAlphaNumericString(10);
        String complainant = getAlphaNumericString(10);
        String description = getAlphaNumericString(10);

        Complaint complaint = new Complaint(complainee, complaineeID, complainant, description);

        return complaint;
    }

    /**
     * DELIVERABLE 2 UNIT TESTS
     */

    @Test
    public void checkExistingClient(){
        List<Client> clientDB = generateClientDB();
        Client clientTest4 = new Client("test4", "test4", "test4", "test4", "test4", "test4", "4865848", "345", "2344");

        boolean loginFound = false;
        for (int i=0; i<clientDB.size(); i++){
            if (clientDB.get(i).email.trim().equals(clientTest4.email) && clientDB.get(i).password.trim().equals(clientTest4.password)){
                loginFound = true;
                break;
            };
        }
        assertTrue(loginFound);

    }

    @Test
    public void checkExistingCook(){
        List<Cook> cookDB = generateCookDB();
        Cook cookTest4 = new Cook("test4", "test4", "test4", "test4", "test4", "test4", "test4", "test4", new ArrayList<>(), new ArrayList<>());

        boolean loginFound = false;
        for (int i=0; i<cookDB.size(); i++){
            if (cookDB.get(i).email.trim().equals(cookTest4.email) && cookDB.get(i).password.trim().equals(cookTest4.password)){
                loginFound = true;
                break;
            };
        }
        assertTrue(loginFound);

    }

    @Test
    public void checkNonExistingClient(){
        List<Client> clientDB = generateClientDB();
        Client clientTest4 = new Client("test4", "test4", "test4", "test4", "test99", "test4", "4865848", "334", "3241");

        boolean loginFound = false;
        for (int i=0; i<clientDB.size(); i++){
            if (clientDB.get(i).email.trim().equals(clientTest4.email) && clientDB.get(i).password.trim().equals(clientTest4.password)){
                loginFound = true;
                break;
            };
        }
        assertFalse(loginFound);

    }

    @Test
    public void checkNonExistingCook(){
        List<Cook> cookDB = generateCookDB();
        Cook cookTest4 = new Cook("test4", "test4", "test4", "test99", "test4", "test4", "test4", "test4", new ArrayList<>(), new ArrayList<>());

        boolean loginFound = false;
        for (int i=0; i<cookDB.size(); i++){
            if (cookDB.get(i).email.trim().equals(cookTest4.email) && cookDB.get(i).password.trim().equals(cookTest4.password)){
                loginFound = true;
                break;
            };
        }
        assertFalse(loginFound);

    }

    @Test
    public void fullCookSignUp() {
        String id = getAlphaNumericString(10);
        String fName = getAlphaNumericString(10);
        String lName = getAlphaNumericString(10);
        String email = getAlphaNumericString(10);
        String password = getAlphaNumericString(10);
        String address = getAlphaNumericString(10);
        String description = getAlphaNumericString(10);
        String cheque = getAlphaNumericString(10);

        Cook cook = new Cook(id, fName, lName, email, password, address, description, cheque, new ArrayList<>(), new ArrayList<>());

        assertEquals(id, cook.id);
        assertEquals(fName, cook.firstName);
        assertEquals(lName, cook.lastName);
        assertEquals(email, cook.email);
        assertEquals(password, cook.password);
        assertEquals(address, cook.address);
        assertEquals(description, cook.description);
        assertEquals(cheque, cook.voidCheque);
    }

    @Test
    public void fullClientSignUp() {
        Random randInt = new Random();

        String id = getAlphaNumericString(10);
        String fName = getAlphaNumericString(10);
        String lName = getAlphaNumericString(10);
        String email = getAlphaNumericString(10);
        String password = getAlphaNumericString(10);
        String address = getAlphaNumericString(10);
        String creditCard = String.valueOf(randInt.nextInt(999999999));
        String expiry = "3343";
        String cvv = "321";

        Client client = new Client(id, fName, lName, email, password, address, creditCard, cvv, expiry);

        assertEquals(id, client.id);
        assertEquals(fName, client.firstName);
        assertEquals(lName, client.lastName);
        assertEquals(email, client.email);
        assertEquals(password, client.password);
        assertEquals(address, client.address);
        assertEquals(creditCard, client.creditCardNumber);
    }

    /**
     * DELIVERABLE 3 UNIT TESTS
     */


    @Test
    public void checkMealExists(){
        List<Meal> mealDB = generateMealDB();
        Meal mealTest4 = new Meal("test4", "test4", "test4", "test4", "test4", "test4", "test4", "test4", "test4", true);

        boolean mealFound = false;
        for (int i=0; i<mealDB.size(); i++){
            if (mealDB.get(i).getMealID().trim().equals(mealTest4.getMealID())){
                mealFound = true;
                break;
            };
        }
        assertTrue(mealFound);

    }

    @Test
    public void checkNonExistingMeal() {
        List<Meal> mealDB = generateMealDB();
        Meal mealTest4 = new Meal("test9999", "test4", "test4", "test77", "test4", "test4", "test4", "test4", "test4", true);

        boolean mealFound = false;
        for (int i = 0; i < mealDB.size(); i++) {
            if (mealDB.get(i).getMealID().trim().equals(mealTest4.getMealID())) {
                mealFound = true;
                break;
            }
            ;
        }
        assertFalse(mealFound);

    }


    @Test
    public void checkMealsOffered() {
        List<Meal> mealDB = generateMealDB();

        int count = 0;
        for (int i=0; i<mealDB.size(); i++){
            if(mealDB.get(i).isOffered());

            count++;
        }
        assertTrue(count == 7);

    }


    @Test
    public void checkComplaintExists(){
        List<Complaint> complaintDB = generateComplaintDB();
        Complaint complaintTest4 = new Complaint("test4", "test4", "test4", "test4");

        boolean complaintFound = false;
        for (int i=0; i<complaintDB.size(); i++){
                if (complaintDB.get(i).getComplaineeID().trim().equals(complaintTest4.getComplaineeID())){
                    complaintFound = true;
                    break;
            };
        }
        assertTrue(complaintFound);

    }

    @Test
    public void checkNonExistingComplaint(){
        List<Complaint> complaintDB = generateComplaintDB();
        Complaint complaintTest4 = new Complaint("ABCD6", "test4", "test4", "test4");

        boolean complaintFound = false;
        for (int i=0; i<complaintDB.size(); i++){
            if (complaintDB.get(i).getComplainee().trim().equals(complaintTest4.getComplainee())){
                complaintFound = true;
                break;
            };
        }
        assertFalse(complaintFound);

    }

    /**
     * DELIVERABLE 4 UNIT TESTS
     */


    @Test
    public void checkPurchaseExists(){
        List<Meal> mealDB = generateMealDB();
        PurchaseRequest purchase = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");

        boolean mealFound = false;
        for (int i=0; i<mealDB.size(); i++){
            if (mealDB.get(i).getMealID().trim().equals(purchase.mealId)){
                mealFound = true;
                break;
            };
        }
        assertTrue(mealFound);

    }

    @Test
    public void checkPurchaseExists2(){
        List<Client> clients = generateClientDB();
        PurchaseRequest purchase = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");

        boolean mealFound = false;
        for (int i=0; i<clients.size(); i++){
            if (clients.get(i).id.trim().equals(purchase.cookId)){
                mealFound = true;
                break;
            };
        }
        assertTrue(mealFound);

    }

    @Test
    public void checkPurchaseExists3(){
        List<Cook> cookDB = generateCookDB();
        PurchaseRequest purchase = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");

        boolean mealFound = false;
        for (int i=0; i<cookDB.size(); i++){
            if (cookDB.get(i).id.trim().equals(purchase.cookId)){
                mealFound = true;
                break;
            };
        }
        assertTrue(mealFound);

    }

    @Test
    public void checkPurchaseExists4(){
        List<PurchaseRequest> pcDB = generatePCDB();
        PurchaseRequest purchase = new PurchaseRequest("test4", "test4", "test4", "test4", "test4");

        boolean mealFound = false;
        for (int i=0; i<pcDB.size(); i++){
            if (pcDB.get(i).id.trim().equals(purchase.id)){
                mealFound = true;
                break;
            };
        }
        assertTrue(mealFound);

    }

}