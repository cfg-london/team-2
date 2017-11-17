<!DOCTYPE html>
<link rel="stylesheet" href="https://openlayers.org/en/v4.1.1/css/ol.css" type="text/css">
    <!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
    <script src="https://openlayers.org/en/v4.1.1/build/ol.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script>
        function map(x, in_min, in_max, out_min, out_max)
        {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
        }
        function getRadius(meters)
        {
            //return 2189068/5;
            if(meters<60)
                return 120378137;
            if(meters>=60 && meters<=100)
                return map(meters,60,100,120378137,60378137);
            if(meters>=100 && meters<=400)
                return map(meters,100,400,60378137,16378137);
            if(meters>=400 && meters<=750)
                return map(meters,400,750,16378137,8189068);
            if(meters>=750 && meters<=15000)
                return map(meters,750,15000,8189068,437813);
            /*if(meters==60)
                return 120378137;
            if(meters==100)
                return 60378137;
            if(meters==400)
                return 16378137;
            if(meters==750)
                return 8189068;*/

        }
        
        var iconStyle = new ol.style.Style({
        image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
          anchor: [0.5, 46],
          anchorXUnits: 'fraction',
          anchorYUnits: 'pixels',
          src: 'https://openlayers.org/en/v4.1.1/examples/data/icon.png'
        }))
      });
      var icon= new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([24.04292234,47.64118283], 'EPSG:4326',     
                'EPSG:3857')),
            name: 'Null Island',
            population: 4000,
            rainfall: 500
        });
    </script>
<?php
    function haversineGreatCircleDistance(
  $latitudeFrom, $longitudeFrom, $latitudeTo, $longitudeTo, $earthRadius = 6371000)
{
  // convert from degrees to radians
  $latFrom = deg2rad($latitudeFrom);
  $lonFrom = deg2rad($longitudeFrom);
  $latTo = deg2rad($latitudeTo);
  $lonTo = deg2rad($longitudeTo);

  $latDelta = $latTo - $latFrom;
  $lonDelta = $lonTo - $lonFrom;

  $angle = 2 * asin(sqrt(pow(sin($latDelta / 2), 2) +
    cos($latFrom) * cos($latTo) * pow(sin($lonDelta / 2), 2)));
  return $angle * $earthRadius;
}
    include ("database.php");
    $conn=openConnection();
    $points=readPointEntries($conn);
    var_dump($points);
    $pointsCount=0;
    $lenghtOfPoints=sizeof($points);


    echo "<br>".$lenghtOfPoints."<br>";
    //$lenghtOfPoints=400;
    //distanceOverMinute
    $thePointBeforeOneMinute=-1;
    for($i=$lenghtOfPoints-1;$i>=0;$i--)
    {
        $datetime1 = strtotime((string)$points[$lenghtOfPoints-1]["Time"]);
        $datetime2 = strtotime((string)$points[$i]["Time"]);
        $interval  = abs($datetime2 - $datetime1);
        $minutes   = round($interval / 60);
        if($minutes>0)
        {
            $thePointBeforeOneMinute=$i;
            break;
        }
    }
    $distanceBetweenLastPoints=0;
    //if($lenghtOfPoints>1)
        //$distanceBetweenLastPoints=haversineGreatCircleDistance($points[$lenghtOfPoints-1]["X"],$points[$lenghtOfPoints-1]["Y"],$points[$thePointBeforeOneMinute]["X"],$points[$thePointBeforeOneMinute]["Y"]);
    $distanceBetweenLastPoints=round($distanceBetweenLastPoints);
    echo $distanceBetweenLastPoints;
    echo "<script>var distanceOverMinute=$distanceBetweenLastPoints;</script>";
    echo "<script>lastX=".$points[$lenghtOfPoints-1]["X"].";lastY=".$points[$lenghtOfPoints-1]["Y"].";</script>";
    echo "<script>var icons=[";
    //var icons=[icon,icon,icon,icon];
    
    for($i=0;$i<$lenghtOfPoints;$i++)
    {
        echo "icon";
        if($i<$lenghtOfPoints-1)
        {   
            echo ",";
        }
    }
    echo "];";
    for($i=0;$i<$lenghtOfPoints;$i++)
    {
        echo "icons[$i]= new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([".$points[$i]["Y"].",".$points[$i]["X"]."], 'EPSG:4326',     
                'EPSG:3857')),
            name: 'Null Island',
            population: 4000,
            rainfall: 500
        });
        icons[$i].setStyle(iconStyle);";
        if($i==0)
        {
            //echo "var icons=[icon,icon,icon,icon]";
        }
        else
        {
            //echo "icons[".$i."]=icon;";
        }
    }
    echo "</script>";
