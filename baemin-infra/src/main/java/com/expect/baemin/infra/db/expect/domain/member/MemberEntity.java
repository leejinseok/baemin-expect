package com.expect.baemin.infra.db.expect.domain.member;

import com.expect.baemin.infra.db.expect.BaeminExpectDbBaseTimeEntity;
import com.expect.baemin.infra.db.expect.domain.member.address.MemberDeliveryAddressEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
public class MemberEntity extends BaeminExpectDbBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberDeliveryAddressEntity> deliveryAddresses = new ArrayList<>();

    public static MemberEntity create(String nickname) {
        MemberEntity member = new MemberEntity();
        member.nickname = nickname;
        return member;
    }
}
