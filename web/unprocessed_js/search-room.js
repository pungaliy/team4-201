function searchRoom(query) {
    console.log("searching...");
    if(query.length != 8 && !(query.includes('@') && query.includes('.'))) {
        console.log("Invalid query");
        swal("Error", "Please input a valid 8-digit room code or user email.", "error");
    }
    else {
        $.ajax({
            url: '/search',
            method: 'POST',
            data: {
                query: query,
            },
            success: function (responseText) {
                console.log(responseText);
                if (responseText == "dne") swal("Error", "Please input a valid 8-digit room code or user email.", "error");
                else swal("Room Status:", responseText, "info");
            }
        });
    }
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