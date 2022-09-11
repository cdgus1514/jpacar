package rentapi.jpacar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rentapi.jpacar.domain.Car;
import rentapi.jpacar.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/car/regist")
    public ResponseEntity<Long> regist(@RequestBody Car car) {
        log.debug("param check : {}, {}, {}", car.getName(), car.getYear(), car.getCarType());
        Long result = carService.regist(car);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/car/{no}")
    public ResponseEntity<Car> car(@PathVariable("no") Long carId) {
        return ResponseEntity.ok(carService.findCar(carId));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> carList() {
        return ResponseEntity.ok(carService.list());
    }
}
