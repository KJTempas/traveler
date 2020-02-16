import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TicketGUI extends JFrame {
    private JLabel topLabel;
    private JComboBox<String> cityChoices;
    private JLabel cityName;
    private JPanel mainPanel;

    private JButton submitButton;
    private JList<String> eventList;
    private JComboBox<String> keywordComboBox;

    private DefaultListModel<String> listModel;


    //hashMap of cities and dmaId (which is used in query)
    Map<String, String> cityAndDmaID = new HashMap<>();

    public TicketGUI() {
        listModel = new DefaultListModel<>();
        eventList.setModel(listModel);
        eventList.setVisibleRowCount(20);
        //setting up combo box for user selection of city
        String[] cities = {"Boston", "Chicago", "Denver", "Kansas City", "Minneapolis/St.Paul",};
        DefaultComboBoxModel<String> cityModel = new DefaultComboBoxModel<>(cities);
        cityChoices.setModel(cityModel);

        String[]keywords = {"select a keyword", "sports", "theatre", "music", "play", "comedy", "festival"};
        DefaultComboBoxModel<String> keywordModel = new DefaultComboBoxModel<>(keywords);
        keywordComboBox.setModel(keywordModel);

        setHashMap();
        addListeners();

        setTitle("Find events in the city");
        setPreferredSize(new Dimension(800, 600));
        setContentPane(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    void setHashMap() {
        cityAndDmaID.put("Boston", "235");
        cityAndDmaID.put("Chicago", "249");
        cityAndDmaID.put("Denver", "264");
        cityAndDmaID.put("Kansas City", "311");
        cityAndDmaID.put("Minneapolis/St.Paul", "336");
    }


    protected void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get user input from JCombo box
                String city = (String) cityChoices.getSelectedItem();
                String keyword = (String) keywordComboBox.getSelectedItem();
                //if(keyword == "select a city") {  //doesn't work
                  //  JOptionPane.showMessageDialog(TicketGUI.this, "Select a city");
                //}
                System.out.println(city);  //works
                //get value(dmaID) associated with that city from HashMap
                String dma = cityAndDmaID.get(city);
                System.out.println(dma); //works
                /*API api = new API(); //one way of doing it
                List<Event> events = api.getEventsPerCity(dma);*/
                //send the dma to the API method and get back a list of embedded events
                List<Embedded.Event> events = API.getEventsPerCity(dma, keyword);  //this is not getting to the API method
                //send events list to method to convert it to vectors for display in the JTable
                System.out.println("events back from API" + events);//vector of objects prints
                List<String> eventList = convertEventListToVector(events);
                //System.out.println("vectors after conversion from eventList" + event);  //prints vector
                System.out.println("list after conversion" + eventList);
                for (String event : eventList) {
                    listModel.addElement(event);
                }
            }
        });
    }


    private List<String> convertEventListToVector(List<Embedded.Event> events) {

        List<String> eventList = new ArrayList<>();
        //Vector test = new Vector();
        for (Embedded.Event e : events) {  //loop through events, and get name; add to the new vector v
            eventList.add(e.getName());
            //eventList.add(e.getDates());
        }
        System.out.println(eventList);
        return eventList;

    }
}




