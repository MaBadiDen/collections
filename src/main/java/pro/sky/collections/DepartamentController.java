package pro.sky.collections;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departament")
public class DepartamentController {
    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @GetMapping("/min")
    public String getMix(@RequestParam("departamentId") int departamentId) {
        try {
            return departamentService.getMinSalary(departamentId).toString();
        } catch (RuntimeException e) {
            return "Такого депортамента не существует";
        }
    }
    @GetMapping("/max")
    public String getMax(@RequestParam("departamentId") int departamentId) {
        try {
            return departamentService.getMaxSalary(departamentId).toString();
        } catch (RuntimeException e) {
            return "Такого депортамента не существует";
        }
    }
    @GetMapping("/all")
    public List<Employee> writeAllEmployees() {
        return departamentService.writeEmployees();
    }
    @GetMapping(path = "/all", params = {"departamentId"})
    public List<Employee> writeDepEmployees(@RequestParam(value = "departamentId") Integer departamentId) {
        return departamentService.getPersonsByDepartament(departamentId);
    }

}
