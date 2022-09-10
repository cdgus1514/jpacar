package rentapi.jpacar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import rentapi.jpacar.domain.*;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class AccidentRepositoryTest {

    @Autowired AccidentRepository accidentRepository;
    @Autowired UserRepository userRepository;
    @Autowired CarRepository carRepository;
    @Autowired ReservationRepository reservationRepository;

    @Test
    public void 사고조회() throws Exception {

        Accident accident = new Accident();
        accident.setAccidentType(AccidentType.WHEEL);
        accident.setAccidentDetail("우측후방 타이어 휠 스크래치");
        accident.setRepaire(false);
        Accident saveAccident = accidentRepository.save(accident);

        Accident findAccident = accidentRepository.findById(saveAccident.getAccidentId()).get();

        assertEquals(findAccident.getAccidentId(), saveAccident.getAccidentId());
    }

    @Test
    void 사고등록() throws Exception{

        Car car = new Car();
        car.setName("제네시스G80");
        car.setCarType(CarType.SUV);
        car.setYear("2020");
        Car saveCar = carRepository.save(car);

        Accident accident = new Accident();
        accident.setAccidentType(AccidentType.WHEEL);
        accident.setAccidentDetail("우측후방 타이어 휠 스크래치");
        accident.setRepaire(false);
        Accident saveAccident = accidentRepository.save(accident);


        Car findCar = carRepository.findById(saveCar.getCarId()).get();
        findCar.setAccidents(saveAccident);
    }
}