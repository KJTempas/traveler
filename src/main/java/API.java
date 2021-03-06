import com.google.gson.Gson;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API {


        public static List<Embedded.Event> getEventsPerCity(String dma, String keyword){

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


            String baseURL = "https://app.ticketmaster.com/discovery/v2/events.json";
            //creating a map of query parameters and values
            Map<String, Object> params = new HashMap<>();  //unirest needs string,object
            params.put("apikey", ticketMasterAPIKey);
            params.put("dmaId", dma);
            params.put("keyword", keyword);


            //get classes from pojko.sodhanalibrary.com   paste in result of query, and it will generate classes
            //which you can copy/paste into Java- if there aren't too many

            TicketMasterResponse response = Unirest.get(baseURL)
                    .queryString(params)
                    .asObject(TicketMasterResponse.class)
                    .getBody();

            System.out.println(response);

            return response.get_embedded().getEvents();

        }
    }


