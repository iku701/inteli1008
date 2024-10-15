package edu.du.sb1015.controller;

import edu.du.sb1015.dto.DataObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HelloController {

//    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("msg", "please write name...");
        return "index";
    }

    @PostMapping("/")
    public String helloPost(@RequestParam("text1") String name, Model model) {
        model.addAttribute("value", name);
        model.addAttribute("msg", "Your name is " + name);
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "message 1<hr/>message 2<br/>message 3");
        DataObject obj = new DataObject(123, "lee", "lee@flower");
        model.addAttribute("object", obj);
        return "index";
    }

    @GetMapping("/id/{id}")
    public String index(@PathVariable int id, Model mav) {
        mav.addAttribute("id", id);
        mav.addAttribute("check", id >= 0);
        mav.addAttribute("trueVal", "POSITIVE!");
        mav.addAttribute("falseVal", "NEGATIVE!");
        DataObject obj = new DataObject(123, "lee", "lee@flower");
        mav.addAttribute("object", obj);
        return "index";
    }

    @RequestMapping("/month/{month}")
    public ModelAndView index(@PathVariable int month, ModelAndView mav) {
        mav.setViewName("index2");
        int m = Math.abs(month) % 12;
        m = m == 0 ? 12 : m;
        mav.addObject("month", m);
        mav.addObject("check", Math.floor(m / 3));
        return mav;
    }

    @RequestMapping("/index3")
    public ModelAndView index3(ModelAndView mav) {
        mav.setViewName("index3");
        ArrayList<String[]> data = new ArrayList<String[]>();
        data.add(new String[]{"park", "park@tammm.com", "123-4564-7898"});
        data.add(new String[]{"lee", "lkee@tammm.com", "123-4564-7898"});
        data.add(new String[]{"chai", "chai@tammm.com", "123-4564-7898"});
        mav.addObject("data", data);
        return mav;
    }

    @RequestMapping("/index4")
    public ModelAndView index4(ModelAndView mav) {
        mav.setViewName("index4");
        ArrayList<String[]> data = new ArrayList<String[]>();
        data.add(new String[]{"park", "park@tammm.com", "123-4564-7898"});
        data.add(new String[]{"lee", "lkee@tammm.com", "123-4564-7898"});
        data.add(new String[]{"chai", "chai@tammm.com", "123-4564-7898"});
        mav.addObject("data", data);
        return mav;
    }

    @RequestMapping("/tax/{tax}")
    public ModelAndView index5(@PathVariable int tax, ModelAndView mav) {
        mav.setViewName("index5");
        mav.addObject("tax", tax);
        return mav;
    }
}
