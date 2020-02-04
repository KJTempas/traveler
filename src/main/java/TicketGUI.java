import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    private JTable eventsTable;
    private DefaultTableModel tableModel;
    private Vector columnNames;
    private JButton submitButton;
    private JList<String> eventList;
    //private Manager manager;
    private DefaultListModel<String> listModel;


    //hashMap of cities and dmaID (which is used in query)
    Map<String, String> cityAndDmaID = new HashMap<>();

    public TicketGUI() {
        listModel = new DefaultListModel<>();
        eventList.setModel(listModel);
        eventList.setVisibleRowCount(20);
        //setting up combo box for user selection of city
        String[] cities = {"Boston", "Chicago", "Denver", "Kansas City", "Minneapolis/St.Paul",};
        DefaultComboBoxModel<String> cityModel = new DefaultComboBoxModel<>(cities);
        cityChoices.setModel(cityModel);

        //configureTableAtStart();
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
                System.out.println(city);  //works
                //get value(dmaID) associated with that city from HashMap
                String dma = cityAndDmaID.get(city);
                System.out.println(dma); //works
                /*API api = new API(); //one way of doing it
                List<Event> events = api.getEventsPerCity(dma);*/
                //send the dma to the API method and get back a list of embedded events
                List<Embedded.Event> events = API.getEventsPerCity(dma);  //this is not getting to the API method
                //send events list to method to convert it to vectors for display in the JTable
                System.out.println("events back from API" + events);//vector of objects prints
                //Vector vectors = convertEventListToVector(events);
                List<String> eventList = convertEventListToVector(events);
                //System.out.println("vectors after conversion from eventList" + event);  //prints vector
                System.out.println("list after conversion" + eventList);
                for (String event : eventList) {
                    listModel.addElement(event);
                }
                //updateEventsTable(vectors);
                //System.out.println(vectors.size()); //prints a number like 20
                //int length = vectors.size();
                // for(int i=0; i<vectors.size(); i++) {
                //   listModel.addElement(vectors[i]); //error -array type expected; found Vector
            }
            //for(int i=0; i<vectors.length, i++) { //length not recognized
            //for(String event: vectors) {    //error-required object, found string
            // System.out.println(event);
            //listModel.addElement(event);
            // }


        });
    }


    private List<String> convertEventListToVector(List<Embedded.Event> events) {
        //Vector data = new Vector();
        List<String> eventList = new ArrayList<>();
        Vector test = new Vector();
        for (Embedded.Event e : events) {  //loop through events, and get name; add to the new vector v
            //data.add(e.getName());
            eventList.add(e.getName());

        }

        //System.out.println("Example vector"  + data);  //prints a vector
        //return data;
        System.out.println(eventList);
        return eventList;

    }
}

           /* Vector getColumnNames () {
            Vector colNames = new Vector();
            colNames.add("Event Name");
            colNames.add("Venue Name");
            columnNames = colNames;

            return columnNames;
        }*/
/*
        protected void configureTableAtStart (){
            eventsTable.setGridColor(Color.BLACK);
            eventsTable.setAutoCreateRowSorter(true);  //enables sorting
            getColumnNames();
            System.out.println(columnNames); //they do print
            Vector data = new Vector();  //to hold response from API call

            tableModel = new DefaultTableModel(data, columnNames) {

                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;    //cells are not editable
                }
            };
            eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            eventsTable.setModel(tableModel);    //column names are now showing up when gui is loaded
        }

        private void updateEventsTable (Vector vectors){
            //data should come back from the API call
            tableModel.setDataVector(vectors, columnNames);
            System.out.println("vector  ?? " + tableModel.getDataVector());
        }
    }
*/


