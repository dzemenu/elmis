package org.openlmis.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeographicZone {
  Integer id;
  String name;
  GeopoliticalLevel level;
  GeographicZone parent;
}
