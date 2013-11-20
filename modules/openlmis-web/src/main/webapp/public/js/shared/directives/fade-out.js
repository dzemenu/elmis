/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */

app.directive('fadeOut', function ($timeout) {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      var timeouts = [];

      function fadeMessage() {
        element.fadeOut('slow');
        scope.message = undefined;
      }

      scope.$watch('message', function () {
        if (!scope.message) return;
        timeouts.forEach(function (timeoutId) {
          $timeout.cancel(timeoutId);
        });
        timeouts.length = 0;
        timeouts.push($timeout(fadeMessage, utils.parseIntWithBaseTen(attrs.fadeOut)));
      });

    }
  };
});