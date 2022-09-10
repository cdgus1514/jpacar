package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Getter @Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_REPAIRE")
public class Repaire {

    @Id @GeneratedValue
    @Column(name = "repaired_id", updatable = false)
    private Long repairedId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accidentId")
    private Accident accidents;

    @Column(name = "repaired_cost", nullable = false)
    private Long repairedCost;

    @Column(name = "repaired_start", nullable = false)
    private String reapiredStart;

    @Column(name = "repaired_end", nullable = false)
    private String repairedEnd;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;
}
