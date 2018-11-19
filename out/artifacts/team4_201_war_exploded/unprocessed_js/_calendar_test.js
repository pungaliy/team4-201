
var Calendar = require("react-big-calendar");
var moment = require("moment");

// Setup the localizer by providing the moment (or globalize) Object
// to the correct localizer.
const localizer = Calendar.momentLocalizer(moment); // or globalizeLocalizer

class MyCalendar extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
                <Calendar
                    localizer={localizer}
                    events={myEventsList}
                    startAccessor="start"
                    endAccessor="end"
                />
        );
    }
}

const element = document.getElementById("root");
ReactDOM.render(<MyCalendar/>, element);