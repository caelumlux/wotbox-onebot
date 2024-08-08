package com.caelumlux.onebot.wotbox.repositoy;

import com.caelumlux.onebot.wotbox.entity.WotUser;
import org.springframework.stereotype.Repository;


@Repository
public interface WotUserRepository extends BaseRepository<WotUser, Long> {

    WotUser findByUserQq(Long id);

}
