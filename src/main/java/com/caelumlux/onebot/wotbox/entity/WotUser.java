package com.caelumlux.onebot.wotbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author Caelumlux
 * @ClassName WotUser
 * @Description
 * @Date 2024/8/8 11:07
 */
@Data
@Entity
@Table(name = "WOT_USER")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class WotUser {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "USER_QQ")
    private Long userQq;

    @Column(name = "WOT_NAME")
    private String wotName;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;
}
