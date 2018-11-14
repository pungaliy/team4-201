var socket;
function connectToChoreSocket() {
    console.log("hello");
    socket = new WebSocket("ws://localhost:8080/sockets/chore");
    socket.onmessage = function(event) {
        var p = JSON.parse(event.data);
        console.log(p);
        displayMyChores(p.mychores);
        displayChoreRotation(p.allchores);
        displayShameWall(p.shamedchores);
        componentHandler.upgradeDom();
    }
}

function displayMyChores(c) {
    var list = document.getElementById("my_chores");
    list.innerHTML = "";
    for(var i = 0; i < c.length; i++) {
        var checkID = c[i].choreID+'check';
        list.innerHTML +=
            '<li class="mdl-list__item">' +
                '<span class="mdl-list__item-primary-content">' +
                    c[i].choreDescription +
                '</span>' +
                '<span class="mdl-list__item-secondary-action">' +
                    '<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="'+checkID+'">' +
                        '<input type="checkbox" id="'+checkID+'" class="mdl-checkbox__input"  />' +
                    '</label>' +
                '</span>' +
            '</li>';
    }
    if(c.length == 0) {
        list.innerHTML +=
            '<li class="mdl-list__item">' +
                '<span class="mdl-list__item-primary-content">' +
                    'There are no chores currently assigned to you.' +
                '</span>' +
            '</li>';
    }
}

function displayChoreRotation(c) {
    var table = document.getElementById("chore_rotation");
    table.innerHTML = "";
    for(var i = 0; i < c.length; i++) {
        var due = (new Date(c[i].rotationTime+c[i].rotationPeriod)).toString().substring(0,21);
        table.innerHTML +=
            '<tr>' +
                '<td class="mdl-data-table__cell--non-numeric">'+c[i].choreDescription+'</td>' +
                '<td>'+c[i].currentUser.fullName+'</td>' +
                '<td>'+due+'</td>' +
            '</tr>';
    }
    if(c.length == 0) {
        list.innerHTML +=
            '<p>' +
                "Your room doesn't have chores yet. Click the ADD A CHORE button to start adding chores." +
            '</p>';
    }
}

function displayShameWall(c) {
    var list = document.getElementById("shame_wall");
    list.innerHTML = "";
    for(var i = 0; i < c.length; i++) {
        list.innerHTML +=
            '<li class="mdl-list__item mdl-list__item--two-line">' +
                '<span class="mdl-list__item-primary-content">' +
                    '<i class="material-icons mdl-list__item-avatar">person</i>' +
                    '<span>'+c[i].currentUser.fullName+'</span>' +
                    '<span class="mdl-list__item-sub-title">Didn\'t '+c[i].choreDescription.toLowerCase()+'</span>' +
                '</span>' +
            '</li>';
    }
    if(c.length == 0) {
        list.innerHTML +=
            '<li class="mdl-list__item mdl-list__item--two-line">' +
                '<span class="mdl-list__item-primary-content">' +
                    '<span>Everyone has completed their chores on time.</span>' +
                '</span>' +
            '</li>';
    }
}