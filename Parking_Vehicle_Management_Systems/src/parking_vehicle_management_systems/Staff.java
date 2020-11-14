package parking_vehicle_management_systems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static parking_vehicle_management_systems.Driver_Class.registered_Staff;

public class Staff {
    public String name;
    public String surname;
    public String username;
    public String password;
    static ArrayList<Staff> loggedStaff = new ArrayList<>();
    /* Arraylist will store the staff attributes of the staff those logged on*/
    
    //empty constructor
    public Staff() {
    }    
    
    //parametized constructor, username is auto generated
    public Staff(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.username = generate_username(this.name, this.surname);
        this.password = password;
    }

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    
    /*This method prompts the admin to enter the details of the new Teller they ae about to create
      Receiving all teller credentials and double checking password entry*/
    public void receiveCredentials(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nCreate New Teller Account\n_________________________________________________________________________________________________");
        System.out.print("Enter Teller First Name: ");
        setName(input.nextLine());
        
        System.out.print("Enter Teller Last Name: ");
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
    }

    void RegisterNewTeller() {
        receiveCredentials(); //Receive input for new staff credentials
        
        boolean isTellerExist = false;
        for(Staff teller: registered_Staff){// for loop without the classic "int i" and "i++" increment___ eg for
            if (teller.username.equals(username)&&teller.password.equals(password)){
                isTellerExist = true;
                System.out.println("\nStaff Account Already Exists!\n");
                break;
            }
        } //Checking whether new teller credentials match with existing teller
        if (!isTellerExist){
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
            System.out.println((char)27 +"[33mNew Teller Account Successfully Created."+(char)27 +"[0m");
            System.out.println("_________________________________________________________________________________________________");
        } //Create new staff and add to teller's list if the teller does not exist
    }    
}
