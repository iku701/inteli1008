package edu.du.sb1008;

import edu.du.sb1008.dao.ISimpleBbsDao;
import edu.du.sb1008.dto.SimpleBbsDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

    @Autowired
    ISimpleBbsDao dao;

    @GetMapping("/")
    public String root() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", dao.listDao());
        return "list";
    }

    @GetMapping("/view")
    public String view(HttpServletRequest request, Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(sId));
        return "view";
    }

    @GetMapping("/writeForm")
    public String writeForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute SimpleBbsDto dto, Model model) {
        int result = dao.writeDao(dto.getWriter(), dto.getTitle(), dto.getContent());

        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        dao.deleteDao(request.getParameter("id"));
        return "redirect:/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(HttpServletRequest request, Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(sId));
        return "updateForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute SimpleBbsDto simpleBbsDto, Model model) {
        int result = dao.updateDao(simpleBbsDto);

        return "redirect:/list";
    }

    @GetMapping("/resetId")
    public String resetId() {
        dao.resetIdDao();
        return "redirect:/list";
    }

}
