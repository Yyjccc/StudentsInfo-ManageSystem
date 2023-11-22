package Mode.enity.Action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Change {
    //记录号
    private long id;
    //学号
    private long StudentId;
    //变更代码
    private int changeId;
    //记录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date recTime;
    //描述
    private String description;
}
