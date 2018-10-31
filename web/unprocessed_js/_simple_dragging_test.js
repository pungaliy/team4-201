'use strict';


class PostIt extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        $(function() {
            $(".draggable").draggable();
        });
    }

    render() {
        return (
            <div className="draggable postit" id={"post-it_" + new Date().getDate().toString()}>
                <div className="handle">
                    <button type="button" className="close_postit" onClick={() => myclose(this)}>
                        &times;
                    </button>
                </div>
                <textarea defaultValue="hello" className="postit-text"/>
            </div>
        );
    }
}


const element = document.getElementById("thing");
ReactDOM.render(<PostIt/>, element);

