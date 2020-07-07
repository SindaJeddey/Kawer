package kawer.tn.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import kawer.tn.security.AuthenticationRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig config;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig config) {
        this.authenticationManager = authenticationManager;
        this.config = config;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationRequest authenticationRequest = new ObjectMapper() //ObjectMapper reads the values from the
                    //input stream of the request and puts them in the UserAuthenticationRequest
                    .readValue(request.getInputStream(), AuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), //principal
                    authenticationRequest.getPassword()  //credentials
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);//the manager will make
            // sure that the username exists and if it does, it will check if the passwords match
            // If the passwords match, the request will be then authenticated

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will be invoked after the attemptAuthentication method is successful, otherwise it won't be
     * */

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName()) //subject : the username
                .claim("authorities",authResult.getAuthorities()) // claim:body: authorities
                .setIssuedAt(java.sql.Date.valueOf(LocalDate.now().plusDays(config.getTokenExpirationAfterDays())))
                //token expires after two weeks
                .signWith(config.getSecretKeyForSigning()) //signature
                .compact();

        response.addHeader(config.getAuthorizationHeader(),config.getTokenPrefix()+token);
        chain.doFilter(request,response);
    }
}

