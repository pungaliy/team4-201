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

function searchRoom(query) {
    console.log("searching...");
    $.ajax({
        url: '/search',
        method: 'POST',
        data: {
            query : query,
        },
        success: function(responseText) {
            // MDCDialog("Room "+query+" Status:", responseText);
            // componentHandler.upgradeDom();
            // var dialog = document.querySelector('dialog');
            // var showDialogButton = document.querySelector('#show-dialog');
            // if (! dialog.showModal) {
            //     dialogPolyfill.registerDialog(dialog);
            // }
            console.log(responseText);
            window.alert(responseText);
        }
    });
    return false;
}

// function MDCDialog(title, body) {
//     document.body.innerHTML += '<div className="mdc-dialog" role="alertdialog" aria-modal="true" aria-labelledby="my-dialog-title" aria-describedby="my-dialog-content">  <div className="mdc-dialog__container">  <div className="mdc-dialog__surface"> <h2 className="mdc-dialog__title" id="my-dialog-title">'+title+'</h2> <div className="mdc-dialog__content" id="my-dialog-content">'+body+'</div> <footer className="mdc-dialog__actions"> <button type="button" className="mdc-button mdc-dialog__button" data-mdc-dialog-action="no">Ok</button> </footer> </div> </div> <div className="mdc-dialog__scrim"></div> </div>'
//
// }