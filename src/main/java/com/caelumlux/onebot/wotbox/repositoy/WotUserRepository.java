package com.caelumlux.onebot.wotbox.repositoy;

import com.caelumlux.onebot.wotbox.entity.WotUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WotUserRepository extends BaseRepository<WotUser, Long> {

    WotUser findByUserQq(Long id);

    List<WotUser> findByWotName(String wotName);

}
