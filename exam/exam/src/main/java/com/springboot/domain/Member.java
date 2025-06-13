package com.springboot.domain;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data  // @Getter, Setter, ToString 등을 모두 포함
public class Member {
    
    @MemberId
    private String memberId;
    
    @Size(min=4, max=10, message="4자~10자 이내로 입력해 주세요")
    private String passwd;

}
