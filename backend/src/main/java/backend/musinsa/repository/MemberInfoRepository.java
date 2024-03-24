package backend.musinsa.repository;

import backend.musinsa.domain.member.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo,Long> {
}
