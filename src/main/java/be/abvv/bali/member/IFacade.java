package be.abvv.bali.member;

import be.abvv.bali.member.persistence.rpg.domain.MemberEntity;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

public interface IFacade {
    MemberEntity getMember(@RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "lastName") String lastName, HttpServletRequest request);

    static HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

}
