function DashboardControllerFunction($scope, leafletData,$routeParams,RnRStatusDetail,ngTableParams,$filter) {
    $scope.data = "mamama";
    console.log("Reached Here");
//year=2017&schedule=1&period=114&program=3
$scope.filter={
   period: "114",
    program:"3",
    schedule: 1,
    year: "2017",
    zoneId:18
};
    $scope.geojson = {};

    $scope.default_indicator = "period_over_expected";

    $scope.expectedFilter = function (item) {
        return item.expected > 0;
    };

    $scope.style = function (feature) {
        if ($scope.filter !== undefined && $scope.filter.indicator_type !== undefined) {
            $scope.indicator_type = $scope.filter.indicator_type;
        }
        else {
            $scope.indicator_type = $scope.default_indicator;
        }
        var color = ($scope.indicator_type === 'ever_over_total') ? interpolate(feature.ever, feature.total) : ($scope.indicator_type === 'ever_over_expected') ? interpolate(feature.ever, feature.expected) : interpolate(feature.period, feature.expected);

        return {
            fillColor: color,
            weight: 1,
            opacity: 1,
            color: 'white',
            dashArray: '1',
            fillOpacity: 0.7
        };
    };

    $scope.drawMap = function (json) {

        angular.extend($scope, {
            geojson: {
                data: json,
                style: $scope.style,
                onEachFeature: onEachFeature,
                resetStyleOnMouseout: true
            }
        });
        $scope.$apply();
    };

    function getExportDataFunction(features) {

        var arr = [];
        angular.forEach(features, function (value, key) {
            if (value.expected > 0) {
                var percentage = {'percentage': ((value.period / value.expected) * 100).toFixed(0) + ' %'};
                arr.push(angular.extend(value, percentage));
            }
        });
        $scope.exportData = arr;
    }

    filter();
    function filter() {

        $.getJSON('/gis/reporting-rate.json', $scope.filter, function (data) {
            $scope.features = data.map;
            var dataValues = [];
            var districts = _.pluck( $scope.features, 'name');
            var  expected= _.pluck( $scope.features, 'expected');
            var  reported= _.pluck( $scope.features, 'period');
            var expArray=[{name:'expected',data:expected},
                {name:'reported',data:reported}
            ];
           var districtMap = _.groupBy($scope.features,'name');
            getExportDataFunction($scope.features);
            angular.forEach(districtMap,function () {

            });
            angular.forEach($scope.features, function (feature) {
                feature.geometry_text = feature.geometry;
                feature.geometry = JSON.parse(feature.geometry);
                feature.type = "Feature";
                feature.properties = {};
                feature.properties.name = feature.name;
                feature.properties.id = feature.id;
                dataValues.push({
                    name:feature.name,
                    data:[ parseInt('200',feature.expected),parseInt('200',feature.period)]
                    // period: parseInt('200',feature.period),
                    // value:300,
                    // color: 'green'
                    // // color:interpolateCoverage(code.cumulative_vaccinated,code.monthly_district_target,code.coverageclassification.toLowerCase())

                });
            });
            console.log(JSON.stringify( $scope.features))
            $scope.drawMap({
                "type": "FeatureCollection",
                "features": $scope.features
            });
            zoomAndCenterMap(leafletData, $scope);
            var separators = Highcharts.geojson(Highcharts.maps['countries/zm/zm-all'], 'mapline');
            Highcharts.chart('container', {
                chart: {
                    type: 'bar'
                },
                title: {
                    text: 'Reporting Rate'
                },
                xAxis: {
                    categories: districts
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Reporting Rate'
                    }
                },
                legend: {
                    reversed: true
                },
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                series:  expArray
            });
            Highcharts.mapChart('container1', {
                chart: {
                    map: 'countries/zm/zm-all'
                }, credits: {enabled: false},

                title: {
                    text: '<span style="font-size: 15px !important;color: #0c9083;text-align: center"> Reporting Rate, </span>'
                },

                subtitle: {
                    text: '',
                    floating: true,
                    align: 'right',
                    y: 50,
                    style: {
                        fontSize: '16px'
                    }
                },

                legend: {

                },

                /*   colorAxis: {
                 min: 0,
                 minColor: '#FF0000',
                 maxColor: '#52C552'
                 },*/
                colorAxis: {
                    dataClasses: [{
                        from: 0,
                        to: 80,
                        color: '#ff0d00',
                        name: 'Non Reporting'
                    }, {
                        from: 80,
                        to: 90,
                        color: '#ffdb00',
                        name:'Partial Reporting'
                    }, {
                        from: 90,
                        color: '#006600',
                        name:'Fully Reporting'

                    },
                        , {
                            from: 90,
                            color: '#000000',
                            name:'Not Expected To'

                        }]
                },

                mapNavigation: {
                    enabled: true,
                    buttonOptions: {
                        verticalAlign: 'bottom'
                    }
                },

                plotOptions: {
                    map: {
                        states: {
                            /* hover: {
                             color: '#EEDD66'
                             }*/
                        }
                    }
                },

                series: [{
                    data:   dataValues ,
                    keys: ['name', 'value'],
                    joinBy: 'name',
                    name: 'Coverage',
                    dataLabels: {
                        enabled: true,
                        format: '{point.properties.postal-code}'
                    }, shadow: false
                }]
            });

        });


    };

    initiateMap($scope);

    $scope.onDetailClicked = function (feature) {
        $scope.currentFeature = feature;
        $scope.$broadcast('openDialogBox');
    };

