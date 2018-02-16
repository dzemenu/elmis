
/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */
package org.openlmis.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.openlmis.upload.Importable;
import org.openlmis.upload.annotation.ImportField;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacilityOwner extends BaseModel implements Importable{
    private String displayName;
    @ImportField(mandatory = true, name = "Owner Code", nested = "code")
    private Owner owner;
    @ImportField(mandatory = true, name = "Facility Code", nested = "code")
    private Facility facility;
    private boolean active=true;
    private String description;
    public String toString(){
        return  owner.getCode() + " "+owner.getText();
    }

    public String getDisplayName(){
        return  owner.getCode() + " "+owner.getText();
    }
}
