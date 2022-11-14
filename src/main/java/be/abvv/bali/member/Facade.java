package be.abvv.bali.member;

import be.abvv.bali.member.business.service.MemberService;
import be.abvv.bali.member.persistence.rpg.Db2MultiTenantResolver;
import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/v1")
@RestController
@Slf4j
public class Facade implements IFacade{

/*    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

    @Autowired
    MemberService memberService;

/*    @GetMapping("/memberFacade")
    public String getMember(@RequestParam(value = "firstName") String firstName,
                            @RequestParam(value = "lastName") String lastName,
                            HttpServletRequest request) {
        String html = "Hi " + firstName+ " " +lastName + " ! Your phone number is: " + memberService.getMember(firstName,lastName).getTelephone();
        html += "<form method='GET' action='/bali/services/v1/memberPhoneChange'>";
        html += "<label path='name'>Modify phone number </form:label> <input type='text' name='tel' maxlength='14' pattern= '([0-9]|\\/)*'/> </br>";
        html += "<input type='hidden' name ='id' value='" + memberService.getMember(firstName,lastName).getId()+"'>";
        html += "<input type='submit'>";
        html += "</br> </form>";

        return html;

    }*/

    @GetMapping("/memberFacade")
    public MemberEntity getMember(@RequestParam(value = "firstName") String firstName,
                                  @RequestParam(value = "lastName") String lastName,
                                  HttpServletRequest request) {

        log.debug("getMember ");
        System.out.println("getMember");
        return memberService.getMember(firstName,lastName);

    }
    @GetMapping("/memberPhoneChange")
    public MemberEntity setMember(@RequestParam(value = "tel") String tel, @RequestParam(value = "id") String id){
        log.debug(" memberPhoneChange ");
        memberService.updatephonenumber(id, tel);
        return memberService.getMember(id);
    }

    @PutMapping("/putChange")
    public MemberEntity updateMember(@RequestParam(value = "tel") String tel, @RequestParam(value = "id") String id){
        log.debug(" memberPhoneChange ");
        memberService.updatephonenumber(id, tel);
        return memberService.getMember(id);
    }

}
