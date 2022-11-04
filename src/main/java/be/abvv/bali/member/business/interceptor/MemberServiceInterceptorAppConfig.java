package be.abvv.bali.member.business.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class MemberServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    MemberServiceInterceptor baliServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baliServiceInterceptor);
    }
}
