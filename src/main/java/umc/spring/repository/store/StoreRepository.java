package umc.spring.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Store;

/**
 * JPA 기본 CRUD + 커스텀 QueryDSL 메서드를 모두 사용하기 위해
 * StoreRepositoryCustom 을 상속한다.
 */
public interface StoreRepository
        extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
