'use strict';

class PostIt extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        $(function() {
            $(".draggable").draggable({
                containment: $("#post-it-container")
            });
        });
    }

    render() {
        return (
            <div className="draggable postit" id={this.props.idname} style={
                {
                    top: this.props.ypos + "%",
                    left: this.props.xpos + "%",
                    position: "absolute"
                }
            }>
                <div className="handle">
                    <button type="button"  className="close_postit" onClick={() => myclose(this)}>
                        &times;
                    </button>
                </div>
                <textarea defaultValue={this.props.text} className="postit-text"/>
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
                    <PostIt text={l.text} xpos={l.xpos} ypos={l.ypos} idname={l.idname} key={l.idname}/>)}
            </div>
        );
    }
}




var new_post_it = function () {
    var m_idname = "post-it_" + new Date().getTime();


};

var render_post_its = function(all_post_its){

    var parsed = JSON.parse(all_post_its);
    ReactDOM.render(<PostIts notes={parsed.notes}/>, document.getElementById("thing2"));
    // console.log(m_idname);
};

var json_str = '{' +
    '"notes": [' +
    '{' +
    '"text": "abc123",' +
    '"xpos": 20, ' +
    '"ypos": 20, ' +
    '"idname": "idname1"'+
    '},' +
    '{' +
    '"text": "abc123",' +
    '"xpos": 20, ' +
    '"ypos": 20, ' +
    '"idname": "idname2"'+
    '}' +
    ']' +
    '}';

render_post_its(json_str)
console.log(json_str);


