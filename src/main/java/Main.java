import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main {
    public static void main(String[] args) {
        //ticketGUI gui;
        //gui = new ticketGUI();

        ticketGUI = new ticketGUI();

        public List<Event> getEventsPerCity(String dma){


        Unirest.config().setObjectMapper(new ObjectMapper() {
            private Gson gson = new Gson();

            @Override
            public <T> T readValue(String s, Class<T> aClass) {
                return gson.fromJson(s, aClass);
            }

            @Override
            public String writeValue(Object o) {
                return gson.toJson(o);
            }
        });

        // key is stored in an environmental variable
        String ticketMasterAPIKey = System.getenv("ticketMasterAPIKey");

        String location = "336"; //Mpls/StP

        //my API key =YKVl3ivkvXGB6wB0m418jXOrKGazQwFS

        String baseURL = "https://app.ticketmaster.com/discovery/v2/events.json";
        //creating a map of query parameters and values
        Map<String, Object> params = new HashMap<>();  //unirest needs string,object
        params.put("apikey", ticketMasterAPIKey);
        params.put("dmaID", location);
        //params.put("city", location);
        //params.put("keyword", play);


        //get classes from pojko.sodhanalibrary.com   paste in result of query, and it will generate classes
        //which you can copy/paste into Java- if there aren't too many

        TicketMasterResponse response = Unirest.get(baseURL)
                .queryString(params)
                .asObject(TicketMasterResponse.class)
                .getBody();

        System.out.println(response);
        System.out.println(ticketMasterAPIKey);

    }
    }

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
                    '}';
        }

        public List<Event> getEvents() {
            return events;
        }

        public void setEvents(List<Event> events) {
            this.events = events;
        }
    }
 //class _embedded {

 //}




    }

