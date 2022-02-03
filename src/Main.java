public class Main {

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        new Thread(null, shop::sellCar, "Покупатель 1").start();
        new Thread(null, shop::sellCar, "Покупатель 2").start();
        new Thread(null,  shop::sellCar, "Покупатель 3").start();
        new Thread(null, shop::recieveCar, "Производитель").start();

        while(shop.store.size() != 10) {
            Thread.onSpinWait();
        }
    }
}