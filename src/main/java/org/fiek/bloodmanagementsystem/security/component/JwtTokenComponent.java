package org.fiek.bloodmanagementsystem.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.repository.UserRepository;
import org.fiek.bloodmanagementsystem.security.model.JwtUser;
import org.fiek.bloodmanagementsystem.security.model.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenComponent {

    static final String CLAIM_KEY_NAME = "firstName";

    static final String CLAIM_KEY_SURNAME = "lastName";

    static final String CLAIM_KEY_IMAGE = "image";

    static final String CLAIM_KEY_ID = "id";

    static final String CLAIM_KEY_ROLE = "role";


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${image.url}")
    String imageUrl;


    @Autowired
    UserRepository userRepository;

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUserDetails user = (JwtUserDetails) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(String.valueOf(claims.get(CLAIM_KEY_ID)));
    }

    public String getUserRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get(CLAIM_KEY_ROLE).toString();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token) .getBody();
        return claimsResolver.apply(claims);
    }

    public JwtUser validate(String token) {
        JwtUser user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new JwtUser();
            user.setId(Long.valueOf((String)body.get("id")));
            user.setUsername((String)body.get("username"));
            user.setRole((String)body.get("role"));
        }catch(Exception e) {}
        return user;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_ID, user.getId());
        claims.put(CLAIM_KEY_NAME, user.getFirstName());
        claims.put(CLAIM_KEY_SURNAME, user.getLastName());

        if (user.getImage() != null) {
            claims.put(CLAIM_KEY_IMAGE, imageUrl+"/users/"+user.getImage());
        } else {
            claims.put(CLAIM_KEY_IMAGE, imageUrl+"users/unknown.jpg");
        }

        claims.put(CLAIM_KEY_ROLE, user.getRole().getName());


        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}

