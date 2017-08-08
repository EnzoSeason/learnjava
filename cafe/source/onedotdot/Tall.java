package onedotdot;

public class Tall extends CondimentDecorator{
    Drink drink;
    
    public Tall(Drink d){
        this.drink = d;
    }
    
    public String getDescription(){
        return drink.getDescription() + " , Size Tall";
    }
    
    public double cost(){
        return drink.cost() + 0;
    }
}
