package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ReservationDto {

    @Id
    private long reserveId;

    private long userId;
    private String userName;
    private UserLevel userLevel;

    private long carId;
    private String carName;
    private CarType carType;
    private int carUseCnt;

    private String startDate;
    private String endDate;
    private Boolean isInsuerance;
    private String licenceInfo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;


    public ReservationDto(long reserveId, long userId, String userName, UserLevel userLevel, long carId, String carName, CarType carType, int carUseCnt, String startDate, String endDate, Boolean isInsuerance, String licenceInfo, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.reserveId = reserveId;
        this.userId = userId;
        this.userName = userName;
        this.userLevel = userLevel;
        this.carId = carId;
        this.carName = carName;
        this.carType = carType;
        this.carUseCnt = carUseCnt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isInsuerance = isInsuerance;
        this.licenceInfo = licenceInfo;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
