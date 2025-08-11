package org.example.newscheduleproject.common.filter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/signup", "/login", "/logout"};
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            // 로그인하지 않은 사용자인 경우
            if (session == null || session.getAttribute("LOGIN_DIRECTOR") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }
        chain.doFilter(request, response);
    }
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
