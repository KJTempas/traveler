public class Events {
    String name;
    String type;
    String id;
    //String dates

    //EmbeddedVenue _embedded;

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +   //why all the " and '????
                "type='" + type +
                ", id='" + id + '\'' +
               // ", _embedded=" + _embedded +  //this should get me to Venues class
                '}';
    }

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
}

