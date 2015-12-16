/*
 * Electronic Logistics Management Information System (eLMIS) is a supply chain management system for health commodities in a developing country setting.
 *
 * Copyright (C) 2015  John Snow, Inc (JSI). This program was produced for the U.S. Agency for International Development (USAID). It was prepared under the USAID | DELIVER PROJECT, Task Order 4.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openlmis.web.controller.vaccine;

import org.openlmis.core.web.OpenLmisResponse;
import org.openlmis.vaccine.service.VaccineDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/vaccine/dashboard/")
public class VaccineDashboardController {

    @Autowired
    VaccineDashboardService service;

    @RequestMapping(value = "summary.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getReportingSummary(){

        Map<String, Object> summary = new HashMap<>();
        summary.put("reportingSummary", service.getReportingSummary());
        summary.put("repairing", service.getRepairingSummary());
        summary.put("investigating", service.getInvestigatingSummary());

        return OpenLmisResponse.response("summary", summary);
    }

    @RequestMapping(value = "reporting-details.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getReportingDetails(){
        return OpenLmisResponse.response("reportingDetails", service.getReportingDetails());
    }

    @RequestMapping(value = "monthly-coverage.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getCoverageByMonthly(@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate, Long product){

        return OpenLmisResponse.response("monthlyCoverage", service.getMonthlyCoverage(startDate, endDate, product));
    }

    @RequestMapping(value = "district-coverage.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getDistrictCoverage(@RequestParam("period") Long period, @RequestParam("product") Long product){
        return OpenLmisResponse.response("districtCoverage", service.getDistrictCoverage(period, product));
    }
    @RequestMapping(value = "monthly-wastage.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getWastageByMonthly(@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate, @RequestParam("product") Long product){

        return OpenLmisResponse.response("wastageMonthly",  service.getMonthlyWastage(startDate, endDate, product));
    }

    @RequestMapping(value = "district-wastage.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getWastageByDistrict(@RequestParam("period") Long period, @RequestParam("product") Long product){

        return OpenLmisResponse.response("districtWastage", service.getWastageByDistrict(period, product));
    }


    @RequestMapping(value = "sessions.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getSessions(@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate){


        return OpenLmisResponse.response("monthlySessions", service.getMonthlySessions(startDate, endDate));
    }

    @RequestMapping(value = "district-sessions.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getDistrictSessions(@RequestParam("period")Long period){


        return OpenLmisResponse.response("districtSessions", service.getDistrictSessions(period));
    }

    @RequestMapping(value = "monthly-dropout.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getMonthlyDropout(@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate, @RequestParam("product") Long product){
        return OpenLmisResponse.response("monthlyDropout", service.getMonthlyDropout(startDate, endDate, product));
    }

    @RequestMapping(value = "district-dropout.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getDistrictDropout(@RequestParam("period") Long period, @RequestParam("product") Long product){
        return OpenLmisResponse.response("districtDropout", service.getDistrictDropout(period,product));
    }


    @RequestMapping(value = "bundle.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getBundling(@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate, @RequestParam("product")Long productId){
        return OpenLmisResponse.response("bundling", service.getBundling(startDate, endDate, productId));
    }

    @RequestMapping(value = "monthly-stock.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getMonthlyStock(@RequestParam("startDate")String startDate, @RequestParam("endDate") String endDate, @RequestParam("product") Long product){
        return OpenLmisResponse.response("monthlyStock", service.getMonthlyStock(startDate, endDate, product));
    }

    @RequestMapping(value = "district-stock.json", method = RequestMethod.GET)
    public ResponseEntity<OpenLmisResponse> getDistrictStock(@RequestParam("period") Long period, @RequestParam("product") Long product){
        return OpenLmisResponse.response("districtStock", service.getDistrictStock(period,product));
    }
}