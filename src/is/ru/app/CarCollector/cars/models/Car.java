package is.ru.app.CarCollector.cars.models;

/**
 * <h1>Car</h1>
 * <h2>is.ru.app.CarCollector.cars.rest.dto</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Car {

    String registryNumber;
    String number;
    String factoryNumber;
    String type;
    String subType;
    String color;
    String registeredAt;
    String status;
    String nextCheck;
    String pollution;
    String weight;

    public Car() {
    }

    public Car(String registryNumber, String number, String factoryNumber, String type, String subType, String color, String registeredAt, String status, String nextCheck, String pollution, String weight) {
        this.registryNumber = registryNumber;
        this.number = number;
        this.factoryNumber = factoryNumber;
        this.type = type;
        this.subType = subType;
        this.color = color;
        this.registeredAt = registeredAt;
        this.status = status;
        this.nextCheck = nextCheck;
        this.pollution = pollution;
        this.weight = weight;
    }

    public String getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextCheck() {
        return nextCheck;
    }

    public void setNextCheck(String nextCheck) {
        this.nextCheck = nextCheck;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Car{\n" +
                "\tregistryNumber='" + registryNumber + '\'' + "\n" +
                "\tnumber='" + number + '\'' + "\n" +
                "\tfactoryNumber='" + factoryNumber + '\'' + "\n" +
                "\ttype='" + type + '\'' + "\n" +
                "\tsubType='" + subType + '\'' + "\n" +
                "\tcolor='" + color + '\'' + "\n" +
                "\tregisteredAt=" + registeredAt + "\n" +
                "\tstatus=" + status + "\n" +
                "\tnextCheck=" + nextCheck + "\n" +
                "\tpollution=" + pollution + "\n" +
                "\tweight=" + weight + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (factoryNumber != null ? !factoryNumber.equals(car.factoryNumber) : car.factoryNumber != null) return false;
        if (nextCheck != null ? !nextCheck.equals(car.nextCheck) : car.nextCheck != null) return false;
        if (number != null ? !number.equals(car.number) : car.number != null) return false;
        if (pollution != null ? !pollution.equals(car.pollution) : car.pollution != null) return false;
        if (registeredAt != null ? !registeredAt.equals(car.registeredAt) : car.registeredAt != null) return false;
        if (registryNumber != null ? !registryNumber.equals(car.registryNumber) : car.registryNumber != null)
            return false;
        if (status != null ? !status.equals(car.status) : car.status != null) return false;
        if (subType != null ? !subType.equals(car.subType) : car.subType != null) return false;
        if (type != null ? !type.equals(car.type) : car.type != null) return false;
        if (weight != null ? !weight.equals(car.weight) : car.weight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = registryNumber != null ? registryNumber.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (factoryNumber != null ? factoryNumber.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (subType != null ? subType.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (registeredAt != null ? registeredAt.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (nextCheck != null ? nextCheck.hashCode() : 0);
        result = 31 * result + (pollution != null ? pollution.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
