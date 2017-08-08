package onedotdot;

public class Grande extends CondimentDecorator{
    Drink drink;
    
    public Grande(Drink d){
        this.drink = d;
    }
    
    public String getDescription(){
        return drink.getDescription() + " , Size Grande";
    }
    
    public double cost(){
        return drink.cost() + 1;
    }
}
