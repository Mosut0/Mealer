package com.uottawa.seg2105_group20_project;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.Toast;

public class MealerUnitTests {

    /**
     * HELPER METHODS
     */

    /**
     * Generates list of clients that act as a database.
     * @return databaseClient
     */
    public static List<Client> generateClientDB(){
        List<Client> databaseClient = new ArrayList<>();

        Client clientTest1 = new Client("test1", "test1", "test1", "test1", "test1", "test1", "4865848");
        Client clientTest2 = new Client("test2", "test2", "test2", "test2", "test2", "test2", "4865848");
        Client clientTest3 = new Client("test3", "test3", "test3", "test3", "test3", "test3", "4865848");
        Client clientTest4 = new Client("test4", "test4", "test4", "test4", "test4", "test4", "4865848");
        Client clientTest5 = new Client("test5", "test5", "test5", "test5", "test5", "test5", "4865848");
        Client clientTest6 = new Client("test6", "test6", "test6", "test6", "test6", "test6", "4865848");
        Client clientTest7 = new Client("test7", "test7", "test7", "test7", "test7", "test7", "4865848");

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

        Client client = new Client(id, fName, lName, email, password, address, creditCard);

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
     * DELIVERABLE 2 UNIT TESTS
     */

    @Test
    public void checkExistingClient(){
        List<Client> clientDB = generateClientDB();
        Client clientTest4 = new Client("test4", "test4", "test4", "test4", "test4", "test4", "4865848");

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
        Client clientTest4 = new Client("test4", "test4", "test4", "test4", "test99", "test4", "4865848");

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

        Client client = new Client(id, fName, lName, email, password, address, creditCard);

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

}