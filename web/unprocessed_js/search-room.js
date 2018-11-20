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
            window.alert(responseText);
        }
    });
    return false;
}