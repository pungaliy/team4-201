function searchRoom(query) {
    console.log("searching...");
    $.ajax({
        url: '/search',
        method: 'POST',
        data: {
            query : query,
        },
        success: function(responseText) {
            console.log(responseText);
            if(responseText == "!=8") swal("Error", "Room code's must be exactly 8 digits long.", "error");
            else if(responseText == "dne") swal("Error", "That room code does not exist.", "error");
            else swal("Room "+query+"'s Status:", responseText, "info");
        }
    });
    return false;
}

function mySignOut() {
    $.ajax({
                url: "/logout",
                method: "post",
                success: function (response) {
                    console.log(response);
                    window.location.reload();
                }
            });
    // if(!gapi.auth2) {
    //     $.ajax({
    //         url: "/logout",
    //         method: "post",
    //         success: function (response) {
    //             console.log(response);
    //             setTimeout(function() {window.location.reload();}, 500);
    //         }
    //     });
    // }
    // else {
    //     var auth2 = gapi.auth2.getAuthInstance();
    //     auth2.signOut().then(function () {
    //         console.log('User signed out.');
    //     });
    //     gapi.auth2.getAuthInstance().disconnect();
    //
    //     if (!gapi.auth2) {
    //         gapi.load('auth2', function () {
    //             gapi.auth2.init();
    //         });
    //     }
    //
    //
    // }
}