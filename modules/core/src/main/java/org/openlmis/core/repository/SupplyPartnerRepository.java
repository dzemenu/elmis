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

package org.openlmis.core.repository;

import org.openlmis.core.domain.SupplyPartner;
import org.openlmis.core.repository.mapper.SupplyPartnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplyPartnerRepository {

  @Autowired
  private SupplyPartnerMapper mapper;

   public List<SupplyPartner> getAll(){
     return mapper.getAll();
   }

   public SupplyPartner getById(Long id){
     return mapper.getById(id);
   }

   public void insert(SupplyPartner supplyPartner){
     mapper.insert(supplyPartner);
   }

   public void update(SupplyPartner supplyPartner){
     mapper.update(supplyPartner);
   }


}
