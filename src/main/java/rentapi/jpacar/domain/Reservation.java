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
@Table(name = "TB_RESERVATION")
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reserve_id")
    private Long reserveId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User users;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "carId")
    private Car cars;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "is_insuerance")
    private Boolean isInsuerance;

    @Column(name = "licence_info")
    private String licenceInfo;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;
}
