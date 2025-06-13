package com.daeguFC.fanPage.repository;

import com.daeguFC.fanPage.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
}
