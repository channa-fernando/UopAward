function initMap() {
    // Replace latitude and longitude with your location
    var myLatLng = { lat: 37.7749, lng: -122.4194 };

    var map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 15
    });

    var marker = new google.maps.Marker({
        map: map,
        position: myLatLng,
        title: 'Our Office'
    });
}
