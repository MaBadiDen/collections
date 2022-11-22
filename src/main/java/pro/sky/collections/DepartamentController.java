package pro.sky.collections;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @GetMapping("/min")
    public Employee getMin(@RequestParam("departamentId") int departamentId) {
        return departamentService.getMinSalary(departamentId);
    }
    @GetMapping("/max")
    public Employee getMax(@RequestParam("departamentId") int departamentId) {
        return departamentService.getMaxSalary(departamentId);

    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> writeAllEmployees() {
        return departamentService.writeEmployees();
    }
    @GetMapping(path = "/all", params = {"departamentId"})
    public List<Employee> writeDepEmployees(@RequestParam(value = "departamentId") Integer departamentId) {
        return departamentService.getPersonsByDepartament(departamentId);
    }

}