?>
<html>
  <head>
    <title>Icon Symbolizer</title>
    
    <style>
      #map {
        position: relative;
      }
    </style>
  </head>
  <body>
    <div id="map" class="map"><div id="popup"></div></div>
    <script>
      

      var iconFeature2 = new ol.Feature({
        geometry: new ol.geom.Point(ol.proj.transform([26.24592234,47.64118283], 'EPSG:4326',     
  'EPSG:3857')),
        name: 'Null Island',
        population: 4000,
        rainfall: 500
      });
      

      
      iconFeature2.setStyle(iconStyle);

      var vectorSource = new ol.source.Vector({
        features: icons
      });

      var vectorLayer = new ol.layer.Vector({
        source: vectorSource
      });

    

var baseLayer = new ol.layer.Tile({
    source: new ol.source.OSM()
});

precisionCircle = ol.geom.Polygon.circular(
        /* WGS84 Sphere */
        new ol.Sphere(getRadius(distanceOverMinute)),
        [lastY,lastX],
        1000,
        /* Number of verticies */
        64).transform('EPSG:4326', 'EPSG:3857');
precisionCircleFeature = new ol.Feature(precisionCircle)
vectorSource2 = new ol.source.Vector();
vectorSource2.addFeature(precisionCircleFeature);
 layer = new ol.layer.Vector({
    source: vectorSource2,
    style: [
        new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: 'red',
                width: 3
            }),
            fill: new ol.style.Fill({
                color: 'rgba(255, 0, 0, 0.1)'
            })
        })]
});

      var rasterLayer = new ol.layer.Tile({
        source: new ol.source.TileJSON({
          url: 'https://api.tiles.mapbox.com/v3/mapbox.geography-class.json?secure',
          crossOrigin: ''
        })
      });
      var bingLayer = new ol.layer.Tile({
          visible: false,
          preload: Infinity,
          source: new ol.source.BingMaps({
            key: 'AooOFaixUfdTBP29KqbmLOXXpI7LJcJ9VnHomdG-REla4DpvRO33GrCKa3gQiUx5',
            imagerySet: 'AerialWithLabels'
            // use maxZoom 19 to see stretched tiles instead of the BingMaps
            // "no photos at this zoom level" tiles
            // maxZoom: 19
          })
        });
        bingLayer.setVisible(1);
      var map = new ol.Map({
        layers: [bingLayer, vectorLayer,layer],
        target: document.getElementById('map'),
        view: new ol.View({
          center: ol.proj.fromLonLat([22.87892234,47.79118283]),
            zoom: 16
        })
      });

      var element = document.getElementById('popup');

      var popup = new ol.Overlay({
        element: element,
        positioning: 'bottom-center',
        stopEvent: false,
        offset: [0, -50]
      });
      map.addOverlay(popup);

      // display popup on click
      map.on('click', function(evt) {
        var feature = map.forEachFeatureAtPixel(evt.pixel,
            function(feature) {
              return feature;
            });
        if (feature) {
          var coordinates = feature.getGeometry().getCoordinates();
          popup.setPosition(coordinates);
          $(element).popover({
            'placement': 'top',
            'html': true,
            'content': feature.get('name')
          });
          $(element).popover('show');
        } else {
          $(element).popover('destroy');
        }
      });
      
      // change mouse cursor when over marker
      map.on('pointermove', function(e) {
        if (e.dragging) {
          $(element).popover('destroy');
          return;
        }
        var radius=500000;
        var pixel = map.getEventPixel(e.originalEvent);
        var hit = map.hasFeatureAtPixel(pixel);
        map.getTarget().style.cursor = hit ? 'pointer' : '';
      });

    </script>
  </body>
</html>