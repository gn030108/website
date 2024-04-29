package backend.musinsa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityUtil {

    public static String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication name => "+authentication.getName());
        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("No authentication information");
        }
        return authentication.getName();
    }

}
