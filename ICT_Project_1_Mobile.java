package ict_timetable_saheti;

import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class ICT_TimeTable_Saheti {

    private static TrayIcon trayIcon;
    private static JFrame timetableFrame;
    
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "This Application will alert you of your next subject", "INFO",
                JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "We will start by entering your subject data");

        // GUI
        JTextField O4 = new JTextField(10);
        JTextField O3 = new JTextField(10);
        JTextField O2 = new JTextField(10);
        JTextField Grade = new JTextField(10);
        JTextField HomeTeacher = new JTextField(10);
        JTextField CurrentYear = new JTextField(10);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Option 4: "));
        panel.add(O4);
        panel.add(new JLabel("Option 3: "));
        panel.add(O3);
        panel.add(new JLabel("Option 2: "));
        panel.add(O2);
        panel.add(new JLabel("Grade: "));
        panel.add(Grade);
        panel.add(new JLabel("Home Room Teacher: "));
        panel.add(HomeTeacher);
        panel.add(new JLabel("Current Year: "));
        panel.add(CurrentYear);

        // Data Received
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Subject Data", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            //School Data
            String option4 = O4.getText();
            String option3 = O3.getText();
            String option2 = O2.getText();
            int grade = Integer.parseInt(Grade.getText());
            String homeTeacher = HomeTeacher.getText();
            int currentYear = Integer.parseInt(CurrentYear.getText());
            int currentYearNow = Year.now().getValue();
            //Time Data
            java.util.Calendar cal = java.util.Calendar.getInstance();
            int currentDay = cal.get(java.util.Calendar.DAY_OF_WEEK);
            int currentHour = cal.get(java.util.Calendar.HOUR_OF_DAY);
            int currentMinute = cal.get(java.util.Calendar.MINUTE);

            // Update Grade and Year Annually
            if (currentYearNow > currentYear) {
                currentYearNow++;
                grade++;
            }

            // Option Corrections For Grade 10
            if (grade == 10 && !(option4.equalsIgnoreCase("Business") || option4.equalsIgnoreCase("IT")
                    || option4.equalsIgnoreCase("Physics") || option4.equalsIgnoreCase("Art")
                    || option4.equalsIgnoreCase("Biology") || option4.equalsIgnoreCase("History")
                    || option4.equalsIgnoreCase("Drama"))) {
                showErrorDialog();
            }
            if (grade == 10 && !(option3.equalsIgnoreCase("Physics") || option3.equalsIgnoreCase("Accounting")
                    || option3.equalsIgnoreCase("Biology") || option3.equalsIgnoreCase("CAT")
                    || option3.equalsIgnoreCase("Geography"))) {
                showErrorDialog();
            }
            if (grade == 10 && !(option2.equalsIgnoreCase("Physics") || option2.equalsIgnoreCase("Business")
                    || option2.equalsIgnoreCase("IT") || option2.equalsIgnoreCase("Consumer")
                    || option2.equalsIgnoreCase("History"))) {
                showErrorDialog();
            }

            // Option Corrections For Grade 11
            if (grade == 11 && !(option4.equalsIgnoreCase("Physics") || option4.equalsIgnoreCase("Art")
                    || option4.equalsIgnoreCase("Business") || option4.equalsIgnoreCase("Biology")
                    || option4.equalsIgnoreCase("IT") || option4.equalsIgnoreCase("History")
                    || option4.equalsIgnoreCase("CAT") || option4.equalsIgnoreCase("Drama"))) {
                showErrorDialog();
            }
            if (grade == 11 && !(option3.equalsIgnoreCase("Business") || option3.equalsIgnoreCase("Accounting")
                    || option3.equalsIgnoreCase("Art") || option3.equalsIgnoreCase("CAT")
                    || option3.equalsIgnoreCase("Geography") || option3.equalsIgnoreCase("Drama"))) {
                showErrorDialog();
            }
            if (grade == 11 && !(option2.equalsIgnoreCase("Physics") || option2.equalsIgnoreCase("Business")
                    || option2.equalsIgnoreCase("IT") || option2.equalsIgnoreCase("Consumer")
                    || option2.equalsIgnoreCase("History") || option2.equalsIgnoreCase("Art"))) {
                showErrorDialog();
            }

            // Option Corrections For Grade 12
            if (grade == 12 && !(option4.equalsIgnoreCase("Business") || option4.equalsIgnoreCase("IT")
                    || option4.equalsIgnoreCase("Physics") || option4.equalsIgnoreCase("Art")
                    || option4.equalsIgnoreCase("Biology") || option4.equalsIgnoreCase("History")
                    || option4.equalsIgnoreCase("Drama") || option4.equalsIgnoreCase("AP Maths")
                    || option4.equalsIgnoreCase("Chemistry"))) {
                showErrorDialog();
            }
            if (grade == 12 && !(option3.equalsIgnoreCase("Physics") || option3.equalsIgnoreCase("Accounting")
                    || option3.equalsIgnoreCase("Biology") || option3.equalsIgnoreCase("CAT")
                    || option3.equalsIgnoreCase("Geography") || option3.equalsIgnoreCase("Drama")
                    || option3.equalsIgnoreCase("AP Maths") || option3.equalsIgnoreCase("Consumer"))) {
                showErrorDialog();
            }
            if (grade == 12 && !(option2.equalsIgnoreCase("Physics") || option2.equalsIgnoreCase("Business")
                    || option2.equalsIgnoreCase("IT") || option2.equalsIgnoreCase("Consumer")
                    || option2.equalsIgnoreCase("History") || option2.equalsIgnoreCase("Art")
                    || option2.equalsIgnoreCase("Geography") || option2.equalsIgnoreCase("Chemistry"))) {
                showErrorDialog();
            }

            String timetablePath = "C:\\Users\\krampursat\\Desktop\\ICT_JProject_1\\TimeTables\\" + grade + homeTeacher + ".png";

            // Load timetable
            String timetableData = loadTimetable(timetablePath);

            // Display timetable
            timetableFrame = new JFrame("Saheti Time Table");
            timetableFrame.setIconImage(
                    Toolkit.getDefaultToolkit().getImage("C:\\Users\\krampursat\\Desktop\\ICT_JProject_1\\Ico\\download.png"));
            timetableFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            timetableFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    minimizeToTray();
                }
            });
            timetableFrame.setSize(960, 1080);
            JLabel timetableLabel = new JLabel();
            timetableLabel.setIcon(new ImageIcon(timetablePath));
            JScrollPane scrollPane = new JScrollPane(timetableLabel);
            timetableFrame.getContentPane().add(scrollPane);
            timetableFrame.setVisible(true);

            // Check for next subject
            String nextSubject = getNextSubject(timetableData, currentHour, currentMinute);
            if (nextSubject != null) {
                JOptionPane.showMessageDialog(timetableFrame, "Your next subject is: " + nextSubject, "Next Subject",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            //TODO CODE APPLICATION HERE
            while (true) {
                //Variables 
                LocalDate CurrentDate = LocalDate.now();
                DateTimeFormatter Format = DateTimeFormatter.ofPattern("E");
                String currentDayAbv = CurrentDate.format(Format);

                //Grade 10 MH Alerts Mon
                if (homeTeacher.equalsIgnoreCase("MH") && grade == 10) {
                    if (currentHour == 7 && currentMinute == 50) {
                        JOptionPane.showMessageDialog(null, "School Started");
                    } else {

                    }
                    if (currentDay == java.util.Calendar.MONDAY) {

                        if (currentHour == 8 && currentMinute == 20) {
                            JOptionPane.showMessageDialog(null, "You Have " + option4 + " Next! Enjoy", "", JOptionPane.WARNING_MESSAGE);
                        } else {

                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Have Maths Next, Study Hard");
                        } else {

                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Now Have Break, Enjoy");
                        } else {

                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Break Is Over You Now Have Greek Studies");
                        } else {

                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have Life Orientation Next");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have your 2nd Break, Enjoy!");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Your 2nd Break Is Over You Now Have " + option3 + ", Enjoy!");
                        } else {

                        }
                        if (currentHour == 13 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have " + option2 + ", Enjoy!");
                        } else {

                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Can Go Home Now Unless You Have Sports Or Other Lessons Planned");
                        } else {

                        }
                    } else {

                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Grade 10 MH Alerts Tue
                    if (currentDay == java.util.Calendar.TUESDAY) {
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Have " + option3 + " Up Next, Enjoy!");
                        } else {

                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Have " + option4 + " Next, Enjoy!");
                        } else {

                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Can Relax And Have Your Break Now");
                        } else {

                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have Your Secondary Language Now, Either Afrikaans Or Zulu");
                        } else {

                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Have Mathematics Next, Study Hard!");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have Your Second Break, Have Fun");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "You Now Have " + option2 + " Work Hard!");
                        } else {

                        }
                        if (currentHour == 13 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have English, Study Well");
                        } else {

                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "You Now Have Greek Studies, Work Hard");
                        } else {

                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Schoool Is Now Out For The Day, Unless Anything Else Is Planned");
                        } else {

                        }
                    } else {

                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Grade 10 MH Alerts Wed
                    if (currentDay == java.util.Calendar.WEDNESDAY) {
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day With Languages, Go To Afrikaans or Zulu Respectivly");
                        } else {

                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Two Language Classes In A Row, Wow, Head To English");
                        } else {

                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Can Enjoy Break Now, You've Probably Earned It...Right");
                        } else {

                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have " + option4 + ", Have Fun!");
                        } else {

                        }
                        if (currentHour == 10 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "you Now Have " + option3 + ", Learn Lots!");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Have You Second Break Of The Day, Enjoy!");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Your Second Break Is Over, You Now Have " + option2 + ", Have Fun");
                        } else {

                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "School Is Out For The Day, Have A Wonderfull Rest Of Your Afternoon");
                        } else {

                        }
                    } else {

                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Grade 10 MH Alerts Thu
                    if (currentDay == java.util.Calendar.THURSDAY) {
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Head Over To you Greek Studies To Start The Day");
                        } else {

                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Now You Will be Sweating Bullets Right Before Break, head Over To PE");
                        } else {

                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Probably Wont See This As You're Most Likely Doing PE, But You Can Take Your 1st Break");
                        } else {

                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Have English Now, Enjoy!");
                        } else {

                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Your Next Class Is Afrikaans or Zulu, Respectively Of Course");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Can Take Another Break, Something I Should Have Done Whilst Making This App");
                        } else {

                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "You Now Have Your " + option3 + " To Go To Next");
                        } else {

                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "You Now Have Maths As Your Last Lesson");
                        } else {

                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "School Is Out For The Day, You Know, If You Have Anything Else Good Luck");
                        } else {

                        }
                    } else {

                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Grade 10 MH Alerts Fri
                    if (currentDay == java.util.Calendar.FRIDAY) {
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Start The Day With, " + option4);
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Your Next Class Is " + option2 + ", Enjoy And Have Fun!");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Can Take Your 1st Break Of The Day");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Your Next Class Is Greek Studies, Have Fun");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have Maths As Your Next Class");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Cannot Take Your Second Break\nthe amount of times I typed this is unbelievable");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "2nd Break Is Over, You Now have English for An Hour, Enjoy");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "You Now have Afrikaans Or Zulu");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Can Go Home Now, Unless You Have Anything Else To Do With SAHETI"
                                    + "\nbut...Programmer will be taking his first break in 9.5 hrs...");
                        }
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //DO NOT GO BELOW THIS BRACKET IF WORKING ON HAINS CLASS
                    //TODO CODE APPLICATION FOR GRADE 10 HAINS CLASS
                }
                //Grade 10 ZS Mon Alerts
                if (homeTeacher.equalsIgnoreCase("ZS") && grade == 10) {
                    if (currentHour == 7 && currentMinute == 40) {
                        JOptionPane.showMessageDialog(null, "School Started Get To Home Room");
                    }
                    if (currentDayAbv.toUpperCase().equalsIgnoreCase("MON")) {
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "There's An Assembly See You There");
                        }
                        if (currentHour == 8 && currentMinute == 20) {
                            JOptionPane.showMessageDialog(null, "You Have " + option4 + ", Next Learn Lots");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Have Mathematics Next, 3.141");
                        }
                        if (currentHour == 9 && currentHour == 50) {
                            JOptionPane.showMessageDialog(null, "You Have A Break Now, Enjoy!");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Your Break Is Over, Next Up, Greek. Study Hard...I Think");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Greek Is Over, Head To Your Secondary Language Class, Afrikaans Or Zulu");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Can Take Your Second Break Of The Day, Have Fun!");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Your Second Break Is Over, Head Over To Your " + option3 + " Class");
                        }
                        if (currentHour == 13 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have Your " + option2 + " Class, Learn Lots");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Schools Over For The Day, Unless You Have Other Plans");
                        }
                    }
                    //Grade 10 ZS Alerts Tue
                    if (currentDayAbv.equalsIgnoreCase("Tue")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "You Have to Head To Home Room To Start The DAy Of");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Once You've Finished Up With Your Home Room, Head Over To " + option3 + " Class");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Right, Your Next Class.... Lets See. Ah It's " + option4);
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You May Take A Break, Enjoy it , Heavy Studying Ahead");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Break's Over, Head Over To English Class");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Have Mathematics Class next Up, Study Hard...");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Have Your Second Break Of The Day, Have Lots Of Fun");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Breaks Over Scrubs, Head Over To " + option2 + " Class");
                        }
                        if (currentHour == 13 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right, " + option2 + " Is Over, Head Over To Afrikaans Or Zulu Class");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Almost Done With School, One More Class To Do, Greek");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right, Schools Over, Meaning So Are My Notis, If You Have any Extra Classes, Idc Its Not My Business");
                        }
                    }
                    //Grade 10 ZS Alerts Wed
                    if (currentDayAbv.equalsIgnoreCase("Wed")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head Over To Home Room, you Don't Want To Be Marked Absent");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Have Life Orientation Class Next, Enjoy >:)");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "ight Life Orientation Is Over, Head To Hour English Class");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Go Enjoy Break You Have Classes Ahead");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Now Have Your Options, Starting With " + option4);
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Your Next Class Is " + option3 + ", Enjoy!");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Go Enjoy Your Second Break Of The Day,\nsomething programmer should have done...");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Second Break Is Over Get Back To Your Classes, Starting With " + option2);
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Schools Over For The DAy Uness You Have Other Plans, Enjoy!");
                        }
                    }
                    //Grade 10 ZS Alerts Thu
                    if (currentDayAbv.equalsIgnoreCase("Thu")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head To Home Room, What Are You Doing Wasting Precious Time");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day Of With Greek Studies, Epic!\n...right...");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Time To Smell The Sweat Smell Of BO During Todays Sesion Of PE Class");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Go Hvae A Break Now, Deserved After A Hard PE Lesson");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Break Is Over, Head Back To Class, Specifically Aftikaans Or Zulu");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right Next Class Is Also A Language Class...English, Enjoy!!!");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Second Break!!!, Finally");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Head Over To Your Precious " + option3 + " Class");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Your Next Class Is Now Mathematics, Enjoy, Study Hard");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Schools Officially Out For Thr Day, Unless You Have Something Else To Do");
                        }
                    }
                    //Grade 10 ZS Alerts Fri
                    if (currentDayAbv.equalsIgnoreCase("Fri")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head To Home Room, You Have To Be Registered As Present For The Day");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day Of With " + option4 + ", Enjoy");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Next Up, " + option2 + " Class");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Can Now Have A Break, Enjoy");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Did You Enjoy Break, Yes?, Want More Time?, NO YOU DONT GET MORE TIME HEAD OVER TO YOUR GREEK CLASS");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right, Greek Is Over, Head Over To Maths, Study Hard");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right, Enjoy You Second Break");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Right, Breaks Over Head Over To Afrikaans Or Zulu Class");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "After Your Secondary Language Classes, head over You Your Primary Language, English");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right, Schools Out Enjoy The Weekend, unless AP Classes Are Still On Then GLHF"
                                    + "\nprogrammer is probably going to run himself dry coding again, send help");
                        }
                    }
                }
                //Grade 10 MT Alerts Mon
                if (homeTeacher.equalsIgnoreCase("MT") && grade == 10) {
                    if (currentDayAbv.equalsIgnoreCase("Mon")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Go To Home Room, Get Registered For The Day");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "There's An Assembly Early In The Morning");
                        }
                        if (currentHour == 8 && currentMinute == 20) {
                            JOptionPane.showMessageDialog(null, "The Next Class Is " + option4 + ", Enjoy!");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Your Next Class Is Mathematics, Work Hard");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You Have A Break, First Of Two");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Break Is Over, Head Over To Your Greek Class");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "From One Language Class To Another, Head Over To English Class");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Sick, You Survived Your Classes, And You've Earned A Second Break");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Second Break Is Over, Back To Class Up Next " + option3 + ", Enjoy!");
                        }
                        if (currentHour == 13 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Nice, You've Survived Your " + option3 + " Class, Up Next " + option2);
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "School's Out For The Day, If You Have Any Extra Classes, Good Luck With Them..."
                                    + "\nwho'd have though how hard it is to come up with new sentances...");
                        }
                    }
                    //Grade 10 MT Alerts Tue
                    if (currentDayAbv.equalsIgnoreCase("Tue")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head To Home Room To Be Registered For The Day");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day With A Whoping " + option3 + " Class");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Next Class Is The Wonderful " + option4 + ", Enjoy!");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "You've Earned Your First Break, Enjoy It Well");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right, First Break Is Over, Go To Your Next Class, English");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "After You've Written Until Blisters Formed On Your Fingers, Your Next Class Is Mathematics");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "After You've Racked Your Brain Around These Maths Equations, Enjoy A Nice Long 30 Min Break");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "After you've Finished Up With Your Break, head Over To " + option2);
                        }
                        if (currentHour == 13 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "After Youve Finished Woth Your " + option2 + " Class, You Now Have Afrikaans Or Zulu, Depends ofc");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "After Afrikaans, You Have Half An Hour Of Greek Studies, Work Hard");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Great, Schools Out For The Day Unless You Have After School Activities"
                                    + "Ahhh love the repitition...\n");
                        }
                    }
                    //Grade 10 MT Alerts Wed
                    if (currentDayAbv.equalsIgnoreCase("Wed")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head To Home Room to Get Started With The Day");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day Of With An epic Afrikaans Or Zulu Class!");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Time To Sweat Like Hell During This PE Class");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "When You Are Done Sweating Like A Fresh Rotisserie Chicken, Take A Break");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Break's Over Hope You Are Ready Beacuse Up Next Is " + option4 + " Class");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "After That Amazing " + option4 + " Class Its Time To Enjoy A Nice " + option3 + " Class");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "After All That Work You've Done, You Earned A Second Break");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Nice, Breaks Over Time To Learn Some " + option2);
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Great, Schools Over, Unless You Have After School Activities GLHF");
                        }
                    }
                    //Grade 10 MT Alerts Thu
                    if (currentDayAbv.equalsIgnoreCase("Thu")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head Over To Home Room To Start The Day, You Know How Ig Goes");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day With Greek Studies...");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "From One Language To Another, Head Of To English Class");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Two Languages Can Be Rough, Go Take A Break");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Breaks Over, Go To Another Langage Class, Afrikaans Or Zulu");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Nice Now That You've Done All The Language Classes SAHETI Offers Other Than AP, Go To LO Class");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Second Break Of The Day, Yay!!!");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Great, Break Is Over Head To Your " + option3 + " Class");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Epic, Ending The Day With Maths, Study Hard");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "School Is Over For The Day, Unless You Have Any Other School Activities, Gl");
                        }
                    }
                    //Grade 10 MT Alerts Fri
                    if (currentDayAbv.equalsIgnoreCase("Fri")) {
                        if (currentHour == 7 && currentMinute == 40) {
                            JOptionPane.showMessageDialog(null, "Head To Home Room To Start The Day");
                        }
                        if (currentHour == 7 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Starting The Day With " + option4 + " Class");
                        }
                        if (currentHour == 8 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Nice, Once You've Gotten Your Stuff Head Over To Your " + option2 + " Class");
                        }
                        if (currentHour == 9 && currentMinute == 50) {
                            JOptionPane.showMessageDialog(null, "Once You've Finished Up With Your " + option2 + " Class, You May Take A Break");
                        }
                        if (currentHour == 10 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Sick, Your Break Is Over, head Over To Your Greek Class");
                        }
                        if (currentHour == 11 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "After Your Greek Class, Head Over To Your Maths Class");
                        }
                        if (currentHour == 12 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "Right As the Time Shows The The Time Table That The School Gave Us, It Says You Have A Break Now");
                        }
                        if (currentHour == 12 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "So...Breaks Over And According To This You Have Afrikaans Or Zulu Class Next");
                        }
                        if (currentHour == 13 && currentMinute == 45) {
                            JOptionPane.showMessageDialog(null, "Next Class, English, Study Hard Will Ya?");
                        }
                        if (currentHour == 14 && currentMinute == 15) {
                            JOptionPane.showMessageDialog(null, "You Know The Drill, rn programmer gonna take a break, cya");
                        }
                    }
                }
            }
            //TODO CODE APPLICATION HERE OUTSIDE HAINS AREA ^^^
            //INSIDE OPTION OK
        }
        //BACK TO MAIN CLASS
    }

    private static void showErrorDialog() {
        JOptionPane.showMessageDialog(null, "Invalid subject selection for the chosen grade!", "Error",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    private static String loadTimetable(String path) {
        StringBuilder timetableData = new StringBuilder();
        try {
            java.io.File file = new java.io.File(path);
            java.util.Scanner input = new java.util.Scanner(file);
            while (input.hasNext()) {
                timetableData.append(input.nextLine()).append("\n");
            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return timetableData.toString();
    }

    private static String getNextSubject(String timetableData, int currentHour, int currentMinute) {
        String[] lines = timetableData.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            String time = parts[0].trim();
            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            if (hour > currentHour || (hour == currentHour && minute >= currentMinute)) {
                return parts[1].trim();
            }
        }
        return null;
    }

    private static void minimizeToTray() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final SystemTray tray = SystemTray.getSystemTray();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\krampursat\\Desktop\\ICT_JProject_1\\Ico\\download.png");
        trayIcon = new TrayIcon(imageIcon.getImage(), "Saheti Time Table");
        trayIcon.setImageAutoSize(true);

        trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    tray.remove(trayIcon);
                    timetableFrame.setVisible(true);
                    timetableFrame.setExtendedState(JFrame.NORMAL);
                }
            }
        });

        try {
            tray.add(trayIcon);
            timetableFrame.setVisible(false);
            JOptionPane.showMessageDialog(null, "The Application Has Been Minimized To The System Tray.", "", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    


