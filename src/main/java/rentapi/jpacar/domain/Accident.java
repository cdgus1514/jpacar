package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;

@Getter @Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_ACCIDENT")
public class Accident {

    @Id @GeneratedValue
    @Column(name = "accident_id")
    private Long accidentId;

    @Column(name = "accident_type")
    @Enumerated(EnumType.STRING)
    private AccidentType accidentType;

    @Column(name = "accident_detail")
    private TextArea accidentDetail;

    @Column(name = "is_repaire")
    private boolean isRepaire;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;
}
