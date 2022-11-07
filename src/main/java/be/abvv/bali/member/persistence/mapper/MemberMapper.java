package be.abvv.bali.member.persistence.mapper;

import be.abvv.bali.member.persistence.rpg.domain.BaliFZZEntity;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberMapper {

    public MemberEntity toMember(BaliFZZEntity fzz) {
        MemberEntity member = new MemberEntity();
        if (null != fzz) {
            if (fzz.getInternalLidNumber()!=null){
                member.setId(fzz.getInternalLidNumber());
            }
            if (fzz.getLastName()!=null) {
                member.setNom(fzz.getLastName());
            }
            if (fzz.getFirstName()!=null) {
                member.setPrenom(fzz.getFirstName());
            }
            if (!StringUtils.isBlank(fzz.getGsmNumber())) {
                member.setTelephone(fzz.getGsmNumber());
            }
        }
        return member;
    }
}
