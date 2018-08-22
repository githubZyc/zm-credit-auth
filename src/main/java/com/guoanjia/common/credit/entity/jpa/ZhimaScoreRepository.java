package com.guoanjia.common.credit.entity.jpa;

import com.guoanjia.common.credit.entity.ZhimaScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by gaozhou on 2018/06/26/.
 */
@Repository
public interface ZhimaScoreRepository extends JpaRepository<ZhimaScoreEntity,String> {

    @Query(value = "SELECT valid_flag FROM zhima_score WHERE id =?1",nativeQuery = true)
    String getvalidFlag(String userId);

    @Query(value = "SELECT zhimaScore FROM  zhima_score WHERE id=?1",nativeQuery = true)
    int getUserZhimaScore(String userId);




}
