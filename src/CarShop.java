public class CarShop {
    public static CarStore carStore;
    private static final int CAR_SELLING_TIME = 1000;
    private static final int CAR_MANUFACTORING_TIME = 1000;
    private static final Car NEW_CAR = new Car("Toyota");
    public int soldCars = 0;

    public CarShop(CarStore carStore) {
        this.carStore = carStore;
    }

    public synchronized void receiveCar() throws InterruptedException {
        try {
            System.out.println("Продавец ожидает поставку");
            Thread.sleep(CAR_MANUFACTORING_TIME);
            System.out.println("Производитель Toyota выпустил 1 авто");
            carStore.store.add(NEW_CAR);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() throws InterruptedException {
        try {
            while (carStore.store.size() == 0) {
                Thread.sleep(CAR_SELLING_TIME);
                System.out.println("Продавец: Машин нет в наличии в вашей комплектации");
                receiveCar();
                wait();
            }
            Thread.sleep(CAR_SELLING_TIME);
            System.out.println("Продавец: На складе появился автомобиль");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Car newCar = carStore.store.get(0);
        carStore.store.remove(0);
        soldCars++;
        return newCar;
    }
}