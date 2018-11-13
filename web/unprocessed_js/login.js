function onSignInHI(googleUser) {
    if(googleUser.hasGrantedScopes('https://www.googleapis.com/auth/calendar'))
    {
        console.log("we have already been granted the Calendar scope!")
        send_stuff(googleUser)
    }
    else {
        googleUser.grant({'scope': 'https://www.googleapis.com/auth/calendar'})
            .then(function () {
                setTimeout(send_stuff(googleUser), 1000);
            });
    }
}

function send_stuff(googleUser) {


    $.ajax({
        url: "/login",
        method: "post",
        data: {
            "access_token": googleUser.getAuthResponse().access_token,
            "user_id": googleUser.getBasicProfile().getId(),
            "image": googleUser.getBasicProfile().getImageUrl(),
            "first_name": googleUser.getBasicProfile().getName(),
            "email": googleUser.getBasicProfile().getEmail(),
        },
        success: function (response) {
            console.log(response)
            setTimeout(function() {window.location = "http://localhost:8080/" + response}, 500);
        }
    });

    // var xhttp = new XMLHttpRequest();
    // xhttp.open("POST", "/login", true);
    //
    // xhttp.onreadystatechange = function () {
    //     console.log(response)
    //     // setTimeout(function() {window.location = "http://localhost:8080/" + response}, 500)
    // };
    //
    // xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // xhttp.send("access_token=" + googleUser.getAuthResponse().access_token
    //     + "&user_id=" + googleUser.getBasicProfile().getId()
    //     + "&image=" + googleUser.getBasicProfile().getImageUrl()
    //     + "&first_name=" + googleUser.getBasicProfile().getName()
    //     + "&email=" + googleUser.getBasicProfile().getEmail()
    // );
}

function mySignOut() {
    if(!gapi.auth2){
        gapi.load('auth2', function() {
            gapi.auth2.init();
        });
    }

    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function() {
        console.log('User signed out.');
    });
    gapi.auth2.getAuthInstance().disconnect();

}