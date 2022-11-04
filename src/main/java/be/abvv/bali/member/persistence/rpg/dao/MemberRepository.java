package be.abvv.bali.member.persistence.rpg.dao;

import be.abvv.bali.member.business.IMemberRepository;
import be.abvv.bali.member.persistence.rpg.domain.BaliFZZEntity;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberRepository implements IMemberRepository {

    @Autowired
    BaliFZZDAO baliFZZDAO;

    @Override
    public MemberEntity getMember(String firstname, String lastname) {
        MemberEntity member= new MemberEntity();
        List<BaliFZZEntity> baliFZZList = baliFZZDAO.findGsmByFirstNameAndLastName(firstname,lastname);
        if(!baliFZZList.isEmpty()) {
            BaliFZZEntity baliFzz = baliFZZList.get(0);
            if(!StringUtils.isBlank(baliFzz.getGsmNumber()))
                member.setNom(baliFzz.getLastName());
                member.setPrenom(baliFzz.getFirstName());
                member.setTelephone(baliFzz.getGsmNumber());
                member.setId(baliFzz.getInternalLidNumber());
        }
        return member;
    }

    @Override
    public void setMemberPhoneNumber(MemberEntity member) {
        baliFZZDAO.updatePhone(member.getId(), member.getTelephone());
    }
}
