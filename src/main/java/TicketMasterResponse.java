import java.util.List;

public class TicketMasterResponse {

    //creating an embedded object
    public Embedded _embedded;

    @Override
    public String toString() {
        return "TicketMasterResponse{" +
                "_embedded=" + _embedded +
                '}';
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }
}


//inside the embedded object is another embedded object
class Embedded {
    List<Event> events;

    @Override
    public String toString() {
        return "Embedded{" +
                "events=" + events +
            //    "_embedded=" + _embeddedV +
                '}';
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    class Event {
        String name;
        String type;
        String id;
        String dates;

        //EmbeddedVenue _embedded;

        @Override
        public String toString() {
            return "Event{" +
                    "name='" + name + '\'' +   //why all the " and '????
                    "type='" + type +
                    ", id='" + id + '\'' +
                   // ", dates='" + dates + '\'' +
                   //  ", _embedded=" + _embedded +  //this should get me to Venues class
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDates() {
            return dates;
        }

        public void setDates(String dates) {
            this.dates = dates;
        }
    }


    class EmbeddedV{
        List<Event> venues;
    }


    class Venues {
        String name;
        String city;
        String state;
        String dmas;

        @Override
        public String toString() {
            return "Venue{" +
                    "name=" + name +
                    "city=" + city +
                    "state=" + state +
                    '}';

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}



