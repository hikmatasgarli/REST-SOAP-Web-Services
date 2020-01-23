//Filter - client ve server arasinda olan bir layer (qat) dir
//Clientden gelen request birinci filtere girir ondan sonra gedir servere

/*
package hikmat.util;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/gt") //Hansi url-den gelenler girsin filtere,  "/*" butun nov requestler
public class EncodingFilter implements Filter {
    private static final String ENCODING = "utf-8";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        chain.doFilter(req, resp); //Serhedi acir servlete gonderir
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
*/
