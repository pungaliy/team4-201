class PostIt extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        $(function() {
            $(".draggable").draggable({
                containment: $("#post-it-container"),
                stop: function() {
                    first_update_note(this)
                },
                stack: "div",
                onblur: function() {
                    $(this).css("z-index" , 11);
                }
            })
        });
    }

    render() {
        return (
            <div className="draggable postit" id={this.props.idname} style={
                {
                    top: this.props.ypos + "%",
                    left: this.props.xpos + "%",
                    position: "absolute"
                }}>
                <div className="handle">
                    <button type="button"  className="close_postit" onClick={() => close_this(this)}>
                        &times;
                    </button>
                </div>
                <textarea onBlur={() => update_note($("#" + this.props.idname))} defaultValue={this.props.text} className="postit-text"/>
            </div>
        );
    }
}

class PostIts extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="thing">
                {this.props.notes.map((l) =>
                    <PostIt text={l.text} xpos={l.xpos} ypos={l.ypos} idname={l.noteid} key={l.noteid}/>)}
            </div>
        );
    }
}

var update_note = function(value) {
    first_update_note($(value))
    broadcastChange();
};

var first_update_note = function(val) {
    console.log(val);
    console.log($(val).position());
    console.log($(val).find('textarea').val());
    var xpos = $(val).position().left / $("#post-it-container").width() * 100;
    var ypos = $(val).position().top / $("#post-it-container").height() * 100;
    $.ajax({
        url: '/update_postit',
        method: 'POST',
        data : {
            xpos : xpos,
            ypos:  ypos,
            text: $(val).find("textarea").val(),
            idname: $(val).attr('id')
        },
        success: function() {
            broadcastChange();
        }
    });
};

var close_this = function (self) {
    $.ajax({
        url : '/delete_postit',
        method: 'POST',
        data : {
            idname: self.props.idname
        },
        success: function() {
            render_post_its();
            broadcastChange();
        }
    });
};

var new_post_it = function () {
    let m_idname = "post-it_" + new Date().getTime();
    $.ajax({
        url : '/add_postit',
        method: 'POST',
        data : {
            xpos : 0,
            ypos: 0,
            text: "",
            idname: m_idname
        },
        success: function() {
            render_post_its();
            broadcastChange();
        }
    });
};

var render_these_post_its = function(all_post_its){
    var node = document.getElementById("thing2");
    ReactDOM.unmountComponentAtNode(node);
    var parsed = JSON.parse(all_post_its);
    ReactDOM.render(<PostIts notes={parsed}/>, node);
};

var render_post_its = function() {
    //Call on change
    console.log("rendering...");
    $.ajax({
        url : '/message-board',
        method: 'POST',
        data: {},
        success: function(responseText) {
            render_these_post_its(responseText)
        }
    });
};

$(window).resize(function() {
        console.log("resizing...");
        render_post_its();
    }
);

var socket;
var connectToMessageSocket = function() {
    console.log("connecting...")
    $.ajax({
        url: '/get-user',
        method: 'POST',
        success: function(responseText) {
            console.log(responseText);
            socket = new WebSocket("ws://localhost:8080/sockets/message");
            socket.onopen = function(event) {
                socket.send(responseText);
            };
            socket.onmessage = function(event) {
                render_post_its();
            };
            socket.onclose = function(event) {
                connectToMessageSocket();
            };
        }
    });
};

var broadcastChange = function() {
    socket.send("ping");
};

render_post_its();
connectToMessageSocket();


