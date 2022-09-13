package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "TB_RESERVATION")
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reserve_id", updatable = false)
    private Long reserveId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "carId", nullable = false)
    private Car cars;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date" ,nullable = false)
    private String endDate;

    @Column(name = "is_insuerance", nullable = false)
    private Boolean isInsuerance;

    @Column(name = "licence_info", nullable = false)
    private String licenceInfo;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;



    /* ===== 비지니스 로직 ===== */
    public static Reservation regist(String licenceInfo, Boolean isInsuerance, String startDate, String endDate, Car car, User user) {
        Reservation reservation1 = new Reservation();
        reservation1.setCars(car);
        reservation1.setUsers(user);
        reservation1.setLicenceInfo(licenceInfo);
        reservation1.setIsInsuerance(isInsuerance);
        reservation1.setStartDate(startDate);
        reservation1.setEndDate(endDate);

        return reservation1;
    }
}
