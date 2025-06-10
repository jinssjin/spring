package loginBoard.board2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    //

    @ManyToOne  // 연관관계 : 많은 작성글과 한 명의 작성자
    @JoinColumn(name = "member_id") // 두 변수를 JOIN
    private Member writer;
}
