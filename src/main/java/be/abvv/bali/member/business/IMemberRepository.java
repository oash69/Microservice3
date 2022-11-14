package be.abvv.bali.member.business;

import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;

public interface IMemberRepository {
    MemberEntity getMember(String nom, String prenom);

    MemberEntity getMember(String id);

    void setMemberPhoneNumber(MemberEntity member);
}
