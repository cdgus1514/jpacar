package rentapi.jpacar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_USER")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;


    /* ===== 비지니스 로직 ===== */
    public static User Regist(User user) {

        User user1 = new User();
        user1.setUserName(user.userName);

        switch (user.userLevel) {
            case VVIP:
                user1.setUserLevel(UserLevel.VVIP);
                break;
            case VIP:
                user1.setUserLevel(UserLevel.VIP);
                break;
            case DIAMOND:
                user1.setUserLevel(UserLevel.DIAMOND);
                break;
            case PLATINUM:
                user1.setUserLevel(UserLevel.PLATINUM);
                break;
            case GOLD:
                user1.setUserLevel(UserLevel.GOLD);
                break;
            case SILVER:
                user1.setUserLevel(UserLevel.SILVER);
                break;
            case BRONZE:
                user1.setUserLevel(UserLevel.BRONZE);
                break;
            case ROOKIE:
                user1.setUserLevel(UserLevel.ROOKIE);
                break;
        }

        return user1;
    }
}
