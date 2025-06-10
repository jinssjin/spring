package member.member1.repository;

import member.member1.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
// CrudRepository를 상속받으려고 하기 때문에 Repository 어노테이션이 필요 없음

public interface MemberRepository extends CrudRepository<Member,Long> {
    
    // Iterable타입을 List타입으로 오버라이딩
    List<Member> findAll();
}
