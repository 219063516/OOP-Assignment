package parking_vehicle_management_systems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver_Class {

    static ArrayList<Staff> registered_Staff = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        Start();
    }

    private static void Start() throws IOException, InterruptedException {
        System.out.println("____________________________________________________");
        System.out.println("                   WELCOME                          ");
        System.out.println("                    to the                          ");
        System.out.println("       NUST Parking & Vehicle Management Systems    ");
        System.out.println("____________________________________________________");
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat dateformatter = new SimpleDateFormat(" dd MMMMMMMMM',' yyyy ");
        System.out.println("            Date - " + dateformatter.format(calender.getTime()));
        Choose();
    }

    public static void Choose() throws IOException, InterruptedException {
        
        int logChoice;
        System.out.println("____________________________________________________" + (char) 27 + "[0m");
        System.out.println("1. Login                                       ");
        System.out.println("2. Register                                          ");
        System.out.println("3. Quit                                          ");
        System.out.println("____________________________________________________" + (char) 27 + "[0m");
        System.out.print("Enter Log In Choice: ");
        logChoice = input.nextInt();

        switch (logChoice) {
            case 1:
                populate_Staff_List();
                staff_login();
                registered_Staff.clear();
                Choose();
                break;
            case 2:
                populate_Staff_List();
                Register();
                registered_Staff.clear();
                Choose();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
                Choose();
                break;
        }
    }

    private static void staff_login() throws InterruptedException, IOException {
        form:
        for (;;) {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            System.out.println("___________________________________________________");
            System.out.print("USERNAME: ");
            String user = br.readLine();
            System.out.print("PASSWORD: ");
            String pass = br.readLine();
            System.out.println("___________________________________________________");
            System.out.println("\n\n...Logging you in...");
            Thread.sleep(2000);

            boolean isValid = false;
            Staff log = new Staff(user, pass);
            isValid = log.isValidStaff();
            
            if(isValid == true){
            
            }else{
            
            }
        }
    }

    static void populate_Staff_List() {
        File file = new File("staff_list.txt");
        try {
            Scanner read = new Scanner(file);

            while (read.hasNext()) {
                String name = read.next();
                String surname = read.next();
                String password = read.next();
                
                Staff user = new Staff(name, surname, password);
                registered_Staff.add(user);
            }

        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Driver_Class.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Item data file not found!");
        }
    }

    private static void Register() {
        Staff new_staff = new Staff();
        new_staff.RegisterNewTeller();
    }

}
