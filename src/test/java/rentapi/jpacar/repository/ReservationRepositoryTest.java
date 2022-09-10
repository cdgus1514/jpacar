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
class ReservationRepositoryTest {

    @Autowired ReservationRepository reservationRepository;
    @Autowired CarRepository carRepository;
    @Autowired UserRepository userRepository;

    @Test
    public void 예약조회() throws Exception{

        User user = new User();
        user.setUserName("테스트");
        user.setUserLevel(UserLevel.BRONZE);
        User saveUser = userRepository.save(user);

        Car car = new Car();
        car.setName("제네시스G80");
        car.setCarType(CarType.SUV);
        car.setYear("2020");
        Car saveCar = carRepository.save(car);

        Reservation reservation = new Reservation();
        reservation.setUsers(saveUser);
        reservation.setCars(saveCar);
        reservation.setIsInsuerance(false);
        reservation.setLicenceInfo("132-1234123");
        reservation.setStartDate("2022-09-12");
        reservation.setEndDate("2022-09-14");
        Reservation saveReserve = reservationRepository.save(reservation);


        Reservation findReserve = reservationRepository.findById(saveReserve.getReserveId()).get();


        assertEquals(findReserve.getUsers().getUserId(), saveUser.getUserId());
        assertEquals(findReserve.getUsers().getUserName(), saveUser.getUserName());

        assertEquals(findReserve.getCars().getCarId(), saveCar.getCarId());
        assertEquals(findReserve.getCars().getName(), saveCar.getName());
    }
}