// Instantiate the map


// Prepare random data
    var data = [
        ['Schleswig-Holstein', 1728]
    ];

    $.getJSON('https://cdn.rawgit.com/highcharts/highcharts/057b672172ccc6c08fe7dbb27fc17ebca3f5b770/samples/data/germany.geo.json', function (geojson) {

        // Initiate the chart

    });

//rnr status

function DashboardControllerFunction($scope) {

 function calculatePercentage(data){
     var total = 0;
     angular.forEach(data,function (da,index) {
         total += da.current;
     });
     return parseInt(total/parseInt(data.length,10),10);
 }

$scope.orderFillRateByZone ={
    "zones": [
        {
            "name": "North East",
            "prev": 89,
            "current": 90,
            "status": "good"
        },
        {
            "name": "Western",
            "prev": 89,
            "current": 89,
            "status": "normal"
        },
        {
            "name": "Southern",
            "prev": 50,
            "current": 69,
            "status": "bad"
        },{
            "name": "North Western",
            "prev": 70,
            "current": 20,
            "status": "bad"
        },{
            "name": "Northern",
            "prev": 70,
            "current": 60,
            "status": "bad"
        },{
            "name": "Muchinga",
            "prev": 70,
            "current": 90,
            "status": "bad"
        },{
            "name": "Luapula",
            "prev": 70,
            "current": 90,
            "status": "bad"
        },{
            "name": "Copperbelt",
            "prev": 70,
            "current": 20,
            "status": "bad"
        },{
            "name": "Central",
            "prev": 70,
            "current": 80,
            "status": "bad"
        },{
            "name": "Lusaka Province",
            "prev": 70,
            "current": 85,
            "status": "bad"
        }
    ]
};

$scope.stockAvailability ={
    "zones": [
        {
            "name": "North East",
            "prev": 75,
            "current": 85,
            "status": "good"
        },
        {
            "name": "Western",
            "prev": 80,
            "current": 81,
            "status": "normal"
        },
        {
            "name": "Southern",
            "prev": 61,
            "current": 71,
            "status": "bad"
        },{
            "name": "North Western",
            "prev": 70,
            "current": 75,
            "status": "bad"
        },{
            "name": "Northern",
            "prev": 50,
            "current": 55,
            "status": "bad"
        },{
            "name": "Muchinga",
            "prev": 30,
            "current": 79,
            "status": "bad"
        },{
            "name": "Luapula",
            "prev": 40,
            "current": 79,
            "status": "bad"
        },{
            "name": "Copperbelt",
            "prev": 90,
            "current": 85,
            "status": "bad"
        },{
            "name": "Central",
            "prev": 75,
            "current": 86,
            "status": "bad"
        },{
            "name": "Lusaka Province",
            "prev": 89,
            "current": 90,
            "status": "bad"
        }
    ]
};
$scope.reportingRate ={
    "zones": [
        {
            "name": "North East",
            "prev": 60,
            "current": 80,
            "status": "good"
        },
        {
            "name": "Western",
            "prev": 89,
            "current": 81,
            "status": "normal"
        },
        {
            "name": "Southern",
            "prev": 81,
            "current": 89,
            "status": "bad"
        },{
            "name": "North Western",
            "prev": 81,
            "current": 90,
            "status": "bad"
        },{
            "name": "Northern",
            "prev": 76,
            "current": 83,
            "status": "bad"
        },{
            "name": "Muchinga",
            "prev": 84,
            "current": 98,
            "status": "bad"
        },{
            "name": "Luapula",
            "prev": 70,
            "current": 80,
            "status": "bad"
        },{
            "name": "Copperbelt",
            "prev": 50,
            "current": 50,
            "status": "bad"
        },{
            "name": "Central",
            "prev": 60,
            "current": 79,
            "status": "bad"
        },{
            "name": "Lusaka Province",
            "prev": 75,
            "current": 80,
            "status": "bad"
        }
    ]
};

function borderColor(data){
    return (data >= 80)?'green':(data<80 && data>70)?'orange':'red';

}

$scope.dynamicPerformanceChart = function(data,chartId,name,result)

{

        var gaugeOptions = {

            chart: {
                type: 'solidgauge',
                margin: [0, 0, 0, 0],
                backgroundColor: 'transparent'
            },
            title: null,
            yAxis: {
                min: 0,
                max: 100,
                minColor: borderColor(result),
                maxColor: borderColor(result),
                lineWidth: 0,
                tickWidth: 0,
                minorTickLength: 0,
                // minTickInterval: 500,
                labels: {
                    enabled: false
                }
            },
            pane: {
                size: '100%',
                center: ['50%', '50%'],
                startAngle: 0,
                endAngle: 360,
                background: {
                    borderWidth: 20,
                    backgroundColor: '#DBDBDB',
                    shape: 'arch',
                    borderColor: '#DBDBDB',
                    outerRadius: '80%',
                    innerRadius: '80%'
                }
            },
            tooltip: {
                enabled: true
            },
            plotOptions: {
                solidgauge: {
                    borderColor: borderColor(result),
                    borderWidth: 18,
                    radius: 75,
                    innerRadius: '80%',
                    dataLabels: {
                        borderWidth: 0,
                        useHTML: true,
                        enable: true
                    }
                }
            },
            series: [{
                name: name,
                data: [result],
                dataLabels: {
                    format: '<div style="Width: 30px;text-align:center"><span style="font-size:20px;color:"'+borderColor(data.ofr)+'"><br>{y}%</span></div>'
                }

            }],

            credits: {
                enabled: false
            }
        };
        $(chartId).highcharts(gaugeOptions);
    };
    $scope.dynamicPerformanceChart($scope.orderFillRateByZone,'#container-order-fill-rate','OrderFillRate',calculatePercentage($scope.orderFillRateByZone.zones));
    $scope.dynamicPerformanceChart($scope.stockAvailability,'#stock-availability','StockAvailability',calculatePercentage($scope.stockAvailability.zones));
    $scope.dynamicPerformanceChart($scope.reportingRate,'#reporting-rate','ReportingRate',calculatePercentage($scope.reportingRate.zones));

    var dataValues = [
        ['zm-lp', 0],
        ['zm-no', 1],
        ['zm-ce', 2],
        ['zm-ea', 3],
        ['zm-ls', 4],
        ['zm-co', 5],
        ['zm-nw', 6],
        ['zm-so', 7],
        ['zm-we', 8],
        ['zm-mu', 9]
    ];

    $scope.loadStockStatusByLocation = function (params) {

        $.getJSON('/gis/stock-status-products.json', params, function (data) {
            console.log($scope.products);
            $scope.products = data.products;
        });

        Highcharts.mapChart('stock_status_map', {
            chart: {
                map: 'countries/zm/zm-all'
            }, credits: {enabled: false},

            title: {
                text: '<span style="font-size: 15px !important;color: #0c9083;text-align: center">Stock Status By Location</span>'
            },

            subtitle: {
                text: '',
                floating: true,
                align: 'right',
                y: 50,
                style: {
                    fontSize: '16px'
                }
            },

            legend: {},

            /*   colorAxis: {
                   min: 0,
                   minColor: '#FF0000',
                   maxColor: '#52C552'
               },*/
            colorAxis: {
                dataClasses: [{
                    from: 0,
                    to: 80,
                    color: '#ff0d00',
                    name: ''
                }, {
                    from: 80,
                    to: 90,
                    color: '#ffdb00',
                    name: ''
                }, {
                    from: 90,
                    color: '#006600',
                    name: ' '

                }]
            },

            mapNavigation: {
                enabled: true,
                buttonOptions: {
                    verticalAlign: 'bottom'
                }
            },

            plotOptions: {
                map: {
                    states: {
                        /* hover: {
                             color: '#EEDD66'
                         }*/
                    }
                }
            },

            series: [{
                data: dataValues,
                // joinBy: ['hc-key', 'code'],
                name: 'Coverage',
                dataLabels: {
                    enabled: true,
                    format: '{point.properties.postal-code}'
                }, shadow: false
            }]
        });


    };

    var defaultParam = {
        year: parseInt(2017, 10),
        schedule: parseInt(1, 10),
        period: parseInt(115, 10),
        program: parseInt(3, 10)
    };
    $scope.loadStockStatusByLocation(defaultParam);

}

DashboardControllerFunction.resolve = {};