package kawer.tn.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class JwtTokenVerifier extends OncePerRequestFilter {
    private final JwtConfig config;

    public JwtTokenVerifier(JwtConfig config) {
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(config.getAuthorizationHeader());
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(config.getTokenPrefix())){
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorizationHeader.replace(config.getTokenPrefix(),"");

        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(config.getSecretKeyForSigning())
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<Map<String,String>> authorities = (List<Map<String,String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());


            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    grantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication); //This means that the client that sent
            //the token is now authenticated
        }
        catch (JwtException e){
            throw new IllegalStateException(String.format("Token %s cannot be trusted",token));
        }

        filterChain.doFilter(request,response); // to pass the request to the next filter

    }
}
