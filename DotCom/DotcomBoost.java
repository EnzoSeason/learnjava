import java.util.*;

public class DotcomBoost{
    private ArrayList<Dotcom> targets = new ArrayList<Dotcom>();
    private GameHelper helper = new GameHelper();
    private int GuessNum = 0;
    
    public void SetGame(){
        Dotcom one = new Dotcom();
        one.SetName("Baidu.com");
        Dotcom two = new Dotcom();
        two.SetName("Alibaba.com");
        Dotcom three = new Dotcom();
        three.SetName("qq.com");
        targets.add(one);
        targets.add(two);
        targets.add(three);
        
        System.out.println("You have 3 targets to kill");
        System.out.println("Have Fun :)");
        
        for(Dotcom cibe:targets){
            ArrayList<String> newlocation = helper.placeDotCom(1);
            cibe.SetLocation(newlocation);
        }
    }
    public void CheckUserGuess(String userGuess){
        GuessNum++;
        String result = "miss";
        for (Dotcom cibes:targets){
            result = cibes.checkstatus(userGuess);
            if(result.equals("hit")){
                break;
            }
            if(result.equals("kill")){
                targets.remove(cibes);
                break;
            }
        }
        System.out.println(result);
    }
    
    public String FinishGame(){
        System.out.println("All your dads are gone, good job!");
        System.out.println("You take "+ GuessNum +" to finish your mission.");
        GuessNum = 0;
        System.out.println("Play again ? Y/N");
        Scanner scan = new Scanner(System.in);
        String Ask =scan.nextLine();
        
        while ((!Ask.equals("Y")) && (!Ask.equals("N"))){
            System.out.println("I don't understand what you say, please tell me Y/N");
            Ask = scan.nextLine();
        }
        return Ask;
    }
    
    public String StartGame(){
        String Re = "N";
        while(!targets.isEmpty()){
            String UserGuess = helper.getUserInput("Enter an area number");
            CheckUserGuess(UserGuess);
        }
        Re = FinishGame();
        return Re;
    }
    
    public static void main(String[] args){
        DotcomBoost Game = new DotcomBoost();
        String again = "Y";
        while (again.equals("Y")){
            Game.SetGame();
            again = Game.StartGame();
        }
    }
    
}
