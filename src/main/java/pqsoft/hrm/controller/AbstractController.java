package pqsoft.hrm.controller;

import org.springframework.ui.Model;
import pqsoft.hrm.util.SecurityUtils;

public class AbstractController {
  public void putUserInfo(Model model) {
    model.addAttribute("admin", SecurityUtils.getAdmin());
    model.addAttribute("username", SecurityUtils.getUsername());
    model.addAttribute("avatar", SecurityUtils.getAvatar());
  }
}
