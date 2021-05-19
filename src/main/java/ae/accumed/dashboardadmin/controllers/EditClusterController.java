package ae.accumed.dashboardadmin.controllers;

import ae.accumed.dashboardadmin.model.EditClusterResponse;
import ae.accumed.dashboardadmin.model.EditClusterSubmission;
import ae.accumed.dashboardadmin.model.EditClusterUserResponse;
import ae.accumed.dashboardadmin.services.EditClusterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/cluster")
public class EditClusterController {
    private final EditClusterService editClusterService;

    @Autowired
    public EditClusterController(EditClusterService editClusterService) {
        this.editClusterService = editClusterService;
    }

    @GetMapping
    String editCluster(Model model) {
        EditClusterResponse editClusterResponse = editClusterService.getEditClusterData();
        model.addAttribute("data", editClusterResponse);
        model.addAttribute("submission", new EditClusterSubmission());
        model.addAttribute("currentUser", new EditClusterUserResponse("", false, ""));
        return "edit_cluster";
    }

    @PostMapping
    public String saveClusterData(@Valid EditClusterSubmission editClusterSubmission, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (errors.hasErrors()) {
            if (editClusterSubmission.getUserName() == null)
                redirectAttributes.addFlashAttribute("message", "Please select a user");
            else if (editClusterSubmission.getRegion() == null)
                redirectAttributes.addFlashAttribute("message", "Please select a region");
            else if (editClusterSubmission.getClustering() == null)
                redirectAttributes.addFlashAttribute("message", "Please select a cluster");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/cluster";
        }
        try {
            editClusterService.saveUserDashboard(editClusterSubmission);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/cluster";
    }
}
