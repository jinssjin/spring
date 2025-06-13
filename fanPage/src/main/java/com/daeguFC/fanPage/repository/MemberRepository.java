package com.daeguFC.fanPage.repository;

import com.daeguFC.fanPage.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByName(String name);
}
