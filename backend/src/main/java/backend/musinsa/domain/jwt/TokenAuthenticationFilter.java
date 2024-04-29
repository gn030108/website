package backend.musinsa.domain.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * 클라이언트 요청시 JWT 인증을 위한 필터. UsernamePasswordAuthenticationFilter 이전에 실행한다.
 * 클라이언트에서 들어오는 요청에서 JWT 토큰을 처리하고,
 * 유효한 토큰인 경우 해당 토큰의 인증정보(Authentication)를 SecurityContext에 저장하여 인증된 요청을 처리할수 있도록 한다.
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    /**
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                     to for further processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //1. Request Header에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);
        //2. validateToken으로 토큰 유효성 검사
        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장 --> 요청을 처리하는 동안 인증정보 유지
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }
    /**
     * Request Header에서 토큰 정보 추출 (Bearer 로 시작하는 토큰을 추출하여 반환)
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
