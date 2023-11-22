package Mode.enity;

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
public class Student {
    //学号
    private long StudentId;
    //姓名
    private String name;
    //性别
    private String sex;
    //班级编号
    private String classId;
    //学院编号
    private String department;
    //生日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    //籍贯
    private String  nativePlace;

}
