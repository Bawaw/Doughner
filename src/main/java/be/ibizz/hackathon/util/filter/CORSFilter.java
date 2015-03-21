package be.ibizz.hackathon.util.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Deze filter zorgt er voor dat Cross-origin resource sharing (CORS) toegelaten wordt.
 * Zonder dit zou je geen JavaScript XHR requests kunnen doen naar een server dat niet op hetzelfde domein staat als
 * waar de JavaScript wordt uitgevoerd.
 *
 * Bijvoorbeeld: http://localhost (JavaScript) naar http://hackathonapp.eu-gb.mybluemix.net/hello (Java, op de server)
 */

@Component
public class CORSFilter implements Filter {

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    chain.doFilter(req, res);
  }

  public void init(FilterConfig filterConfig) {}

  public void destroy() {}

}