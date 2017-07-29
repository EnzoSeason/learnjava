class Accum{
    private static Accum a = new Accum();
    private int counter = 0;
    
    private Accum(){}
    
    public static Accum getAccum(){
        return a;
    }
    
    public int getCounter(){
        return counter;
    }
    
    public void updateCounter(int add){
        counter += add;
    }
}
