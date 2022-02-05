import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {
    private CarShop carShop;
    private static final Car NEW_CAR = new Car("Toyota");
    private static final int CAR_MANUFACTURING_TIME = 1000;

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public void receiveCar() {
        try {
            carShop.lock.lock();
            System.out.println("Производитель выпустил 1 авто");
            carShop.store.add(NEW_CAR);
            Thread.sleep(CAR_MANUFACTURING_TIME);
            carShop.storeEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            carShop.lock.unlock();
        }
    }

    public void sellCar() {
        try {
            carShop.lock.lock();
            while (carShop.store.size() == 0) {
                System.out.println("Продавец: Машин нет в наличии в вашей комплектации");
                carShop.storeEmptyCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            carShop.lock.unlock();
        }
        System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
        carShop.store.remove(0);
    }
}