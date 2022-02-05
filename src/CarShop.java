import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarShop {
    public int soldCars = 0;
    public List<Car> store = new ArrayList<>();
    public ReentrantLock lock = new ReentrantLock(true);
    public Condition storeEmptyCondition = lock.newCondition();
    public Seller seller = new Seller(this);


    public void recieveCar() {
        seller.receiveCar();
    }

    public void sellCar() {
        System.out.printf("%s зашел в автосалон\n", Thread.currentThread().getName());
        seller.sellCar();
        soldCars++;
    }
}