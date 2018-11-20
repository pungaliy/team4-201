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