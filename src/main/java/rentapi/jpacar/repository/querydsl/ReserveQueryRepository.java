package rentapi.jpacar.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rentapi.jpacar.domain.Reservation;

import java.util.List;

import static rentapi.jpacar.domain.QReservation.reservation;

@Repository
@RequiredArgsConstructor
public class ReserveQueryRepository {

    private final JPAQueryFactory factory;

    public List<Reservation> findReserve(Long reservId) {

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(reservation.reserveId.eq(reservId));

        JPAQuery<Reservation> result = factory.select(reservation).from(reservation)
                .join(reservation.users)
                .join(reservation.cars)
                .where(builder);


        List<Reservation> results = result.fetch();

        return results;
    }
}
