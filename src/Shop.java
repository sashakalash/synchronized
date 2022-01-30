import java.util.ArrayList;
import java.util.List;

class CarStore {
    private List<Car> store;

    public CarStore() {
        store = new ArrayList<>();
    }

    public void addToStore(Car car) {
        store.add(car);
    }

    public Car getFromStore() {
        return store.remove(0);
    }

    public int getStoreSize() {
        return store.size();
    }
}