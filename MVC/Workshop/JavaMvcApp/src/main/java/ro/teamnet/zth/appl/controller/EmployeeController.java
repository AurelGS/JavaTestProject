package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

@MyController(urlPath = "/employees")

public class EmployeeController {

    @MyRequestMethod(urlPath = "/one")
    public String getOneEmployee() {
        return "oneRandomEmployee";
    }

    @MyRequestMethod(urlPath = "/all")
    public String getAllEmployees() {
        return "allEmployees";
    }

}
