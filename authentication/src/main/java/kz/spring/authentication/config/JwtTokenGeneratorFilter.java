package kz.spring.authentication.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.spring.authentication.model.Customer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.json.JSONObject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenGeneratorFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtTokenGeneratorFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/**", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {

            // 1. Get credentials from request
            Customer creds = new ObjectMapper().readValue(request.getInputStream(), Customer.class);

            // 2. Create auth object (contains credentials) which will be used by auth manager
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), creds.getPassword(), Collections.emptyList());
            System.out.println("creds.getEmail() " + creds.getUsername());
//            if(customer.getActivationCode() == null){

            return authenticationManager.authenticate(authToken);
//            }else{
//                System.out.println("Account unavailable");
//                return authenticationManager.authenticate(null);
//            }
            // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
        } catch (Exception e) {
            throw new RuntimeException(e + " or activation code is null");
        }
    }

    // Upon successful authentication, generate a token.
    // The 'auth' passed to successfulAuthentication() is the current authenticated user.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(auth.getName())
                // Convert to list of strings.
                // This is important because it affects the way we get them back in the Gateway.
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 60*60 * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, "QazMed".getBytes())
                .compact();
        // Add token to header
        response.addHeader("Authorization", "Bearer: " + token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
//        response.addHeader("Authorization", "Access-Control-Expose-Headers");
        response.addHeader("Authorization", "userName: " + auth.getName());
        System.out.println("Bearer: " + token);

//        try {
//            call_me(token, auth.getName());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

//    public static void call_me(String token, String email) throws Exception {
//        URL url = new URL("http://localhost:8762/auth-service/auth/");
//        Map params = new LinkedHashMap<>();
//        params.put("token", token);
//        params.put("email", email);
//        params.put("CODE", 1111);
//        params.put("message", "Hello Post Test success");
//        StringBuilder postData = new StringBuilder();
////        for (Map.Entry param : params.entrySet()) {
////            if (postData.length() != 0) postData.append('&');
////            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
////            postData.append('=');
////            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
////        }
//        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//        conn.setDoOutput(true);
//        conn.getOutputStream().write(postDataBytes);
//        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        StringBuilder sb = new StringBuilder();
//        for (int c; (c = in.read()) >= 0;)
//            sb.append((char)c);
//        String response = sb.toString();
//        System.out.println(response);
//        JSONObject myResponse = new JSONObject(response.toString());
//        JSONObject form_data = myResponse.getJSONObject("form");
//    }

}
