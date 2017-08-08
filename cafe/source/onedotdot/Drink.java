package onedotdot;

public abstract class Drink{
    String description = "Unknown Type";
    
    public String getDescription(){
        return description;
    }
    
    public abstract double cost();
}
