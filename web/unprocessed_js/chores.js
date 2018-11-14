var socket;
function connectToChoreSocket() {
    socket = new WebSocket("ws://localhost:8080/sockets/chore");
    socket.onmessage = function(event) {
        var p = JSON.parse(event.data);
        console.log(p);
    }
}

function displayMyChores() {
    var list = document.getElementById("my_chores");
    list.innerHTML = "";
        list.innerHTML += '<li class="mdl-list__item">' +
            '<span class="mdl-list__item-primary-content">' +
            'Take out the trash!' +
            '</span>' +
            '<span class="mdl-list__item-secondary-action">' +
            '<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="'+'chore1'+'">' +
            '<input type="checkbox" id="'+'chore1'+'" class="mdl-checkbox__input"  />' +
            '</label>' +
            '</span>' +
            '</li>'
    componentHandler.upgradeElement(list);
}