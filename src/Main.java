public class Main {

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        Thread customer1 = new Thread(new Customer(shop));
        customer1.setName("Покупатель 1");
        Thread customer2 = new Thread(new Customer(shop));
        customer2.setName("Покупатель 2");
        Thread customer3 = new Thread(new Customer(shop));
        customer3.setName("Покупатель 3");

        customer1.start();
        customer2.start();
        customer3.start();
        new Thread(null, shop::recieveCar, "Производитель").start();

        while(shop.store.getStoreSize() != 10) {
            Thread.onSpinWait();
        }

        customer1.interrupt();
        customer2.interrupt();
        customer3.interrupt();
    }
}