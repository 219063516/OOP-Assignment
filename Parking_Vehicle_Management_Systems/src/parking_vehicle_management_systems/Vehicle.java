package parking_vehicle_management_systems;

public class Vehicle{

    static void Display(String all) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void Display(String search, String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String Color;
    public String plat_no;
    private int Year;
    private String Make;
    private String Model;

    public Vehicle(String Color, String plat_no, int Year, String Make, String Model) {
        this.Color = Color;
        this.plat_no = plat_no;
        this.Year = Year;
        this.Make = Make;
        this.Model = Model;
    }

    public Vehicle() {
    }
    
    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getPlat_no() {
        return plat_no;
    }

    public void setPlat_no(String plat_no) {
        this.plat_no = plat_no;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    
}
