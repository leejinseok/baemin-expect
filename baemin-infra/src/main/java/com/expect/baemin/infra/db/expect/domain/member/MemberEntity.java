package com.expect.baemin.infra.db.expect.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY)
    private List<MemberDeliveryAddressEntity> deliveryAddresses = new ArrayList<>();


}
