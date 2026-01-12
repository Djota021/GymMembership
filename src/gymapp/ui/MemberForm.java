package gymapp.ui;
import java.awt.Font;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gymapp.db.DatabaseUtil;
import gymapp.db.MemberDbRepository;
import gymapp.model.Member;
import gymapp.repository.FileRepository;
import gymapp.util.SaveThread;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class MemberForm {

    public static void main(String[] args) {
    	
    	DatabaseUtil.initDatabase();
    	
        JFrame frame = new JFrame();
        frame.setTitle("Gym Membership - Đorđe Dević MIT14/25");
        frame.setSize(400, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblFirstName.setBounds(30, 30, 80, 20);
        frame.add(lblFirstName);

        JTextField txtFirstName = new JTextField();
        txtFirstName.setBounds(120, 30, 200, 22);
        frame.add(txtFirstName);
        
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblLastName.setBounds(30, 65, 80, 20);
        frame.add(lblLastName);

        JTextField txtLastName = new JTextField();
        txtLastName.setBounds(120, 65, 200, 22);
        frame.add(txtLastName);
        
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblGender.setBounds(30, 100, 80, 20);
        frame.add(lblGender);
        
        JRadioButton rbMale = new JRadioButton("Male");
        rbMale.setBounds(120, 100, 60, 22);
        rbMale.setSelected(true);
        frame.add(rbMale);

        JRadioButton rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(190, 100, 80, 22);
        frame.add(rbFemale);
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        
        JLabel lblBirthDate = new JLabel("Date of Birth:");
        lblBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblBirthDate.setBounds(30, 135, 80, 20);
        frame.add(lblBirthDate);
        
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

        datePicker.setBounds(120, 135, 200, 25);
        frame.add(datePicker);
        
        JLabel lblMembership = new JLabel("Membership:");
        lblMembership.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblMembership.setBounds(30, 175, 80, 20);
        frame.add(lblMembership);
        
        JSlider sliderFee = new JSlider(1, 12, 1);
        sliderFee.setBounds(120, 210, 200, 45);
        sliderFee.setMajorTickSpacing(1);
        sliderFee.setPaintTicks(true);
        sliderFee.setPaintLabels(true);
        frame.add(sliderFee);
        
        JLabel lblTotalPrice = new JLabel("Total price: 0 RSD");
        lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTotalPrice.setBounds(120, 260, 220, 25);
        frame.add(lblTotalPrice);
        
        JComboBox<String> cmbMembership = new JComboBox<>();
        cmbMembership.setBounds(120, 175, 200, 22);

        cmbMembership.addItem("Select");
        cmbMembership.addItem("Regular");
        cmbMembership.addItem("Premium");
        cmbMembership.addItem("VIP Gold");
        
        cmbMembership.addActionListener(e -> {
            String membership = cmbMembership.getSelectedItem().toString();
            int months = sliderFee.getValue();

            int price = calculatePrice(membership, months);
            lblTotalPrice.setText("Total price: " + price + " RSD");
        });

        frame.add(cmbMembership);

        JLabel lblFee = new JLabel("Monthly Fee:");
        lblFee.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblFee.setBounds(30, 210, 80, 20);
        frame.add(lblFee);
        
        
        sliderFee.addChangeListener(e -> {
            String membership = cmbMembership.getSelectedItem().toString();
            int months = sliderFee.getValue();

            int price = calculatePrice(membership, months);
            lblTotalPrice.setText("Total price: " + price + " RSD");
        });
        
        JCheckBox chkRules = new JCheckBox("I accept gym rules");
        chkRules.setBounds(120, 295, 200, 22);
        frame.add(chkRules);
        
        JLabel lblNote = new JLabel("Note:");
        lblNote.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNote.setBounds(30, 330, 80, 20);
        frame.add(lblNote);
        
        JTextArea txtNote = new JTextArea();
        txtNote.setBounds(120, 330, 200, 60);
        frame.add(txtNote);
        
        JButton btnSave = new JButton("Save Member");
        btnSave.setBounds(120, 410, 200, 30);
        frame.add(btnSave);
        
        btnSave.addActionListener(e -> {

            if (!chkRules.isSelected()) {
                JOptionPane.showMessageDialog(frame,
                        "You must accept gym rules!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String gender = rbMale.isSelected() ? "Male" : "Female";
            String membership = cmbMembership.getSelectedItem().toString();
            int months = sliderFee.getValue();
            int totalPrice = calculatePrice(membership, months);
            Date birthDate = (Date) model.getValue();
            String note = txtNote.getText();

            Member member = new Member(
                    firstName,
                    lastName,
                    gender,
                    membership,
                    months,
                    totalPrice,
                    birthDate,
                    note,
                    true
            );

            JOptionPane.showMessageDialog(frame,
                    "Member saved!\nTotal price: " + member.getTotalPrice() + " RSD");
            
            FileRepository<Member> repo = new FileRepository<>("members.dat");
            SaveThread<Member> thread = new SaveThread<>(repo, member);
            thread.start();
            
            MemberDbRepository dbRepo = new MemberDbRepository();
            dbRepo.save(member);
        });
        
        frame.setVisible(true);
    }
    
    private static int calculatePrice(String membership, int months) {
        int basePrice = 0;

        switch (membership) {
            case "Regular":
                basePrice = 7500;
                break;
            case "Premium":
                basePrice = 14000;
                break;
            case "VIP Gold":
                basePrice = 30000;
                break;
            default:
                return 0;
        }

        int total = basePrice * months;

        if (months >= 12) {
            total *= 0.8; // 20% popusta
        } else if (months >= 6) {
            total *= 0.9; // 10% popusta
        } else if (months >= 3) {
            total *= 0.95; // 5% popusta
        }

        return total;
    }
}
