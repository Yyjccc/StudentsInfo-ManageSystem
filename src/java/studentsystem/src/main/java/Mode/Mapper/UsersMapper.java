package Mode.Mapper;

import Mode.enity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    boolean checkExist(String username);
    Users getInfo(String username);

}
