package Mode.enity.Action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Punishment {
    //记录号
    private long id;
    //学号
    private long StudentId;
    //级别
    private int levels;
    //是否生效
    private boolean enable;
    //记录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recTime;
    //描述
    private String description;

}
