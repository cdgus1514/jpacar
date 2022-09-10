package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "year")
    private String year;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accidentId")
    private Accident accidents;

    @Column(name = "useCount")
    private int useCount;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;
}
