package loginBoard.board2.Service;

import loginBoard.board2.Repository.MemberRepository;
import loginBoard.board2.dto.MemberDTO;
import loginBoard.board2.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member register(MemberDTO dto) {
        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(dto.getPassword());
        return memberRepository.save(member);
    }

    public Member login(MemberDTO dto) {
        return memberRepository.findByUsername(dto.getUsername())
                .filter(m -> m.getPassword().equals(dto.getPassword()))
                .orElse(null);
    }
}
