package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {  //model is an instance of the Model class
        model.addAttribute("col", "all");
        model.addAttribute("term", "");
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    /*params = searchTerm, searchType
    send it model, searchTerm, searchType
    call some of your old functions from TechJobs console - findbyvalue, findbycolumnandvalue
    make a new arraylist jobs & fill with the results
     */

    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("col",searchType);
        model.addAttribute("term",searchTerm);

        ArrayList<HashMap<String, String>> jobs;

        if (searchTerm.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            return "search";

        } else {
            jobs = JobData.findByColumnAndValue(searchTerm, searchType);
            model.addAttribute("title", "All" + ListController.columnChoices.get(searchType) + "Values");
            model.addAttribute("jobs", jobs);
            return "search";
        }

    }

}