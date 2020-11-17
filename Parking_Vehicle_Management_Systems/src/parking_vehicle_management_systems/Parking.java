package parking_vehicle_management_systems;

import static parking_vehicle_management_systems.Driver_Class.Parking_list;

public class Parking {

    public String area;
    public String name;
    public String type;
    public int space;
    private boolean isparkable = true;
    public String CarParked;

    public Parking(String area, String name, String type, int space, boolean isparkable, String CarParked) {
        this.area = area;
        this.name = name;
        this.type = type;
        this.space = space;
        this.isparkable = isparkable;
        this.CarParked = CarParked;
    }

    Parking() {
        this.name = "";
        this.area = "";
        this.type = "";
        this.space = 0;
        this.isparkable = false;
        this.CarParked = "";
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public boolean isIsparkable() {
        return isparkable;
    }

    public void setIsparkable(boolean isparkable) {
        this.isparkable = isparkable;
    }

    

    public String data() {
        return name + " " + area + " " + type + " " + space;
    }

    boolean parkable(int park) {
        if (space == park) {
            isparkable = false;
        } else {
            isparkable = true;
        }
        return isparkable;
    }

    public String getCarParked() {
        return CarParked;
    }

    public void setCarParked(String CarParked) {
        this.CarParked = CarParked;
    }
    
    //this method is for presenting heading titles BEFORE the "display method" in item class is called
    public static void presentFirst(String N){
        String A ="\n\n\t\t\tList of all the ";
        System.out.println(A.concat(N));
        System.out.println((char)27 + "[36m----------------------------------------------------------------------------------------------------------"+(char)27 + "[0m");
        System.out.println(" NO  AREA             | NAME           | TYPE            | SPACE                | PARKABLE     | CAR PARKED");
        System.out.println((char)27 + "[36m----------------------------------------------------------------------------------------------------------"+(char)27 + "[0m");
    }
    
    //a simple line to finish off the table AFTER the "display method" in item class is called
    public static void presentLast(){
        System.out.println("\n----------------------------------------------------------------------------------------------------------");
    }
        
    /*this is called from the Main class, where theres a menu.
    - this method is so that the right title is passed through the "presentFirst method" according to the "if()"statements
    - inside this method the "display" method in Item class is called depended on the "if()" still
    */    
    public static void Display(String display) {
        if("all".equals(display)){
            presentFirst("ALL PARKING LOTS");
            
            for(int i = 0; i < Parking_list.size(); i++) {
                Parking_list.get(i).display(i);
            }
            presentLast();
        }else if("parkable".equals(display)){
            presentFirst("ALL AVAILABLE PARKINGS BAYS");
            
            for(int i = 0; i < Parking_list.size(); i++) {
                if(Parking_list.get(i).isIsparkable() == false){
                    Parking_list.get(i).display(i);
                }
            }
            presentLast();
        }
    }
    
    public void display(int n) {
        n += 1;   
        System.out.printf("\n%-20s  | %-20s  | %-15s | %-15s  | %-15s  | %-15s | %-15s", n, this.getArea(), this.getName(), this.getType(), this.getSpace(), this.isIsparkable(), this.getCarParked());
    }
}
