package backend.musinsa.repository;

import backend.musinsa.domain.jwt.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,Long> {

    Boolean existsByInvalidRefreshToken(String refreshToken);

}
