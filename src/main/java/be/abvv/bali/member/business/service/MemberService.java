package be.abvv.bali.member.business.service;

import be.abvv.bali.member.persistence.rpg.Db2MultiTenantResolver;
import be.abvv.bali.member.persistence.rpg.dao.MemberRepository;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Profile({"local", "!xxlocal"})
public class MemberService implements IMemberService{
    @Autowired
    private Environment env;

    @Autowired
    private MemberRepository memberDao;

    private String schemaPrefix;
    private String nationalSchemaPrefix;
    private boolean isProdEnv = false;


    /**
     * DB2 Service Implementation constructor.
     *
     * @param env environment file
     */
    @Autowired
    public MemberService(Environment env) {
        this.env = env;
        this.schemaPrefix = env.getProperty(SCHEMA_NAME_KEY,
                String.class,
                Db2MultiTenantResolver.DEFAULT_SCHEMA_PREFIX);
        this.nationalSchemaPrefix = env.getProperty("spring.db2.national.datasource.default_schema_name",
                String.class);


        this.isProdEnv = Arrays.asList(this.env.getActiveProfiles()).contains("prod");
    }

    public MemberEntity getMember(String firstName, String lastName) {
/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + Db2MultiTenantResolver.DEFAULT_TENANT_IDENTIFIER);*/
/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + "R");*/
        MemberEntity member = memberDao.getMember(firstName,lastName);
        if(member !=null) {
            return member;
        }
        return null;
    }

    public MemberEntity getMember(String id) {
        MemberEntity member = memberDao.getMember(id);
        if(member !=null) {
            return member;
        }
        return null;
    }

    public void updatephonenumber(String id, String phone) {

/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + "R");*/
        MemberEntity member = new MemberEntity();
        member.setId(id);
        member.setTelephone(phone);
        memberDao.setMemberPhoneNumber(member);
    }

    public void updateMembre(String id, MemberEntity member) {

/*        Db2MultiTenantResolver.setTenant(
                this.schemaPrefix
                        + "R");*/
        memberDao.setMemberPhoneNumber(member);
    }

}
