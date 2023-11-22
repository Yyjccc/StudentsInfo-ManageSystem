package Mode.enity;

import com.alibaba.druid.support.monitor.annotation.MTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String username;
    private String password;

    private int id;

}
