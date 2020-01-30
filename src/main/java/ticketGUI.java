import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ticketGUI extends JFrame {
    private JLabel topLabel;
    private JComboBox<String> cityChoices;
    private JLabel cityName;
    private JLabel startDate;
    private JPanel mainPanel;
    //need two date spinners - one for first date/ one for last date?
    private JSpinner startDateSpinner;

    private JTable eventsTable;
    private DefaultTableModel tableModel;
    private Vector columnNames;
    private JButton submitButton;
    //private Manager manager;



//hashMap of cities and dmaID (which is used in query)
    Map<String, String> cityAndDmaID = new HashMap<>();

    public ticketGUI() {
        //setting up combo box for user selection of city
        String[] cities = {"Boston", "Chicago", "Denver", "Kansas City", "Minneapolis/St.Paul",};
        DefaultComboBoxModel<String> cityModel = new DefaultComboBoxModel<>(cities);
        cityChoices.setModel(cityModel);

        setHashMap();
        addListeners();
        setTitle("Find events in the city");
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
                System.out.println(city);
                //get value(dmaID) associated with that city from HashMap
                String dma = cityAndDmaID.get(city);
                //send the dma to the API in Main class
                Main.getEventsPerCity(dma);

            }

        });
    }

        Vector getColumnNames () {
            Vector colNames = new Vector();
            colNames.add("Event Name");
            colNames.add("Venue Name");

            return colNames;
        }

        protected void configureTableAtStart (Vector colNames){
            eventsTable.setGridColor(Color.BLACK);
            eventsTable.setAutoCreateRowSorter(true);  //enables sorting
            getColumnNames();
            Vector data = new Vector();
            tableModel = new DefaultTableModel(data, colNames) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;    //cells are not editable
                }
            };
            eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            eventsTable.setModel(tableModel);

        }

        private void updateEventsTable (Vector data){
            //data should come back from the API call
            tableModel.setDataVector(data, columnNames);
        }
    }



//These date spinners will let user pick any date.
        /*startDateSpinner.setModel(new SpinnerDateModel());
        endDateSpinner.setModel(new SpinnerDateModel());

        configureDateSpinner();   //to set up date spinner for GUI

    }


        Date startDate = (Date) startDateSpinner.getModel().getValue();
        Date endDate = (Date) endDateSpinner.getModel().getValue();


        //make an object to send to the API?
    }





}

    protected void configureDateSpinner() {
//from https://www.tutorialspoint.com/how-to-create-a-date-spinner-in-java
        //above creates 2 spinners; I think this part is for the date part only
      /*  Date today = new Date();                            //i, min, max, step
        JSpinner spinner = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yy");
        spinner.setEditor(editor);*/

        //from Clara
//This date spinner restricts the range of dates
        //Some random dates... Pick a date, between March 10th, 2010 and June 5th, 2015
/*
        Calendar earliestDate = Calendar.getInstance();
        //make it now??  or how to prevent user from selecting date in past
        earliestDate.set(2020, Calendar.JANUARY, 10);//Note this is Year, Month, Day

    }
*/