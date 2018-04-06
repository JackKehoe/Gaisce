package com.jack.project.web;

import com.jack.project.model.*;
import com.jack.project.service.*;
import com.jack.project.validator.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private SecurityService securityService;
    @Autowired 
    private SkillService skillService;
    @Autowired
    private GoalService goalService;

    @Autowired
    private UserValidator userValidator; 
    @Autowired
    private ReportValidator reportValidator;
    @Autowired
    private SkillValidator skillValidator;

    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Principal principal) throws IOException{
    	User currentUser = userService.findByUsername(principal.getName());
    	List<Skill> skills = currentUser.getSkills();
    	List<Goal> goal = currentUser.getGoal();
    	List<Report> reports = currentUser.getReports();
    	
    	model.addAttribute("skills", skills);
    	model.addAttribute("goal", goal);
    	model.addAttribute("currentUser", currentUser);
    	model.addAttribute("reports", reports);
        
        return "welcome";
    }
    
    
   
    @RequestMapping(value = {"/report"}, method = RequestMethod.GET)
    public String report(Model model) {
        model.addAttribute("reportForm", new Report());
        return "report";
    }
    
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String report(@ModelAttribute("reportForm") Report reportForm, BindingResult bindingResult, Model model, Principal principal) {
        reportValidator.validate(reportForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "report";
        }
        
        String name = principal.getName();
        reportService.save(reportForm, name);
        User currentUser = userService.findByUsername(principal.getName());
        currentUser.addReport(reportForm);
        
        List<Report> savedReports = currentUser.getReports();
        model.addAttribute("savedReports", savedReports);
        model.addAttribute("currentUser", currentUser);
        
        return "redirect:/welcome";
    }
    
    
    @RequestMapping(value = {"/skill"}, method = RequestMethod.GET)
    public String skill(Model model) {
        model.addAttribute("skillForm", new Skill());
        return "skill";
    }
    
    @RequestMapping(value = "/skill", method = RequestMethod.POST)
    public String skill(@ModelAttribute("skillForm") Skill skillForm, BindingResult bindingResult, Model model, Principal principal) {
        skillValidator.validate(skillForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "skill";
        }
        
        String name = principal.getName();
        skillService.save(skillForm, name);
        User currentUser = userService.findByUsername(principal.getName());
        currentUser.addSkill(skillForm);

        List<Skill> savedSkills = currentUser.getSkills();
        for(int i = 0; i < savedSkills.size(); i++) {
        	 model.addAttribute("savedSkills", savedSkills);
             model.addAttribute("currentUser", currentUser);
        }
        
        return "redirect:/welcome";
    }
    
    @RequestMapping(value = {"/goal"}, method = RequestMethod.GET)
    public String goal(Model model) {
        model.addAttribute("goalForm", new Goal());
        return "goal";
    }
    
    @RequestMapping(value = "/goal", method = RequestMethod.POST)
    public String goal(@ModelAttribute("goalForm") Goal goalForm, BindingResult bindingResult, Model model, Principal principal) {

        User currentUser = userService.findByUsername(principal.getName());
        String name = principal.getName();
        goalService.save(goalForm, name);
        
        List<Goal> savedGoal = currentUser.getGoal();
        model.addAttribute("savedGoal", savedGoal);
        model.addAttribute("currentUser", currentUser);

        return "redirect:/welcome";
    }
}
