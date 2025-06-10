package member.member1.service;

import member.member1.dto.MemberDto;
import member.member1.entity.Member;
import member.member1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // dto : 데이터 전달 , entity : DB연결
    @Autowired
    private MemberRepository memberRepository;  // form → Dto → entity → save 저장할때, DB → entity → Dto → form 불러올 때

    public void create(MemberDto dto) {
        Member member = new Member(dto);
        memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();

    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public void update(Long id, MemberDto dto) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {  // isPresent : optionalMember가 존재하면(null이 아니면)
            Member member = optionalMember.get();  // Optional을 get으로 꺼내올 수 있음
            member.updateFromDto(dto);  // memberDto에 생성자
            memberRepository.save(member);  //
        }

    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
