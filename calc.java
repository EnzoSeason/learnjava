class calc{
    public static void main(String[] args){
        calc test = new calc();
        int a = test.calcArea(7,12);
        int d = test.calcArea(57);
        test.calcArea(2,3);
        int g = test.calcArea();
        test.calcArea();
        byte h = test.calcArea(4,10);
        long lo = test.calcArea(3,19);
        short c =7;
        long cc = 10;
        int te = test.calcArea(c,10);
        int ts = test.calcarea(cc,10);
    }
    int calcArea(int height, int width){
        return height * width;
    }
}
