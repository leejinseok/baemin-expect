package com.expect.baemin.infra.db.expect.domain.member.address;

import com.expect.baemin.infra.db.expect.BaeminExpectDbBaseTimeEntity;
import com.expect.baemin.infra.db.expect.domain.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member_delivery_address")
public class MemberDeliveryAddressEntity extends BaeminExpectDbBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(columnDefinition = "varchar(100)")
    private String address;

    @Column(columnDefinition = "varchar(45)")
    private String addressName;

}
