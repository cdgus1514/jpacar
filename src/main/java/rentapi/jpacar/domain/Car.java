package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter @Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_CAR")
public class Car {

    @Id @GeneratedValue
    @Column(name = "car_id", updatable = false)
    private Long carId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "year", nullable = false)
    private String year;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accidentId")
    private Accident accidents;

    @Column(name = "useCount")
    @ColumnDefault("0")
    private int useCount;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;


    /* ===== 비지니스 로직 ===== */
    public static Car insert(Car car) {
        Car car1 = new Car();
        car1.setName(car.getName());
        car1.setYear(car.getYear());

        switch (car.carType) {
            case COUPE:
                car1.setCarType(CarType.COUPE);
                break;
            case SEDAN:
                car1.setCarType(CarType.SEDAN);
                break;
            case SPORTS:
                car1.setCarType(CarType.SPORTS);
                break;
            case SUV:
                car1.setCarType(CarType.SUV);
                break;
            case WAGON:
                car1.setCarType(CarType.WAGON);
                break;
            case HATCHBACK:
                car1.setCarType(CarType.HATCHBACK);
                break;
            case CONVERTIBLE:
                car1.setCarType(CarType.CONVERTIBLE);
                break;
            case CAMPING:
                car1.setCarType(CarType.CAMPING);
                break;
        }

        return car1;
    }
}
