package umc.spring.repository.store;

import umc.spring.domain.Store;

import java.util.List;

/**
 * QueryDSL 기반의 커스텀 쿼리 메서드 모음.
 */
public interface StoreRepositoryCustom {

    /**
     * name(정확 일치) && score 이상 조건으로 가게 목록을 조회한다.
     *
     * @param name   가게명( null 이나 blank 면 조건에서 제외 )
     * @param score  최소 평점( null 이면 조건에서 제외 )
     * @return 조건에 맞는 Store 리스트
     */
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
