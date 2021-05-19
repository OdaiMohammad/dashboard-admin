package ae.accumed.dashboardadmin.controllers;

import ae.accumed.dashboardadmin.DTO.request.EditAccessByClusterRequest;
import ae.accumed.dashboardadmin.DTO.response.EditClusterResponse;
import ae.accumed.dashboardadmin.DTO.response.EditClusterUserResponse;
import ae.accumed.dashboardadmin.services.EditAccessByClusterService;
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
public class EditAccessByClusterController {
    private final EditAccessByClusterService editAccessByClusterService;

    @Autowired
    public EditAccessByClusterController(EditAccessByClusterService editAccessByClusterService) {
        this.editAccessByClusterService = editAccessByClusterService;
    }

    @GetMapping
    String editCluster(Model model) {
        EditClusterResponse editClusterResponse = editAccessByClusterService.getEditClusterData();
        model.addAttribute("data", editClusterResponse);
        model.addAttribute("submission", new EditAccessByClusterRequest());
        model.addAttribute("currentUser", new EditClusterUserResponse("", false, ""));
        return "edit_cluster";
    }

    @PostMapping
    public String saveClusterData(@Valid EditAccessByClusterRequest editAccessByClusterRequest, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Failed");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if (errors.hasErrors()) {
            if (editAccessByClusterRequest.getUserName() == null)
                redirectAttributes.addFlashAttribute("message", "Please select a user");
            else if (editAccessByClusterRequest.getRegion() == null)
                redirectAttributes.addFlashAttribute("message", "Please select a region");
            else if (editAccessByClusterRequest.getClustering() == null)
                redirectAttributes.addFlashAttribute("message", "Please select a cluster");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/cluster";
        }
        try {
            editAccessByClusterService.saveUserDashboard(editAccessByClusterRequest);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/cluster";
    }
}
