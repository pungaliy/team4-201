var socket;
function connectToChoreSocket() {
    console.log("connecting...")
    $.ajax({
        url: '/get-user',
        method: 'POST',
        success: function(responseText) {
            console.log(responseText);
            socket = new WebSocket("ws://localhost:8080/sockets/chore");
            socket.onopen = function(event) {
                socket.send("init"+responseText);
            };
            socket.onmessage = function(event) {
                displayJSONPackage(event.data);
            };
            socket.onclose = function(event) {
                connectToChoreSocket();
            };
        }
    });
}

function completeChore(c) {
    console.log(c.id, c.checked);
    var id = c.id;
    var checked = c.checked ? "t" : "f";
    socket.send("comp"+checked+id);
}

function addChore(form) {
    console.log(form.description.value);
    document.getElementById("rotation_period_error").innerHTML = "";
    $.ajax({
        url: '/add-chore',
        method: 'POST',
        data: {
            description : form.description.value,
            rotation_period : form.rotation_period.value
        },
        success: function(responseText) {
            console.log(responseText);
            if(responseText == "added") {
                console.log("successfully added chore");
                socket.send("update")
            } else {
                console.log("SHIT");
                document.getElementById("rotation_period_error").innerHTML = responseText;
            }
        }
    });
    return false;
}

function displayJSONPackage(json) {
    var p = JSON.parse(json);
    console.log(p);
    displayMyChores(p.mychores);
    displayChoreRotation(p.allchores);
    displayShameWall(p.shamedchores);
    componentHandler.upgradeDom();
}

function displayMyChores(c) {
    var list = document.getElementById("my_chores");
    list.innerHTML = "";
    for(var i = 0; i < c.length; i++) {
        var checkID = c[i].choreID;
        var checked = c[i].completed ? "checked" : "";
        list.innerHTML +=
            '<li class="mdl-list__item">' +
                '<span class="mdl-list__item-primary-content">' +
                    c[i].choreDescription +
                '</span>' +
                '<span class="mdl-list__item-secondary-action">' +
                    '<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="'+checkID+'">' +
                        '<input type="checkbox" id="'+checkID+'" class="mdl-checkbox__input" onclick="completeChore(this)" '+checked+' />' +
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
        table.innerHTML +=
            '<p>' +
                "Your room doesn't have chores yet. Click the ADD A CHORE button to start adding chores." +
            '</p>';
    }
}

function displayShameWall(c) {
    var list = document.getElementById("shame_wall");
    list.innerHTML = "";
    for(var i = 0; i < c.length; i++) {
        var pu = c[i].previousUsers;
        var u = pu[pu.length - 1];
        list.innerHTML +=
            '<li class="mdl-list__item mdl-list__item--two-line">' +
                '<span class="mdl-list__item-primary-content">' +
                    '<img src="'+u.imgURL+'" class="material-icons mdl-list__item-avatar" />' +
                    '<span>'+u.fullName+'</span>' +
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

connectToChoreSocket();