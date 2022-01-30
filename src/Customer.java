class Customer implements Runnable {
    public CarShop shop;

    public Customer(CarShop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        System.out.printf("%s зашел в автосалон\n", getName());
        shop.sellCar();
        System.out.printf("%s уехал на новеньком авто\n", getName());
    }

    private String getName() {
        return Thread.currentThread().getName();
    }
}