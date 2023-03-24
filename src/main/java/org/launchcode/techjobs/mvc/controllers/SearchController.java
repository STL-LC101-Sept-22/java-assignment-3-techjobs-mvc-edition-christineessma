package org.launchcode.techjobs.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import java.util.ArrayList;


import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
@PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        //displaySearchResults method with model parameter, searchType and searchTerm parameter
        ArrayList<Job> jobs = new ArrayList<>();
        //stores searched jobs in an array list
        if ( (searchTerm == ("all")) || (searchTerm == ("")) ) {
            jobs = JobData.findAll();
    }
        //if "all" is entered or box left empty, findAll() method called
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }


    model.addAttribute("jobs", jobs);
    model.addAttribute("columns", columnChoices);
        return "search";
}

}
