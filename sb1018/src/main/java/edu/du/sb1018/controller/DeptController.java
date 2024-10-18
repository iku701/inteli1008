package edu.du.sb1018.controller;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/depts")
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public String listDepts(Model model) {
        model.addAttribute("depts", deptService.findAll());
        return "dept/list";
    }

    @GetMapping("/add")
    public String addDeptForm(Model model) {
        model.addAttribute("dept", new Dept());
        return "dept/add";
    }

    @PostMapping
    public String addDept(@ModelAttribute Dept dept) {
        deptService.save(dept);
        return "redirect:/depts";
    }

    @GetMapping("/edit/{deptno}")
    public String editDeptForm(@PathVariable Integer deptno, Model model) {
        Dept dept = deptService.findById(deptno);
        if (dept != null) {
            model.addAttribute("dept", dept);
            return "dept/edit";
        }
        return "redirect:/depts";
    }

    @PostMapping("/edit")
    public String editDept(@ModelAttribute Dept dept) {
        deptService.save(dept);
        return "redirect:/depts";
    }

    @GetMapping("/delete/{deptno}")
    public String deleteDept(@PathVariable Integer deptno) {
        deptService.deleteById(deptno);
        return "redirect:/depts";
    }
}
