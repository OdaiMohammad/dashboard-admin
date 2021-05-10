package ae.accumed.dashboardadmin.controllers;

import ae.accumed.dashboardadmin.model.IndexResponse;
import ae.accumed.dashboardadmin.model.Submission;
import ae.accumed.dashboardadmin.services.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AppController {
    private final IndexService indexService;

    @Autowired
    public AppController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping
    String index(Model model) {
        IndexResponse indexResponse = indexService.getIndexData();
        model.addAttribute("data", indexResponse);
        model.addAttribute("submission", new Submission());
        return "index";
    }
}
