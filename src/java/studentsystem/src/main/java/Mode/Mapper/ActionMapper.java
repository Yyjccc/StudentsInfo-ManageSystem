package Mode.Mapper;

import Mode.enity.Action.Change;
import Mode.enity.Action.Punishment;
import Mode.enity.Action.Reward;
import Mode.enity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActionMapper {
    List<Reward> rewardGetAll();

    List<Change> changeGetAll();
    List<Punishment> punishGetAll();


    List<Change> changequery(String type,String value);

    List<Punishment> punishquery(String type,String value);
    List<Punishment> rewardquery(String type,String value);

    Boolean update(String ActionType,int id,String type,String value);
    @Transactional
    Boolean changeinsert(Change change);
    @Transactional
    Boolean rewardinsert(Reward reward);
    @Transactional
    Boolean punishinsert(Punishment punishment);

    Boolean delete(String ActionType,int id);

}
