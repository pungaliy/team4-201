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

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/login", true);

    xhttp.onreadystatechange = function () {
        console.log(xhttp.responseText);
        if (xhttp.responseText === "sign_in_successful") {
            setTimeout(function() {window.location = "http://localhost:8080/profile"}, 1000)
        }

        if (xhttp.responseText === "invalid_signin"){
            window.alert("Sorry, that didn't work. I'm signing you out. Please try again");
        }
    };

    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("access_token=" + googleUser.getAuthResponse().access_token
        + "&user_id=" + googleUser.getBasicProfile().getId()
        + "&image=" + googleUser.getBasicProfile().getImageUrl()
        + "&first_name=" + googleUser.getBasicProfile().getName()
        + "&email=" + googleUser.getBasicProfile().getEmail()
    );
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