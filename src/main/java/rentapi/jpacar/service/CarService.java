package rentapi.jpacar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentapi.jpacar.domain.Car;
import rentapi.jpacar.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    @Transactional
    public Long regist(Car car) {
        Car saveCar = Car.insert(car);
        Car result = carRepository.save(saveCar);

        return result.getCarId();
    }

    public Car findCar(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }

    public List<Car> list() {
        return carRepository.findAll();
    }
}
