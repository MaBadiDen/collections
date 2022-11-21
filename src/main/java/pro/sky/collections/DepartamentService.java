package pro.sky.collections;

import java.util.List;

public interface DepartamentService {

    Employee getMaxSalary(int departamentId);

    Employee getMinSalary(int departamentId);
    List<Employee> writeEmployees();

     List<Employee> getPersonsByDepartament(int departamentId);

}
