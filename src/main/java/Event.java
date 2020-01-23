public class Event {
    String name;
    String type;
    String id;
    //String dates

    //EmbeddedVenue _embedded;

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                "type='" + type +
                ", id='" + id + '\'' +
               // ", _embedded=" + _embedded +
                '}';
    }


}
