package be.abvv.bali.member.business.service;

import be.abvv.bali.member.persistence.rpg.domain.BaliFZZEntity;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.springframework.stereotype.Service;

@Service
public interface IMemberService {
    String SCHEMA_NAME_KEY = "spring.db2.datasource.default_schema_name";
    public MemberEntity getMember(String firstName, String lastName);
    public void updatephonenumber(String id, String phone);
}
