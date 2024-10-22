package edu.du.sb1022secu4.controller;

import edu.du.sb1022secu4.entity.Member;
import edu.du.sb1022secu4.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class RegistrationController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 기존 빈 사용

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("member") Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 기존 빈 사용하여 비밀번호 인코딩
        member.setRole("USER"); // 기본적으로 ROLE_USER로 설정
        memberRepository.save(member);
        return "redirect:/login";
    }
}

