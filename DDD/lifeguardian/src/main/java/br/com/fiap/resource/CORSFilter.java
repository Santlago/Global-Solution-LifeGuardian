package br.com.fiap.resource;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  
Access-Control-Allow-Origin : 
    especifica os domínios autorizados para fazer solicitações entre domínios.
     Use “*” se não houver restrições.
Access-Control-Allow-Credentials : 
     especifica se as solicitações entre domínios podem ter credenciais de autorização ou não.
Access-Control-Expose-Headers : 
     indica quais cabeçalhos são seguros para exposição.
Access-Control-Max-Age : 
     indica por quanto tempo os resultados de uma solicitação de simulação podem ser armazenados em cache.
Access-Control-Allow-Methods : 
     indica os métodos permitidos no acesso ao recurso.
Access-Control-Allow-Headers : 
     indica quais nomes de campos de cabeçalho podem ser usados ​​durante a solicitação real.
 * 
 * */
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

        // Authorize (allow) all domains to consume the content
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type"); // Add this line

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}

