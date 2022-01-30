public class Main {

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        new Thread(new Customer(shop)).start();
        new Thread(new Customer(shop)).start();
        new Thread(new Customer(shop)).start();


        new Thread(null, shop::recieveCar, "Производитель").start();
    }
}