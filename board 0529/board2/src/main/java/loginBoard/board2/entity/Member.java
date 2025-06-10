package loginBoard.board2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    
    // 회원이 작성한 글
    @OneToMany(mappedBy = "writer")  // user는 한 명, board는 여러 개
    private List<Board> boards = new ArrayList<>();

    // user는 한명이고, 여러 보드를 가질 수 있다.
    // board는 하나의 User인(writer)를 가집니다.
    // board가 연관관계 주인이고, user는 mappedBy로 관계만 나타냄 
}
