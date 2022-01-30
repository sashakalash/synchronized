public class CarShop {
    public int soldCars = 0;
    public Seller seller = new Seller(this);
    public  CarStore store = new CarStore();

    public void recieveCar() {
        seller.receiveCar();
    }

    public Car sellCar() {
        soldCars++;
        return seller.sellCar();
    }

}