package ae.accumed.dashboardadmin.controllers;

import ae.accumed.dashboardadmin.model.IndexResponse;
import ae.accumed.dashboardadmin.model.Submission;
import ae.accumed.dashboardadmin.services.IndexService;
import ae.accumed.dashboardadmin.services.UserDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
public class AppController {

    private final IndexService indexService;
    private final UserDashboardService userDashboardService;

    @Autowired
    public AppController(IndexService indexService, UserDashboardService userDashboardService) {
        this.indexService = indexService;
        this.userDashboardService = userDashboardService;
    }

    @GetMapping
    public String index(Model model) {
        IndexResponse indexResponse = indexService.getIndexData();
        model.addAttribute("data", indexResponse);
        model.addAttribute("submission", new Submission());
        return "index";
    }

    @PostMapping
    public String saveUserData(@Valid Submission submission, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (errors.hasErrors())
            return "redirect:/";
        try {
            userDashboardService.saveUserDashboard(submission);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/";
    }
}
