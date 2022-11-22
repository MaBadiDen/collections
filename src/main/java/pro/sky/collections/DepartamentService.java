package pro.sky.collections;

import java.util.List;
import java.util.Map;

public interface DepartamentService {

    Employee getMaxSalary(int departamentId);

    Employee getMinSalary(int departamentId);
    Map<Integer, List<Employee>> writeEmployees();

    List<Employee> getPersonsByDepartament(int departamentId);

}
