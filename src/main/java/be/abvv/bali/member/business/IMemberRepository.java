package be.abvv.bali.member.business;

import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;

public interface IMemberRepository {
    MemberEntity getMember(String nom, String prenom);

    void setMemberPhoneNumber(MemberEntity member);
}
