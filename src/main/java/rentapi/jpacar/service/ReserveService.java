package rentapi.jpacar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentapi.jpacar.domain.Car;
import rentapi.jpacar.domain.Reservation;
import rentapi.jpacar.domain.ReservationDto;
import rentapi.jpacar.domain.User;
import rentapi.jpacar.repository.CarRepository;
import rentapi.jpacar.repository.ReservationRepository;
import rentapi.jpacar.repository.UserRepository;
import rentapi.jpacar.repository.querydsl.ReserveQueryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveService {

    private final ReservationRepository reservationRepository;
    private final ReserveQueryRepository reserveQueryRepository;

    private final UserRepository userRepository;
    private final CarRepository carRepository;


    @Transactional
    public long regist(ObjectNode objectNode) throws JsonProcessingException {
        long carId = objectNode.get("carId").asLong();
        long userId = objectNode.get("userId").asLong();
        String licenceInfo = objectNode.get("licenceInfo").asText();
        Boolean isInsuerance = objectNode.get("isInsuerance").asBoolean();
        String startDate = objectNode.get("startDate").asText();
        String endDate = objectNode.get("endDate").asText();

        User findU = userRepository.findById(userId).get();
        Car findC = carRepository.findById(carId).get();

        Reservation saveR = Reservation.regist(licenceInfo, isInsuerance, startDate, endDate, findC, findU);
        Reservation result = reservationRepository.save(saveR);

        return result.getReserveId();
    }

    public List<Reservation> findReserve(long reserveId) {
        return reserveQueryRepository.findReserve(reserveId);
    }

    public List<ReservationDto> findReserveDto(long reserveId) {
        return reserveQueryRepository.findReserveDto(reserveId);
    }

    public List<Reservation> findAllReserve() {
        return reserveQueryRepository.findReserve(0L);
    }

    public List<ReservationDto> findAllReserveDto() {
        return reserveQueryRepository.findReserveDto(0L);
    }
}
