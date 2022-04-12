public class AnonymousInnerClass {
    interface Greeter {
        void wish();
    }


    public static void main(String[] args) {
        Greeter englishGreet = new Greeter() {
            public void wish() {
                System.out.println("Hi");
            }
        };

        Greeter frenchGreet = () -> System.out.println("tout le monde");

        englishGreet.wish(); // Hi
        frenchGreet.wish(); // tout le monde
    }
}