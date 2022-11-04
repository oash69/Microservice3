package be.abvv.bali.member;

import be.abvv.bali.member.business.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/v1")
@RestController
public class Facade implements IFacade{
    @Bean
    public RestTemplate restTemplateFacade() {
        return new RestTemplate();
    }

    @Autowired
    MemberService memberService;

    @GetMapping("/memberFacade")
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

    }
    @GetMapping("/memberPhoneChange")
    public String setMember(@RequestParam(value = "tel") String tel, @RequestParam(value = "id") String id){
        memberService.updatephonenumber(id, tel);
        return "Change phone success! " + tel + " for userId " + id;
    }

}
