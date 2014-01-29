/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */

package org.openlmis.functional;

import org.openlmis.UiUtils.TestWebDriver;
import org.openlmis.pageobjects.*;
import org.openlmis.pageobjects.edi.ConfigureOrderPage;

import java.io.IOException;

public class PageFactory {

  private static ManageFacilityPage instanceOfManageFacilityPage;
  private static DistributionPage instanceOfDistributionPage;
  private static FacilityListPage instanceOfFacilityListPage;
  private static RefrigeratorPage instanceOfRefrigeratorPage;
  private static GeneralObservationPage instanceOfObservation;
  private static FullCoveragePage instanceOfFullCoveragePage;
  private static EPIUsePage instanceOfEpiUsePage;
  private static ChildCoveragePage instanceOfChildCoveragePage;
  private static EpiInventoryPage instanceOfEpiInventoryPage;
  private static WarehouseLoadAmountPage instanceOfWarehouseLoadAmountPage;
  private static ProgramProductISAPage instanceOfProgramProductISAPage;
  private static ConfigureOrderPage instanceOfConfigureOrderPage;
  private static RegimenTemplateConfigPage instanceOfRegimenTemplateConfigPage;
  private static LoginPage instanceOfLoginPage;
  private static HomePage instanceOfHomePage;
  private static InitiateRnRPage instanceOfInitiateRnRPage;
  private static ViewRequisitionPage instanceOfViewRequisitionPage;
  private static UpdatePodPage instanceOfUpdatePodPage;
  private static UserPage instanceOfUserPage;
  private static RolesPage instanceOfRolesPage;

  public static ManageFacilityPage getInstanceOfManageFacilityPage(TestWebDriver testWebDriver) {
    if (instanceOfManageFacilityPage == null) {
      instanceOfManageFacilityPage = new ManageFacilityPage(testWebDriver);
    }
    return instanceOfManageFacilityPage;
  }

  public static DistributionPage getInstanceOfDistributionPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfDistributionPage == null) {
      instanceOfDistributionPage = new DistributionPage(testWebDriver);
    }
    return instanceOfDistributionPage;
  }

  public static FacilityListPage getInstanceOfFacilityListPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfFacilityListPage == null) {
      instanceOfFacilityListPage = new FacilityListPage(testWebDriver);
    }
    return instanceOfFacilityListPage;
  }

  public static RefrigeratorPage getInstanceOfRefrigeratorPage(TestWebDriver testWebDriver) {
    if (instanceOfRefrigeratorPage == null) {
      instanceOfRefrigeratorPage = new RefrigeratorPage(testWebDriver);
    }
    return instanceOfRefrigeratorPage;
  }

  public static GeneralObservationPage getInstanceOfObservation(TestWebDriver testWebDriver) {
    if (instanceOfObservation == null) {
      instanceOfObservation = new GeneralObservationPage(testWebDriver);
    }
    return instanceOfObservation;
  }

  public static FullCoveragePage getInstanceOfCoveragePage(TestWebDriver testWebDriver) {
    if (instanceOfFullCoveragePage == null) {
      instanceOfFullCoveragePage = new FullCoveragePage(testWebDriver);
    }
    return instanceOfFullCoveragePage;
  }

  public static EPIUsePage getInstanceOfEpiUsePage(TestWebDriver testWebDriver) {
    if (instanceOfEpiUsePage == null) {
      instanceOfEpiUsePage = new EPIUsePage(testWebDriver);
    }
    return instanceOfEpiUsePage;
  }

  public static EpiInventoryPage getInstanceOfEpiInventoryPage(TestWebDriver testWebDriver) {
    if (instanceOfEpiInventoryPage == null) {
      instanceOfEpiInventoryPage = new EpiInventoryPage(testWebDriver);
    }
    return instanceOfEpiInventoryPage;
  }

  public static ChildCoveragePage getInstanceOfChildCoveragePage(TestWebDriver testWebDriver) {
    if (instanceOfChildCoveragePage == null) {
      instanceOfChildCoveragePage = new ChildCoveragePage(testWebDriver);
    }
    return instanceOfChildCoveragePage;
  }

  public static WarehouseLoadAmountPage getInstanceOfWarehouseLoadAmountPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfWarehouseLoadAmountPage == null) {
      instanceOfWarehouseLoadAmountPage = new WarehouseLoadAmountPage(testWebDriver);
    }
    return instanceOfWarehouseLoadAmountPage;
  }

  public static ProgramProductISAPage getInstanceOfProgramProductIsaPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfProgramProductISAPage == null) {
      instanceOfProgramProductISAPage = new ProgramProductISAPage(testWebDriver);
    }
    return instanceOfProgramProductISAPage;
  }

  public static ConfigureOrderPage getInstanceOfConfigureOrderPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfConfigureOrderPage == null) {
      instanceOfConfigureOrderPage = new ConfigureOrderPage(testWebDriver);
    }
    return instanceOfConfigureOrderPage;
  }

  public static RegimenTemplateConfigPage getInstanceOfRegimenTemplateConfigPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfRegimenTemplateConfigPage == null) {
      instanceOfRegimenTemplateConfigPage = new RegimenTemplateConfigPage(testWebDriver);
    }
    return instanceOfRegimenTemplateConfigPage;
  }

  public static LoginPage getInstanceOfLoginPage(TestWebDriver testWebDriver, String baseUrlGlobal) throws IOException {
    if (instanceOfLoginPage == null) {
      instanceOfLoginPage = new LoginPage(testWebDriver, baseUrlGlobal);
    }
    return instanceOfLoginPage;
  }

  public static HomePage getInstanceOfHomePage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfHomePage == null) {
      instanceOfHomePage = new HomePage(testWebDriver);
    }
    return instanceOfHomePage;
  }

  public static InitiateRnRPage getInstanceOfInitiateRnRPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfInitiateRnRPage == null) {
      instanceOfInitiateRnRPage = new InitiateRnRPage(testWebDriver);
    }
    return instanceOfInitiateRnRPage;
  }

  public static ViewRequisitionPage getInstanceOfViewRequisitionPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfViewRequisitionPage == null) {
      instanceOfViewRequisitionPage = new ViewRequisitionPage(testWebDriver);
    }
    return instanceOfViewRequisitionPage;
  }

  public static UpdatePodPage getInstanceOfUpdatePodPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfUpdatePodPage == null) {
      instanceOfUpdatePodPage = new UpdatePodPage(testWebDriver);
    }
    return instanceOfUpdatePodPage;
  }

  public static UserPage getInstanceOfUserPage(TestWebDriver testWebDriver) throws IOException {
    if (instanceOfUserPage == null) {
      instanceOfUserPage = new UserPage(testWebDriver);
    }
    return instanceOfUserPage;
  }

  public static RolesPage getInstanceOfRolesPage(TestWebDriver testWebDriver) {
    if (instanceOfRolesPage == null) {
      instanceOfRolesPage = new RolesPage(testWebDriver);
    }
    return instanceOfRolesPage;
  }
}
