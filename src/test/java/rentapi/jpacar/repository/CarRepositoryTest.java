package rentapi.jpacar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import rentapi.jpacar.domain.Car;
import rentapi.jpacar.domain.CarType;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CarRepositoryTest {

    @Autowired CarRepository carRepository;

    @Test
    public void 자동차조회() throws Exception{
        Car car = new Car();

        car.setCarType(CarType.COUPE);
        car.setName("미니쿠페");
        car.setYear("2018");

        Car saveCar = carRepository.save(car);

        Car findCar = carRepository.findById(saveCar.getCarId()).get();

        assertEquals(findCar.getCarType(), car.getCarType());
        assertEquals(findCar.getName(), car.getName());
    }
}