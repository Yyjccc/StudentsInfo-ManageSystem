package Mode.Mapper;

import Mode.enity.Code;

import java.util.List;

public interface CodeMapper {
    List<Code> changeCodes();
    List<Code> rewardCode();

    List<Code> punishmentCode();

    List<Code> classCode();

    List<Code> departmentCode();
}
