package ae.accumed.dashboardadmin.controllers;

import ae.accumed.dashboardadmin.model.IndexResponse;
import ae.accumed.dashboardadmin.model.EditAccessSubmission;
import ae.accumed.dashboardadmin.model.IndexUserResponse;
import ae.accumed.dashboardadmin.services.IndexService;
import ae.accumed.dashboardadmin.services.UserDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/edit")
public class EditController {

    private final IndexService indexService;
    private final UserDashboardService userDashboardService;

    @Autowired
    public EditController(IndexService indexService, UserDashboardService userDashboardService) {
        this.indexService = indexService;
        this.userDashboardService = userDashboardService;
    }

    @GetMapping
    public String edit(Model model) {
        IndexResponse indexResponse = userDashboardService.getEditData();
        model.addAttribute("data", indexResponse);
        model.addAttribute("submission", new EditAccessSubmission());
        model.addAttribute("currentUser", new IndexUserResponse("", false, "", new ArrayList<>()));
        return "edit";
    }

    @GetMapping("/{userName}")
    public String edit(Model model, @PathVariable String userName) {
        IndexResponse indexResponse = indexService.getIndexData();
        model.addAttribute("data", indexResponse);
        model.addAttribute("submission", new EditAccessSubmission());
        if (userName != null) {
            Optional<IndexUserResponse> currentUserResponseOptional = indexResponse.getIndexUserResponses().stream().filter(indexUserResponse -> indexUserResponse.getUserName().equals(userName)).findFirst();
            currentUserResponseOptional.ifPresent(indexUserResponse -> model.addAttribute("currentUser", indexUserResponse));
        } else {
            model.addAttribute("currentUser", new IndexUserResponse("", false, "", new ArrayList<>()));
        }
        return "edit";
    }

    @PostMapping
    public String saveUserData(EditAccessSubmission editAccessSubmission, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (errors.hasErrors())
            return "redirect:/edit";
        try {
            userDashboardService.saveUserDashboard(editAccessSubmission);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/edit";
    }
}
