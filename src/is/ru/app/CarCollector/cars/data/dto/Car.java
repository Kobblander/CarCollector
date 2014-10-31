package is.ru.app.CarCollector.cars.data.dto;

import java.util.Date;

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

    public Car() {
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        if (factoryNumber != null ? !factoryNumber.equals(car.factoryNumber) : car.factoryNumber != null) return false;
        if (number != null ? !number.equals(car.number) : car.number != null) return false;
        if (registeredAt != null ? !registeredAt.equals(car.registeredAt) : car.registeredAt != null) return false;
        if (registryNumber != null ? !registryNumber.equals(car.registryNumber) : car.registryNumber != null)
            return false;
        if (subType != null ? !subType.equals(car.subType) : car.subType != null) return false;
        if (type != null ? !type.equals(car.type) : car.type != null) return false;

        return true;
    }

}
