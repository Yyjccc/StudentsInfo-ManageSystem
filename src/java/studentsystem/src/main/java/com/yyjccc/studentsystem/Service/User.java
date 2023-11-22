package com.yyjccc.studentsystem.Service;


import Mode.enity.Users;
import Mode.Mapper.UsersMapper;
import com.yyjccc.studentsystem.Utils.Datautils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;


import java.io.IOException;
import java.security.Key;
import java.util.Date;
@Data
public class User {
    private String username;
    private String password;

    private Users loginUser;

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public  boolean login() throws IOException {

        if(checkUsername()){
            SqlSession sqlSession=Datautils.SqlStart();
            UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);

            Users users=usersMapper.getInfo(this.username);
            sqlSession.close();
        if(users.getPassword().equals(this.password)){
            this.loginUser=users;
            return true;
            }
        }
        return false;
    }

    //检查用户名是否在数据库中
    protected Boolean checkUsername() throws IOException {
        SqlSession sqlSession= Datautils.SqlStart();
        UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
         boolean result=usersMapper.checkExist(this.username);
         sqlSession.close();
         return result;
    }
    //生成jwt-cookie
    public static String createToken(Users users) {

        return Token.generateJwt(users.toString(),3600000,Token.secretKey);
    }

    public static boolean checkToken(String token){
        if(Token.validateJwt(token,Token.secretKey)){
            return true;
        }

        return false;
    }


}

 class  Token{
    public static Key secretKey= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //生成jwt
    public static String generateJwt(String subject, long expirationTime, Key secretKey) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //校验jwt


    public static boolean validateJwt(String jwt, Key secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();

            // 在这里可以根据需要检查其他声明或条件

            return true;
        } catch (Exception e) {
            // 处理异常，例如JWT过期或签名不匹配
            return false;
        }
    }

}


