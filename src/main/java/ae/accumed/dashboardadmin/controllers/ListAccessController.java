package ae.accumed.dashboardadmin.controllers;

import ae.accumed.dashboardadmin.DTO.request.EditAccessRequest;
import ae.accumed.dashboardadmin.DTO.response.IndexResponse;
import ae.accumed.dashboardadmin.services.ListAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ListAccessController {
    private final ListAccessService listAccessService;

    @Autowired
    public ListAccessController(ListAccessService listAccessService) {
        this.listAccessService = listAccessService;
    }

    @GetMapping
    String index(Model model) {
        IndexResponse indexResponse = listAccessService.getIndexData();
        model.addAttribute("data", indexResponse);
        model.addAttribute("submission", new EditAccessRequest());
        return "index";
    }
}
