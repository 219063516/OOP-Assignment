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
import static parking_vehicle_management_systems.Staff.loggedStaff;

public class Driver_Class {

    static ArrayList<Staff> registered_Staff = new ArrayList<>();
    static ArrayList<Parking> Parking_list = new ArrayList<>();
    static ArrayList<Vehicle> Vehicle_list = new ArrayList<>();
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
        System.out.println("____________________________________________________");
        System.out.println("1. Admin Login                                       ");
        System.out.println("1. Staff Login                                       ");
        System.out.println("2. Register                                          ");
        System.out.println("3. Quit                                          ");
        System.out.println("____________________________________________________");
        System.out.print("Enter Log In Choice: ");
        logChoice = input.nextInt();

        switch (logChoice) {
            case 1:
                populate_Staff_List();
                login("ADMIN");
                Choose();
                break;
            case 2:
                populate_Staff_List();
                login("STAFF");
                registered_Staff.clear();
                Choose();
                break;
            case 3:
                populate_Staff_List();
                Register();
                registered_Staff.clear();
                Choose();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
                Choose();
                break;
        }
    }

    private static void login(String user_passed) throws InterruptedException, IOException {
        form:
        for (;;) {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            System.out.println("_________________ " + user_passed + " ______________________");
            System.out.print("USERNAME: ");
            String user = br.readLine();
            System.out.print("PASSWORD: ");
            String pass = br.readLine();
            System.out.println("___________________________________________________");
            System.out.println("\n\n...Logging you in...");
            Thread.sleep(2000);

            boolean isValid = false;
            if ("STAFF".equals(user_passed)) {
                Staff log = new Staff(user, pass);
                isValid = log.isValidStaff();

                if (isValid == true) {

                    //Staff without parking bay are required to register and request for a parking space.
                    if (log.get_Parking_bay() == false) {
                        System.out.println("___________________________________________________");
                        System.out.println("Welcome " + user + ", you do not have a parking bay");
                        System.out.println("Directing you to register ");
                        Thread.sleep(2000);
                        populate_Parking_List();
                        Staff_Register(log);
                        Parking_list.clear();
                    } else {
                        System.out.println("___________________________________________________");
                        System.out.println("Welcome " + user + ", you DO HAVE a parking bay");
                        System.out.println("Directing you to Staff Menu ");
                        Thread.sleep(2000);
                        staff_menu();
                    }
                } else {
                    System.out.println("Staff member is not valid");
                }
            } else if ("ADMIN".equals(user_passed)) {
                Admin_Menu();
            }
        }
    }

    private static void Register() {
        Staff new_staff = new Staff();
        new_staff.RegisterNewTeller();
    }

    private static void admin_menu() {
        form:
        for (;;) {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            System.out.println("_________________ ADMIN MENU _____________________");
            System.out.println("1. Display occupied parking                 ");
            System.out.println("2. Print details of all parked vehicles  ");
            System.out.println("3. information on different types of vehicles                                    ");
            System.out.println("4. information on the parking bay                               ");
            System.out.println("5. information on payment                                  ");
            System.out.println("6. report on owner registration");
            System.out.println("7. Display all registered vehicles");
            System.out.println("8. Display all registered Owners");
            System.out.println("9. Display all registered Owners");
            System.out.println("1. ADD Parking Lot                                ");
            System.out.println("2. REMOVE Parking Lot                             ");
            System.out.println("3. ADD Vehicle                                    ");
            System.out.println("4. REMOVE Vehicle                                 ");
            System.out.println("5. VIEW Vehicle                                   ");
            System.out.println("10. Quit                                           ");
            System.out.println("___________________________________________________");
            System.out.print("Enter Log In Choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    populate_Parking_List();
                    add_parking();
                    Parking_list.clear();
                    admin_menu();
                    break;
                case 2:
                    populate_Parking_List();
                    Parking.Display("all");
                    remove_parking();
                    Parking_list.clear();
                    admin_menu();
                    break;
                case 3:
                    populate_Vehicle_List();
                    Vehicle.Display("all");
                    add_vehicle();
                    Vehicle_list.clear();
                    admin_menu();
                    break;
                case 4:
                    populate_Parking_List();
                    Parking.Display("all");
                    remove_vehicle();
                    Parking_list.clear();
                    admin_menu();
                    break;
                case 5:
                    populate_Parking_List();
                    String term = "";
                    Vehicle.Display("search", term);
                    Parking_list.clear();
                    admin_menu();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    staff_menu();
                    break;
            }
        }
    }

    private static void staff_menu() {
        form:
        for (;;) {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            System.out.println("_________________ STAFF MENU _____________________");
            System.out.println("1. View Status                                   ");
            System.out.println("1. Park Car                                      ");
            System.out.println("2. Unpark Car                                    ");
            System.out.println("3. CHeck Accumulate Amount                       ");
            System.out.println("4. Quit                                          ");
            System.out.println("__________________________________________________");
            System.out.print("Enter Log In Choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    view_status();
                    staff_menu();
                    break;
                case 2:
                    parkcar();
                    staff_menu();
                    break;
                case 3:
                    un_parkcar();
                    staff_menu();
                    break;
                case 4:
                    populate_Parking_List();
                    Parking.Display("all");
                    remove_vehicle();
                    Parking_list.clear();
                    staff_menu();
                    break;
                case 5:
                    check_balance();
                    staff_menu();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    staff_menu();
                    break;
            }
        }
    }

    private static void populate_Vehicle_List() {
        File file = new File("parking_list.txt");
        try {
            Scanner read = new Scanner(file);

            while (read.hasNext()) {
                String name = read.next();
                String area = read.next();
                String type = read.next();
                int space = read.nextInt();
                String parkable_ = read.next();
                boolean parkable = false;
                String carparked = "";

                if ("true".equals(parkable_)) {
                    parkable = true;
                    carparked = read.next();
                }

                Parking parking_lot = new Parking(name, area, type, space, parkable, carparked);
                Parking_list.add(parking_lot);
            }

        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Driver_Class.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Parking data file not found!");
        }
    }

    private static void populate_Parking_List() {
        File file = new File("parking_list.txt");
        try {
            Scanner read = new Scanner(file);

            while (read.hasNext()) {
                String name = read.next();
                String area = read.next();
                String type = read.next();
                int space = read.nextInt();
                String parkable_ = read.next();
                boolean parkable = false;
                String carparked = "";

                if ("true".equals(parkable_)) {
                    parkable = true;
                    carparked = read.next();
                }

                Parking parking_lot = new Parking(name, area, type, space, parkable, carparked);
                Parking_list.add(parking_lot);
            }

        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Driver_Class.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Parking data file not found!");
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
                String bay = read.next();
                boolean has_Parking_bay = false;
                String parking = "";
                if ("true".equals(bay)) {
                    has_Parking_bay = true;
                    parking = read.next();
                }

                Staff user = new Staff(name, surname, password, has_Parking_bay, parking);
                registered_Staff.add(user);
            }

        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Driver_Class.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Staff data file not found!");
        }
    }

    private static void add_parking() {
        System.out.println("----------------- NEW PARKING LOT --------------------");
        System.out.print("Enter Name: ");
        String name = input.next();
        System.out.print("Enter Area ");
        String area = input.next();
        System.out.print("Enter Type ");
        String type = input.next();
        System.out.print("Enter Space: ");
        int space = input.nextInt();

        //new parking has no car parked
        Parking new_parking = new Parking(name, area, type, space, false, "");
        Parking_list.add(new_parking);
        System.out.println("-------- SUCCESSFUL PARKING ADDITION ------------------");
    }

    private static void remove_parking() {
        System.out.println("--------------- REMOVING PARKING LOT --------------------");
        System.out.print("Enter Name: ");
        String name = input.next();

        for (int i = 0; i < Parking_list.size(); i++) {
            if (name.equals(Parking_list.get(i).getName())) {
                Parking_list.remove(Parking_list.get(i));
                System.out.println("-------- SUCCESSFUL PARKING REMOVAL ------------------");
            }
        }
    }

    private static void Staff_Register(Staff log) {
        Parking.Display("parkable");

        System.out.println("ENTER NAME NAME of BAY you want: ");
        String name = input.next();

        System.out.println("ENTER NAME AREA of BAY you want: ");
        String area = input.next();

        for (int i = 0; i < Parking_list.size(); i++) {
            if (name.equals(Parking_list.get(i).getName()) && area.equals(Parking_list.get(i).getArea())) {
                log.setParking_space(Parking_list.get(i).getName());
                System.out.println("-------- SUCCESSFUL REGISTERED TO A PARKING BAY ------------------");

            }
        }
    }

    private static void add_vehicle() {
        System.out.println("----------------- NEW VEHICLE --------------------");
        System.out.print("Enter Vehicle Color: ");
        String Color = input.next();
        System.out.print("Enter Plat Number ");
        String plat_no = input.next();
        System.out.print("Enter Year ");
        int Year = input.nextInt();
        System.out.print("Enter Make: ");
        String Make = input.next();
        System.out.print("Enter Model: ");
        String Model = input.next();

        //new parking has no car parked
        Vehicle new_car = new Vehicle(Color, plat_no, Year, Make, Model);

        Vehicle_list.add(new_car);
        System.out.println("-------- SUCCESSFUL VEHICLE ADDITION ------------------");
    }

    private static void remove_vehicle() {
        System.out.println("----------------- REMOVING VEHICLE ------------------------");
        System.out.print("Enter Color: ");
        String Color = input.next();
        System.out.print("Enter Model: ");
        String Model = input.next();

        for (int i = 0; i < Vehicle_list.size(); i++) {
            if (Color.equals(Vehicle_list.get(i).getColor()) && Model.equals(Vehicle_list.get(i).getModel())) {
                Vehicle_list.remove(Vehicle_list.get(i));
                System.out.println("-------- SUCCESSFUL VEHICLE REMOVAL ------------------");
            }
        }
    }

    private static void Admin_Menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void check_balance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void un_parkcar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void parkcar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void view_status() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
