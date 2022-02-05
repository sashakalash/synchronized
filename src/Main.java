import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int CAR_MANUFACTURING_TIME = 3;
    private static final int CUSTOMER_ARRIVING_TIME = 1;
    private static final int THREAD_DELAY = 0;

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        ThreadGroup mainGroup = new ThreadGroup("main group");


        new Thread(mainGroup, shop::recieveCar, "Производитель").start();

        while (shop.soldCars < 10) {
            new Thread(mainGroup, shop::sellCar, "Покупатель 1").start();
            new Thread(mainGroup, shop::sellCar, "Покупатель 2").start();
            new Thread(mainGroup, shop::sellCar, "Покупатель 3").start();
        }
        mainGroup.interrupt();
    }

}