import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int CAR_MANUFACTURING_TIME = 3;
    private static final int CUSTOMER_ARRIVING_TIME = 1;
    private static final int THREAD_DELAY = 0;

    public static void main(String[] args) {
        final CarShop shop = new CarShop();

        ScheduledExecutorService ex1 = makeScheduleExecutor(new Thread(null, shop::sellCar, "Покупатель 1"), CUSTOMER_ARRIVING_TIME);
        ScheduledExecutorService ex2 = makeScheduleExecutor(new Thread(null, shop::sellCar, "Покупатель 2"), CUSTOMER_ARRIVING_TIME);
        ScheduledExecutorService ex3 = makeScheduleExecutor(new Thread(null, shop::sellCar, "Покупатель 3"), CUSTOMER_ARRIVING_TIME);
        ScheduledExecutorService manEx = makeScheduleExecutor(new Thread(null, shop::recieveCar, "Производитель"), CAR_MANUFACTURING_TIME);

        while (shop.soldCars <= 10) {
            Thread.onSpinWait();
        }
        ex1.shutdown();
        ex2.shutdown();
        ex3.shutdown();
        manEx.shutdown();
    }

    public static ScheduledExecutorService makeScheduleExecutor(Runnable thread, int period) {
        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(thread, THREAD_DELAY, period, TimeUnit.SECONDS);
       return executor;
    }
}