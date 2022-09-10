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
class RepaireRepositoryTest {

    @Autowired RepaireRepository repaireRepository;
    @Autowired CarRepository carRepository;
    @Autowired AccidentRepository accidentRepository;

    @Test
    public void 수리조회() throws Exception {

        Car car = new Car();
        car.setName("BMWM5");
        car.setCarType(CarType.SPORTS);
        car.setYear("2021");
        Car saveCar = carRepository.save(car);

        Accident accident = new Accident();
        accident.setAccidentType(AccidentType.WHEEL);
        accident.setAccidentDetail("전방 우측범퍼 스크래치");
        accident.setRepaire(false);
        Accident saveAccident = accidentRepository.save(accident);


        Car findCar = carRepository.findById(saveCar.getCarId()).get();
        findCar.setAccidents(saveAccident);


        Repaire repaire = new Repaire();
        repaire.setAccidents(findCar.getAccidents());
        repaire.setRepairedCost(2500000L);
        repaire.setReapiredStart("2022-09-15");
        repaire.setRepairedEnd("2022-09-30");
        Repaire saveRepaire = repaireRepository.save(repaire);

        Repaire findRepaire = repaireRepository.findById(saveRepaire.getRepairedId()).get();

        assertEquals(findRepaire.getAccidents().getAccidentId(), findCar.getAccidents().getAccidentId());
        assertEquals(findRepaire.getRepairedCost(), saveRepaire.getRepairedCost());
    }
}