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

        makeScheduleExecutor(new Thread(mainGroup, shop::sellCar, "Покупатель 1"), CUSTOMER_ARRIVING_TIME);
        makeScheduleExecutor(new Thread(mainGroup, shop::sellCar, "Покупатель 2"), CUSTOMER_ARRIVING_TIME);
        makeScheduleExecutor(new Thread(mainGroup, shop::sellCar, "Покупатель 3"), CUSTOMER_ARRIVING_TIME);
        makeScheduleExecutor(new Thread(mainGroup, shop::recieveCar, "Производитель"), CAR_MANUFACTURING_TIME);

        while(shop.soldCars < 10) {
            Thread.onSpinWait();
        }
        mainGroup.interrupt();
    }

    public static void makeScheduleExecutor(Runnable thread, int period) {
        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(thread, THREAD_DELAY, period, TimeUnit.SECONDS);
    }
}