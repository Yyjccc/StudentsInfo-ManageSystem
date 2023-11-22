package Mode.Mapper;

import Mode.enity.Student;

import java.util.List;

public interface StudentsMapper {
    List<Student> getAll();
    List<Student> query(String type,String value);

    Boolean update(long StudentId,String type,String value);

    Boolean insert(Student student);

    Boolean delete(long StudentId);
}
