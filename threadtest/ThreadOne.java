class ThreadOne implements Runnable{
    Accum a = Accum.getAccum();
    public void run(){
        for(int x=0; x<98; x++){
            increment();
        }
        System.out.println("one" + a.getCounter());
    }
    
    private synchronized void increment(){
        a.updateCounter(1000);
    }
}
