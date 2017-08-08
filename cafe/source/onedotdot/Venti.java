package onedotdot;

public class Venti extends CondimentDecorator{
    Drink drink;
    
    public Venti(Drink d){
        this.drink = d;
    }
    
    public String getDescription(){
        return drink.getDescription() + " , Size Venti";
    }
    
    public double cost(){
        return drink.cost() + 3;
    }
}
