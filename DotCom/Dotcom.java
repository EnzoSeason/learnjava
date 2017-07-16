import java.util.*;

public class Dotcom{
    private ArrayList<String> location;
    private String name;
    
    public void SetLocation(ArrayList<String> loc){
        location = loc;
    }
    
    public void SetName(String n){
        name = n;
    }
    
    public String checkstatus(String UserInput){
        String result = "miss";
        int index = location.indexOf(UserInput);
        if (index >= 0){
            location.remove(index);
            if(location.isEmpty()){
                result = "kill";
            }
            else{
                result = "hit";
            }
        }
        return result;
    }
}
