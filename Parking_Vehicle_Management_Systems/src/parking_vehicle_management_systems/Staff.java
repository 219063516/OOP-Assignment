package parking_vehicle_management_systems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static parking_vehicle_management_systems.Driver_Class.Vehicle_list;
import static parking_vehicle_management_systems.Driver_Class.input;
import static parking_vehicle_management_systems.Driver_Class.registered_Staff;

public class Staff  extends Person{
    public String username;
    public String password;
    static ArrayList<Staff> loggedStaff = new ArrayList<>();
    public boolean has_Parking_bay;
    public String parking_space;
    public Vehicle ownsCar;
    /* Arraylist will store the staff attributes of the staff those logged on*/
    
    //empty constructor
    public Staff() {
        this.parking_space = "";
    }    
    
    //parametized constructor, username is auto generated
    public Staff(String name, String surname, String password, boolean has_Parking_bay, String parking_space, String Color, String plat_no, int Year, String Make, String Model) {
        super(name, surname);
        this.username = generate_username(this.name, this.surname);
        this.ownsCar = new Vehicle(Color, plat_no, Year, Make, Model); //Composition Relationship
        this.password = password;
        this.has_Parking_bay = has_Parking_bay;
        this.parking_space = parking_space;
    }

    public Staff(String username, String password, String parking_space) {
        this.username = username;
        this.password = password;
        this.parking_space = parking_space;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<Staff> getLoggedStaff() {
        return loggedStaff;
    }

    public static void setLoggedStaff(ArrayList<Staff> loggedStaff) {
        Staff.loggedStaff = loggedStaff;
    }

    private String generate_username(String name, String surname) {
        return name.charAt(0) + surname;
    }  

    public boolean get_Parking_bay() {
        return has_Parking_bay;
    }

    public void setHas_Parking_bay(boolean has_Parking_bay) {
        this.has_Parking_bay = has_Parking_bay;
    }

    public String getParking_space() {
        return parking_space;
    }

    public void setParking_space(String parking_space) {
        this.parking_space = parking_space;
    }

    public Vehicle getOwnsCar() {
        return ownsCar;
    }

    public void setOwnsCar(Vehicle ownsCar) {
        this.ownsCar = ownsCar;
    }
       
    public boolean isValidStaff(){
        boolean isValid = false;
        for (int i = 0; i < registered_Staff.size(); i++){
            if (registered_Staff.get(i).username.equals(username) && registered_Staff.get(i).password.equals(password)) {
                isValid = true;
                loggedStaff.add(registered_Staff.get(i));
            }
        }
        return isValid; //Validating staff credentials through registered_Staff list
    }
        
    /*This method prompts the admin to enter the details of the new staff they ae about to create
      Receiving all staff credentials and double checking password entry*/
    public void receiveCredentials(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nCreate New Staff Account\n_________________________________________________________________________________________________");
        System.out.print("Enter Staff First Name: ");
        setName(input.nextLine());
        
        System.out.print("Enter Staff Last Name: ");
        setSurname(input.nextLine());
        
        System.out.print("Enter new Username: ");
        username = input.nextLine();//attribute username is declared in this class
        
        //for tightening security, the password should atleast be strong
        System.out.print("Set a password ( \n- minimum 8 chars; \n- minimum 1 digit, \n- 1 lowercase, \n- 1 uppercase, \n- 1 special character[!@#$%^&*_]) :");
        String passwordFirstTyped = input.next();
        input.nextLine();
        
        //a "while" statement to assure that the password if of the required conditions
        while(!passwordFirstTyped.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
        {
            System.err.println("Invalid password condition. Set again :");//"err" instead of "out" to emphiseze the warning
            passwordFirstTyped=input.next();//you're prompted to re-enter
        }
        System.out.print("Retype new password: ");
        String passwordRetyped = input.next();
        input.nextLine();
        
        //another "while" to exception handle an incorrect password
        while(!passwordRetyped.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
        {
            System.err.println("Invalid password condition. Set again :");
            passwordRetyped=input.next();//you're prompted to re-enter
        }
        
        //another while if the password doesnt match the retyped one
        while(!passwordFirstTyped.equals(passwordRetyped)){
            System.out.println("__________________________________________________________");
            System.err.println("     Retyped password does not match! Try again.         ");
            System.out.println("__________________________________________________________");
            System.out.print("Enter new password: ");//your prompted to re-enter the new password
            passwordFirstTyped = input.nextLine();
            System.out.print("Retype new password: ");
            passwordRetyped = input.nextLine();////your prompted to re-enter the typed password
            System.out.println("__________________________________________________________");
        }
        
        //only after all this whiles are passed is then the password accepted
        password = passwordFirstTyped;
        
        has_Parking_bay = false;//new staff has no Parking bay
        parking_space = "";
    }

    void RegisterNewStaff() {
        receiveCredentials(); //Receive input for new staff credentials
        
        boolean isStaffExist = false;
        for(Staff staff: registered_Staff){// for loop without the classic "int i" and "i++" increment___ eg for
            if (staff.username.equals(username)&&staff.password.equals(password)){
                isStaffExist = true;
                System.out.println("\nStaff Account Already Exists!\n");
                break;
            }
        } //Checking whether new Staff credentials match with existing Staff
        
        
        if (!isStaffExist){
            registered_Staff.add(this);
            
            File file = new File("staff_list.txt");
            try (final FileWriter w = new FileWriter(file.getAbsoluteFile(),true);
                    final BufferedWriter buw = new BufferedWriter(w);
                    final PrintWriter out = new PrintWriter(buw)) {
                if (!file.exists()) {
                    file.createNewFile();
                }
                int s = registered_Staff.size()-1;
                out.println(registered_Staff.get(s).getName() + " " + registered_Staff.get(s).getSurname() + " " + registered_Staff.get(s).getUsername() + " " +registered_Staff.get(s).getPassword());

            }catch (IOException e) {

            }
            
            System.out.println("----------------- NEW YOUR VEHICLE DETAILS--------------------");
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
            
            this.setOwnsCar(new_car);            
            Vehicle_list.add(new_car);
            System.out.println("--. SUCCESSFUL VEHICLE ADDITION -");
            
            System.out.println((char)27 +"[33mNew Staff Account Successfully Created."+(char)27 +"[0m");
            System.out.println("_________________________________________________________________________________________________");
        } //Create new staff and add to staff's list if the staff does not exist
    } 
}
