package rentapi.jpacar.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.ReservationDto;

import java.util.List;

import static rentapi.jpacar.domain.QReservation.reservation;

@Repository
@RequiredArgsConstructor
public class ReserveQueryRepository {

    private final JPAQueryFactory factory;

    public List<ReservationDto> findReserve(long reservId) {

        BooleanBuilder builder = new BooleanBuilder();

        if(reservId != 0L) {
            builder.and(reservation.reserveId.eq(reservId));
        }

        JPAQuery<ReservationDto> result = factory.select(Projections.constructor(ReservationDto.class,
                reservation.reserveId,
                reservation.users.userId, reservation.users.userName, reservation.users.userLevel,
                reservation.cars.carId, reservation.cars.name, reservation.cars.carType, reservation.cars.useCount,
                reservation.startDate, reservation.endDate, reservation.isInsuerance, reservation.licenceInfo,
                reservation.createdAt, reservation.modifiedAt))
                .from(reservation)
                .join(reservation.users)
                .join(reservation.cars)
                .where(builder);


        List<ReservationDto> results = result.fetch();

        return results;
    }
}
