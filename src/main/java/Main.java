import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

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

        //String ticketMasterURL = "https://app.ticketmaster.com/discovery/v2/events.json?classificationName=music&dmaId=324&apikey=ticketMasterKey";  //root URL -maybe need more?
        int location = 336; //Mpls/StP
        String play = "play";
        //String query = "https://app.ticketmaster.com/discovery/v2/events.json?apikey="+ ticketMasterAPIKey + "&dmaID="+ location; //this info from GUI
//long form of query
        String query = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=YKVl3ivkvXGB6wB0m418jXOrKGazQwFS&dmaID=336";

//example query from ticketmaster

        String baseURL = "https://app.ticketmaster.com/discovery/v2/events.json";
        //creating a map of query parameters and values
        Map<String, Object> params =  new HashMap<>();  //unirest needs string,object
        params.put("APIKey", ticketMasterAPIKey);
        params.put("city", location);
        //params.put("keyword", play);


        //get response from URL
        //ticketMasterResponse response = Unirest.get(ticketMasterURL)
        //get classes from pojko.sodhanalibrary.com   paste in result of query, and it will generate classes
        //which you can copy/paste into Java- if there aren't too many
        //System.out.println(url);  //
        TicketMasterResponse response = Unirest.get(baseURL)
        //System.out.println(url);
                //TicketMasterResponse response = Unirest.get(query)     //test
                .queryString(params)
                .asObject(TicketMasterResponse.class)
                .getBody();

        System.out.println(response);

//create an array to hold the responses (event objects)
     /*   Event [] events = response.events;
        //loop through events that are returned from the query
        for (Event e : Events) {
            System.out.println(e.name + e.location   etc);
            //print response in JTable - need to set up Jtable
        }
*/

        //turn the raw data (ticketMasterResponse) into an Event object

        // use this to get details about an event --	/discovery/v2/attractions/{id}
///discovery/v2/events -- use this and filter results by location, date, availability
        //query parameters: id, keyword, attractionID, city, state Code, localStartDateTime, localStartEndDateTime, genreID,
        // includeSpellCheck, locale(ISO format), classificationName, sort, venueID

//playGUIAct1.getColumnNames();
//guiAct1.configureTableAtStart();



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
    }





    }

