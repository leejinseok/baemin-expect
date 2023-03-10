package com.expect.baemin.api;

import com.expect.baemin.infra.db.expect.domain.member.MemberEntity;
import com.expect.baemin.infra.db.expect.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BaeminApiCommandLineRunner implements CommandLineRunner {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        MemberEntity member1 = MemberEntity.create("둘리");
        memberRepository.save(member1);
    }
}
