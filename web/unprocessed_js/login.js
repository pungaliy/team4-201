window.onbeforeunload = function(e){
    gapi.auth2.getAuthInstance().signOut();
};

function onSignInHI(googleUser) {
  setTimeout(send_stuff(googleUser), 1000);
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
            console.log(response);
            setTimeout(function() {window.location = "http://localhost:8080/" + response}, 500);
        }
    });
}