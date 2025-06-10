package board1.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter  // lombok으로 Getter 만들어주기
@Setter  // lombok으로 Setter 만들어주기
@AllArgsConstructor  // lombok으로 전체 생성자 만들어주기
@NoArgsConstructor  // lombok으로 기본 생성자 만들어주기
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;

}
