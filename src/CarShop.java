import java.util.ArrayList;
import java.util.List;

public class CarShop {
    public int soldCars = 0;
    public List<Car> store = new ArrayList<>();
    public Seller seller = new Seller(this);

    public void recieveCar() {
        seller.receiveCar();
    }

    public Car sellCar() {
        soldCars++;
        return seller.sellCar();
    }

}