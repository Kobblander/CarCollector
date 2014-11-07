package is.ru.app.CarCollector.game.models;

import java.util.List;

/**
 * <h1>TypeStats</h1>
 * <h2>is.ru.app.CarCollector.game.models</h2>
 * <p></p>
 * Created on 7.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class TypeStats {

    private CarType carType;
    private List<CarSubType> carSubTypes;

    public TypeStats() {
    }

    public TypeStats(CarType carType, List<CarSubType> carSubTypes) {
        this.carType = carType;
        this.carSubTypes = carSubTypes;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<CarSubType> getCarSubTypes() {
        return carSubTypes;
    }

    public void setCarSubTypes(List<CarSubType> carSubTypes) {
        this.carSubTypes = carSubTypes;
    }
}
