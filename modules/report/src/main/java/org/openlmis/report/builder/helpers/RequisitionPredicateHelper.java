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

package org.openlmis.report.builder.helpers;

public class RequisitionPredicateHelper {

  private RequisitionPredicateHelper() {

  }

  public static String multiProductFilterBy(String products, String productIdField, String tracerField) {
    if ("-1".equals(products)) {
      return String.format("%s = true", tracerField);
    } else if (!"0".equals(products) && !products.isEmpty()) {
      return String.format(" %2$s = ANY(array[ %1$s ]::INT[])", products, productIdField);
    }
    return null;
  }

  public static String productCategoryIsFilteredBy(String field) {
    return String.format("%s = #{filterCriteria.productCategory} ", field);
  }

  public static String userHasPermissionOnFacilityBy(String field) {
    return String.format("%s in (select facility_id from vw_user_facilities where user_id = #{userId} and program_id = #{filterCriteria.program}) ", field);
  }

  public static String geoZoneIsFilteredBy(String viewAlias) {
    return String.format("(%1$s.zone_id = #{filterCriteria.zone} or %1$s.parent = #{filterCriteria.zone} or %1$s.region_id = #{filterCriteria.zone} or %1$s.district_id = #{filterCriteria.zone})", viewAlias);
  }

  public static String programIsFilteredBy(String field) {
    return String.format("%s = #{filterCriteria.program}", field);
  }

  public static String periodIsFilteredBy(String field) {
    return String.format("%s = #{filterCriteria.period}", field);
  }

  public static String rnrStatusFilteredBy(String field, String acceptedRnrStatuses) {
    return String.format("%1$s in ( %2$s )", field, acceptedRnrStatuses);
  }

  public static String productFilteredBy(String field) {
    return String.format("%s = #{filterCriteria.product}::INT", field);
  }

  public static String facilityIsFilteredBy(String field) {
    return String.format("%s = #{filterCriteria.facility}::INT", field);
  }

  public static String facilityTypeIsFilteredBy(String field) {
    return String.format("%s= #{filterCriteria.facilityType}", field);
  }
  public static String facilityOwnerIdFilteredBy(String field) {
    return String.format("%s= #{filterCriteria.facilityOwner}", field);
  }
  public static String regimenCategoryIsFilteredBy(String field) {
    return String.format("%s= #{filterCriteria.regimenCategory}", field);
  }

  public static String regimenIsFilteredBy(String field) {
    return String.format("%s= #{filterCriteria.regimen}", field);
  }

  public static String statusDateFilteredBy(String field) {
    return String.format("%s <= #{filterCriteria.statusDate}::DATE ", field);
  }

  public static String startDateFilteredBy(String field, String startDate) {
    return String.format("%1$s >= '%2$s'::DATE ", field, startDate);
  }

  public static String endDateFilteredBy(String field, String endDate) {
    return String.format("%1$s <= '%2$s'::DATE ", field, endDate);
  }
  public static String facilityStatusFilteredBy(String field, String facilityStatusList) {
    return String.format("%1$s in ('%2$s')", field, facilityStatusList);
  }
  public static String facilityOwnerIsFilteredBy(String field) {
    return String.format("%s= #{filterCriteria.facilityOwner}", field);
  }

  public static String reportTypeFilteredBy(String field) {
    return String.format("%s = #{filterCriteria.isEmergency}::boolean", field);
  }


  /**
   *  a predicate for getting a start date of a period for any selected date @param startDate
   * @param field
   * @param startDate
   * @return
   */
  public static String periodStartDateRangeFilteredBy(String field, String startDate){
    return String.format("%1$s::date >= (select p.startdate::DATE from processing_periods p\n" +
            "where p.startdate <= '%2$s'\n" +
            "order by startdate desc limit 1)", field, startDate);
  }

  /**
   * a predicate for getting a end date of a period for any selected date @param endDate
   * @param field
   * @param endDate
   * @return
   */
  public static String periodEndDateRangeFilteredBy(String field, String endDate){
    return String.format("%1$s::date <= (select p.enddate::DATE from processing_periods p " +
            "where p.enddate >= '%2$s::date' " +
            "order by p.enddate ASC  limit 1) ", field, endDate);
  }

  public static String dateFilteredBy(String field, String date) {
    return String.format("%1$s = '%2$s'::DATE ", field, date);
  }
}
