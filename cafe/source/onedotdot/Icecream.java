package onedotdot;

public class Icecream extends CondimentDecorator{
    Drink drink;
    
    public Icecream(Drink d){
        this.drink = d;
    }
    
    public String getDescription(){
        return drink.getDescription() + " .Icecream";
    }
    
    public double cost(){
        return drink.cost() + 8;
    }
}
