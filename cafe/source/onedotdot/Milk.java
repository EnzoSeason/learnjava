package onedotdot;

public class Milk extends CondimentDecorator{
    Drink drink;
    
    public Milk(Drink d){
        this.drink = d;
    }
    
    public String getDescription(){
        return drink.getDescription() + " ,Milk";
    }
    
    public double cost(){
        return drink.cost() + 3;
    }
}
