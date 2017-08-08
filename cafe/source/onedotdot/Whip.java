package onedotdot;

public class Whip extends CondimentDecorator{
    Drink drink;
    
    public Whip(Drink d){
        this.drink = d;
    }
    
    public String getDescription(){
        return drink.getDescription() + ". Whip";
    }
    
    public double cost(){
        return drink.cost() + 5;
    }
}
