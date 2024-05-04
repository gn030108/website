package backend.musinsa.repository;

import backend.musinsa.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    boolean existsByMemberId(String memberId);


}
