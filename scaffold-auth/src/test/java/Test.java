import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hsh
 * @create 2019-11-17 18:25
 **/
public class Test {

    @org.junit.Test
    public void getPasswrod(){
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }
}
