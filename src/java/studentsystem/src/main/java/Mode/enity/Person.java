package Mode.enity;

import Mode.enity.Action.Change;
import Mode.enity.Action.Punishment;
import Mode.enity.Action.Reward;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
public class Person {
    @Autowired
    private Student studentInfo;
    @Autowired
    private Reward studentReward;
    @Autowired
    private Change studentChange;
    @Autowired
    private Punishment studentPunishment;

}
