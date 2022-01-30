public class Seller {
    private CarShop carShop;
    private static final int CAR_SELLING_TIME = 1000;
    private static final int CAR_MANUFACTORING_TIME = 1000;
    private static final Car NEW_CAR = new Car("Toyota");

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void receiveCar() {
        try {
            System.out.println("Продавец ожидает поставку");
            while (carShop.store.getStoreSize() != 5) {
                Thread.sleep(CAR_MANUFACTORING_TIME);
                System.out.println("Производитель Toyota выпустил 1 авто");
                carShop.store.addToStore(NEW_CAR);
                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Car sellCar() {
        try {
            while (carShop.store.getStoreSize() == 0) {
                Thread.sleep(CAR_SELLING_TIME);
                System.out.println("Продавец: Машин нет в наличии в вашей комплектации");
                wait();
            }
            Thread.sleep(CAR_SELLING_TIME);
            System.out.println("Продавец: На складе появился автомобиль");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carShop.store.getFromStore();
    }
}