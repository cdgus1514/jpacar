package rentapi.jpacar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import rentapi.jpacar.QuerydslConfig;
import rentapi.jpacar.domain.*;
import rentapi.jpacar.repository.querydsl.ReserveQueryRepository;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReservationRepositoryTest {

    @Autowired ReservationRepository reservationRepository;
    @Autowired CarRepository carRepository;
    @Autowired UserRepository userRepository;

    @Autowired
    ReserveQueryRepository reserveQueryRepository;

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

    @Test
    public void 예약조회_쿼리dsl() throws Exception {

        User user = new User();
        user.setUserName("테스트");
        user.setUserLevel(UserLevel.DIAMOND);
        User saveU = userRepository.save(user);

        Car car = new Car();
        car.setCarType(CarType.SEDAN);
        car.setName("Benz-E class");
        car.setYear("2019");
        car.setUseCount(16);
        Car saveC = carRepository.save(car);


        User findU = userRepository.findById(saveU.getUserId()).get();
        Car findC = carRepository.findById(saveC.getCarId()).get();

        Reservation reserve = new Reservation();
        reserve.setUsers(findU);
        reserve.setCars(findC);
        reserve.setIsInsuerance(true);
        reserve.setLicenceInfo("지역-00-123456-00");
        reserve.setStartDate("2022-09-20");
        reserve.setEndDate("2022-09-23");
        Reservation saveR = reservationRepository.save(reserve);




        // 예약 조회
        List<Reservation> findQuerydslR = reserveQueryRepository.findReserve(saveR.getReserveId());

        for (int i = 0; i < findQuerydslR.size(); i++) {
            System.out.println(findQuerydslR.get(i).getReserveId());
            System.out.println(findQuerydslR.get(i).getLicenceInfo());
            System.out.println(findQuerydslR.get(i).getUsers().getUserName());
            System.out.println(findQuerydslR.get(i).getUsers().getUserLevel());
            System.out.println(findQuerydslR.get(i).getCars().getName());
            System.out.println(findQuerydslR.get(i).getCars().getCarType());
        }
    }
}