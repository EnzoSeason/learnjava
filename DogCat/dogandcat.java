public class dogandcat{
    public static void main(String[] args){
        animal[] list = new animal[2];
        Dog d = new Dog();
        Cat c = new Cat();
        d.setName("Frank");
        c.setName("Ann");
        System.out.println("I have pets.");
        System.out.println(d.getClass());
        System.out.println(c.getClass());
        System.out.println("say hi");
        d.bark();
        c.bark();
        list[0] = d;
        list[1] = c;
        System.out.println("In my list");
        System.out.println(list[0].getClass());
        System.out.println(list[1].getClass());
    }
}
