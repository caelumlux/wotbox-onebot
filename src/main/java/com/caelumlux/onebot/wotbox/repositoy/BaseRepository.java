package com.caelumlux.onebot.wotbox.repositoy;


import com.caelumlux.onebot.wotbox.config.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

/**
 * @author Caelumlux
 * @ClassName BaseRepostory
 * @Description
 * @Date 2023/6/28 16:59
 */
@BaseDao
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    @Query(value = "select current_timestamp() ", nativeQuery = true)
    Timestamp currentTimestamp();

}
