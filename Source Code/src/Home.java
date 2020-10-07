
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    String date;
   
    
    public Home() {
        super("Home");
        initComponents();
        con = DBConnect.Connection();
        Calendar();
        TalbleACList();
        PrintTittle();
        HistoryTable();
    }
    
    
    
    public int AccountNumberListLS(int value) {
        int x = 0;
      String query = "select AccountNumber From  dbaccountinformation";
        try{
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData(); 
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> acNumberList = new ArrayList<>(columnCount); 
            while (rs.next()) {              
               int i = 1;
               while(i <= columnCount) {
                    acNumberList.add(rs.getString(i++));
               }

            }
            
            for(int i = 0; i < acNumberList.size(); i++) {
            //System.out.print(" " + acNumberList.get(i));
            
            }
            
            Object[] objects = acNumberList.toArray();
             for (Object obj : objects){
                //System.out.print(obj + "   ");
             }
             
            int[] acNumbers = new int[objects.length];
            for (int j = 0; j < acNumbers.length; j++){
            acNumbers[j] = Integer.parseInt((String) objects[j]);
            //System.out.print("  " +acNumbers[j]);
            
            }
            
            //linearsearch
            for(int a = 0; a < acNumbers.length; a++){
                
               if(acNumbers[a] == value){
                //System.out.print("Found");
                x = 1;
                break;
            }
            else{
                //System.out.print("Not Found");
                x = 0;
            }
               
            }
            
             
            //JOptionPane.showMessageDialog(null, "Done");
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        return x;
    }
    
    
     public int AccountNumberListBS(int value) {
        int x = 0;
        String query = "select AccountNumber From  dbaccountinformation";
        try{
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData(); 
            int columnCount = rsmd.getColumnCount();

            ArrayList<String> acNumberList = new ArrayList<>(columnCount); 
            while (rs.next()) {              
               int i = 1;
               while(i <= columnCount) {
                    acNumberList.add(rs.getString(i++));
               }

            }
            
            for(int i = 0; i < acNumberList.size(); i++) {
            //System.out.print(" " + acNumberList.get(i));
            
            }
            
            Object[] objects = acNumberList.toArray();
             for (Object obj : objects){
                //System.out.print(obj + "   ");
             }
             
            int[] acNumbers = new int[objects.length];
            for (int j = 0; j < acNumbers.length; j++){
            acNumbers[j] = Integer.parseInt((String) objects[j]);
            System.out.print("  " +acNumbers[j]);
            }
            
            //InsertionSort
            
            sort(acNumbers);
            for(int k = 0; k < acNumbers.length; k++){
                System.out.println(" " + acNumbers[k]);
            }
            
            //binary search
            int first  = 0;
            int last   = acNumbers.length - 1;
            int middle = (first + last)/2;

            while( first <= last )
            {
              if ( acNumbers[middle] < value )
                first = middle + 1;    
              else if ( acNumbers[middle] == value ) 
              {
                x = 1;
                break;
              }
              else
                 last = middle - 1;

              middle = (first + last)/2;
           }
           if ( first > last )
              x = 0;
          }
            

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        return x;
    }
     
     
     
     //Inserion Sort
     public static void sort( int arr[] )
        {
            int N = arr.length;
            int i, j, temp;
            for (i = 1; i< N; i++) 
            {
                j = i;
                temp = arr[i];    
                while (j > 0 && temp < arr[j-1])
                {
                    arr[j] = arr[j-1];
                    j = j-1;
                }
                arr[j] = temp;            
            }        
    }    
    
    public void CreditHistory(){
        String query = "insert into Statement(AccountNumber, Date, Credit, Balance)values(?,?,?,?)";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldAccountNumberd.getText());
            pst.setString(2, date);
            pst.setString(3, jTextFieldCreditAmountd.getText());
            pst.setString(4, jTextFieldNewBalanceDeposit.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deposit information added to Histoty!");
            //balance();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void AccountOpeningHistory(){
        String query = "insert into Statement(AccountNumber, Ac_Opening_Date, Credit, Balance)values(?,?,?,?)";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldAccountNumber1.getText());
            pst.setString(2, date);
            pst.setString(3, jTextFieldCreditAmount1.getText());
            pst.setString(4, jTextFieldCreditAmount1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Creation information added to Histoty!");
            //balance();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void DebitHistory(){
        String query = "insert into Statement(AccountNumber, Date, Debit, Balance)values(?,?,?,?)";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldAccountNumberw.getText());
            pst.setString(2, date);
            pst.setString(3, jTextFieldCreditAmountw.getText());
            pst.setString(4, jTextFieldNewBalancew.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Withdraw information added to Histoty!");
            //balance();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void TransferSendHistory(){
        String query = "insert into Statement(AccountNumber, Date, T_Send, T_Number , Balance)values(?,?,?,?,?)";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldSenderAccountNumber.getText());
            pst.setString(2, date);
            pst.setString(3, jTextFieldTransferAmount.getText());
            pst.setString(4, jTextFieldReceiverAccountNumber.getText());
            pst.setString(5, jTextFieldSenderNewBalance.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Transfer send information added to Histoty!");
            //balance();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void TransferReceiveHistory(){
        String query = "insert into Statement(AccountNumber, Date, T_Receive, T_Number , Balance)values(?,?,?,?,?)";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldReceiverAccountNumber.getText());
            pst.setString(2, date);
            pst.setString(3, jTextFieldTransferAmount.getText());
            pst.setString(4, jTextFieldSenderAccountNumber.getText());
            pst.setString(5, jTextFieldReceiverNewBalance.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Transfer receive information added to Histoty!");
            //balance();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Calendar(){
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        jLabelDate.setText(+day +" - " + (month + 1) + " - " + year);
        Date obj = new Date();
        date = obj.toString();
        jLabelTime.setText(date);
    }
    
    public void PrintTittle(){
        Date obj = new Date();
        date = obj.toString();
        String tittle1 = "\n  eBanking\n  Digital Internet Based Banking System\n  " + date + "\n\n  ";
        jTextAreaPrint.setText(tittle1);
    }

    public void TalbleACList(){
        String query = "select 	SL, AccountNumber, Ac_Opening_Date, AccountType, Name, Gender, DateofBirth, Religon, MobileNumber, NIDNumber, BCNumber, Address, Balance From  dbaccountinformation";
        try{
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            jTableAcList.setModel(DbUtils.resultSetToTableModel(rs));
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jButtonNewAccount = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextFieldanswer1 = new javax.swing.JTextField();
        jTextFieldPassword1 = new javax.swing.JTextField();
        jTextFieldCreditAmount1 = new javax.swing.JTextField();
        jTextFieldAccountID1 = new javax.swing.JTextField();
        jTextFieldAccountNumber1 = new javax.swing.JTextField();
        jComboBoxAccountType1 = new javax.swing.JComboBox<>();
        jTextFieldQuestion1 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextFieldMobileNumber1 = new javax.swing.JTextField();
        jTextFieldAddress1 = new javax.swing.JTextField();
        jTextFieldName1 = new javax.swing.JTextField();
        jComboBoxReligon1 = new javax.swing.JComboBox<>();
        jRadioButtonFemale1 = new javax.swing.JRadioButton();
        jRadioButtonMale1 = new javax.swing.JRadioButton();
        jRadioButtonOther1 = new javax.swing.JRadioButton();
        jDateChooserBirthDate1 = new com.toedter.calendar.JDateChooser();
        jButtonPasswordCreate = new javax.swing.JButton();
        jTextFieldNIDNumber1 = new javax.swing.JTextField();
        jTextFieldBCNumber1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldAnswer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDateofBirth = new javax.swing.JTextField();
        jTextFieldAccountID = new javax.swing.JTextField();
        jTextFieldGender = new javax.swing.JTextField();
        jTextFieldType = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldReligion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonView = new javax.swing.JButton();
        jTextFieldUserAccountNUmber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldBCNumber = new javax.swing.JTextField();
        jTextFieldSecurityQuestion = new javax.swing.JTextField();
        jTextFieldNIDNumber = new javax.swing.JTextField();
        jButtonEditInformation = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldMobileNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonSaveInformation = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldAccountNumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButtonClearVP = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldAcOpeningDate = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jTextFieldCurrentBalanced = new javax.swing.JTextField();
        jTextFieldCreditAmountd = new javax.swing.JTextField();
        jTextFieldNewBalanceDeposit = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldNamed = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButtonConfirm = new javax.swing.JButton();
        jButtonSearch = new javax.swing.JButton();
        jTextFieldMobileNumberd = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldAccountNumberd = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButtonDeposit = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jButtonClearDM = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jTextFieldCurrentBalancew = new javax.swing.JTextField();
        jTextFieldCreditAmountw = new javax.swing.JTextField();
        jTextFieldNewBalancew = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jTextFieldNamew = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jButtonConfirmw = new javax.swing.JButton();
        jButtonSearchw = new javax.swing.JButton();
        jTextFieldMobileNumberw = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextFieldAccountNumberw = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jButtonDepositw = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jButtonClearWM = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldSenderCurrentBalance = new javax.swing.JTextField();
        jTextFieldSenderAccountNumber = new javax.swing.JTextField();
        jTextFieldTransferAmount = new javax.swing.JTextField();
        jTextFieldSenderMobileNumber = new javax.swing.JTextField();
        jTextFieldSenderName = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButtonSearchSender = new javax.swing.JButton();
        jButtonTransferMoney = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jTextFieldSenderNewBalance = new javax.swing.JTextField();
        jButtonClearTM = new javax.swing.JButton();
        jButtonConfrimTransfer = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldReceiverCurrentBalance = new javax.swing.JTextField();
        jTextFieldReceiverAccountNumber = new javax.swing.JTextField();
        jTextFieldReceiverMobileNumber = new javax.swing.JTextField();
        jTextFieldReceiverName = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jButtonSearchReceiver = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jTextFieldReceiverNewBalance = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jTextFieldCurrentBalanceCB = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jTextFieldNameCB = new javax.swing.JTextField();
        jButtonSearchw1 = new javax.swing.JButton();
        jTextFieldMobileNumberCB = new javax.swing.JTextField();
        jTextFieldAccountNumberCB = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jButtonClearCB = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButtonChangePasswordPC = new javax.swing.JButton();
        jTextFieldEmployeeNamePC = new javax.swing.JTextField();
        jTextFieldCurrenPasswordPC = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jTextFieldNewPasswordPC = new javax.swing.JTextField();
        jButtonEmployeeNumberSearchPC = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        jTextFieldEmployeeNumberPC = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jTextFieldEmployeeIDPC = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jButtonEmployeePasswordCreate = new javax.swing.JButton();
        jButtonClearPC = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAcList = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButtonClearPrint = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPrint = new javax.swing.JTextArea();
        jButtonPrint = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jButtonDeleteDA = new javax.swing.JButton();
        jTypeDA = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jTextFieldAccountNumberDA = new javax.swing.JTextField();
        jTextFieldDoBDA = new javax.swing.JTextField();
        jTextFieldGenderDA = new javax.swing.JTextField();
        jTextFieldMobileNumberDA = new javax.swing.JTextField();
        jTextFieldNameDA = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jTextFieldNIDNumberDA = new javax.swing.JTextField();
        jTextFieldBCNumberDA = new javax.swing.JTextField();
        jButtonAccountNumberSearchDA = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jTextFieldBalanceDA = new javax.swing.JTextField();
        jButtonClearDA = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableStatement = new javax.swing.JTable();
        jTextFieldSearchST = new javax.swing.JTextField();
        jButtonViewH = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableHistory = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jTextFieldCoin1 = new javax.swing.JTextField();
        jTextFieldAmountCM = new javax.swing.JTextField();
        jTextFieldNote100 = new javax.swing.JTextField();
        jTextFieldNote500 = new javax.swing.JTextField();
        jTextFieldNote1000 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jTextFieldNote10 = new javax.swing.JTextField();
        jTextFieldNote20 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jTextFieldNote2 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jTextFieldNote5 = new javax.swing.JTextField();
        jTextFieldNote50 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jButtonViewCM = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        jButtonClearCM = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();
        jButtonUpdateDatabase = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home - eBanking");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\eBanking - r2hbd\\banklogo.png")); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 204));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel9.setForeground(new java.awt.Color(51, 153, 0));
        jPanel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jButtonNewAccount.setBackground(new java.awt.Color(204, 255, 204));
        jButtonNewAccount.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jButtonNewAccount.setForeground(new java.awt.Color(0, 102, 0));
        jButtonNewAccount.setText("Create New Account");
        jButtonNewAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewAccountActionPerformed(evt);
            }
        });

        jButtonClear.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClear.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jButtonClear.setForeground(new java.awt.Color(0, 102, 0));
        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 153, 0));
        jLabel31.setText("Account Number :");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 0));
        jLabel32.setText("Account ID :");

        jLabel33.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 153, 0));
        jLabel33.setText("Password :");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 0));
        jLabel34.setText("Account Type :");

        jLabel35.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 0));
        jLabel35.setText("Security Question :");

        jLabel36.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 0));
        jLabel36.setText("Answer :");

        jLabel37.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 0));
        jLabel37.setText("Credit Amount :");

        jTextFieldanswer1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldanswer1.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldPassword1.setEditable(false);
        jTextFieldPassword1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldPassword1.setForeground(new java.awt.Color(204, 0, 0));

        jTextFieldCreditAmount1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCreditAmount1.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldAccountID1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountID1.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldAccountNumber1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountNumber1.setForeground(new java.awt.Color(0, 153, 0));

        jComboBoxAccountType1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jComboBoxAccountType1.setForeground(new java.awt.Color(0, 153, 0));
        jComboBoxAccountType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Account Type", "Current", "Savings", "Student" }));

        jTextFieldQuestion1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldQuestion1.setForeground(new java.awt.Color(0, 153, 0));
        jTextFieldQuestion1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a security question", "What is your first teacher name?", "What is your first friend name?", "What is your first pet name?", "What is your first toy name?", " " }));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 102, 0));
        jLabel38.setText("Name :");

        jLabel39.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 102, 0));
        jLabel39.setText("Date of Birth :");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 0));
        jLabel40.setText("Gender :");

        jLabel54.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 102, 0));
        jLabel54.setText("Religion :");

        jLabel55.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 102, 0));
        jLabel55.setText("Mobile Number :");

        jLabel59.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 102, 0));
        jLabel59.setText("Address :");

        jLabel60.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 102, 0));
        jLabel60.setText("NID Number :");

        jLabel61.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 102, 0));
        jLabel61.setText("BC Number :");

        jTextFieldMobileNumber1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldMobileNumber1.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldAddress1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAddress1.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldName1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldName1.setForeground(new java.awt.Color(0, 102, 0));

        jComboBoxReligon1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jComboBoxReligon1.setForeground(new java.awt.Color(0, 102, 0));
        jComboBoxReligon1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Your Religion", "Islam", "Hinduism", "Christianity", "Buddhism", "Other" }));

        buttonGroup1.add(jRadioButtonFemale1);
        jRadioButtonFemale1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jRadioButtonFemale1.setForeground(new java.awt.Color(0, 102, 0));
        jRadioButtonFemale1.setText("Female");

        buttonGroup1.add(jRadioButtonMale1);
        jRadioButtonMale1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jRadioButtonMale1.setForeground(new java.awt.Color(0, 102, 0));
        jRadioButtonMale1.setText("Male");

        buttonGroup1.add(jRadioButtonOther1);
        jRadioButtonOther1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jRadioButtonOther1.setForeground(new java.awt.Color(0, 102, 0));
        jRadioButtonOther1.setText("Other");

        jDateChooserBirthDate1.setForeground(new java.awt.Color(0, 153, 51));

        jButtonPasswordCreate.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonPasswordCreate.setForeground(new java.awt.Color(0, 153, 51));
        jButtonPasswordCreate.setText("Create");
        jButtonPasswordCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasswordCreateActionPerformed(evt);
            }
        });

        jTextFieldNIDNumber1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNIDNumber1.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldBCNumber1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldBCNumber1.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNewAccount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonClear))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldQuestion1, javax.swing.GroupLayout.Alignment.LEADING, 0, 406, Short.MAX_VALUE)
                            .addComponent(jTextFieldCreditAmount1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldanswer1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextFieldPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonPasswordCreate))
                            .addComponent(jTextFieldAccountID1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAccountNumber1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxAccountType1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60)
                            .addComponent(jLabel61)
                            .addComponent(jLabel38)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel54)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jRadioButtonFemale1)
                                .addGap(12, 12, 12)
                                .addComponent(jRadioButtonMale1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonOther1)
                                .addGap(0, 110, Short.MAX_VALUE))
                            .addComponent(jTextFieldAddress1)
                            .addComponent(jTextFieldName1)
                            .addComponent(jComboBoxReligon1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldMobileNumber1)
                            .addComponent(jDateChooserBirthDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldNIDNumber1)
                            .addComponent(jTextFieldBCNumber1))))
                .addGap(39, 39, 39))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextFieldName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserBirthDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonFemale1)
                            .addComponent(jRadioButtonMale1)
                            .addComponent(jRadioButtonOther1))
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxReligon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldMobileNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNIDNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBCNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jTextFieldAccountNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jTextFieldAccountID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonPasswordCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(jTextFieldPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jComboBoxAccountType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jTextFieldQuestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jTextFieldanswer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jTextFieldCreditAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNewAccount)
                    .addComponent(jButtonClear))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create AC", jPanel9);

        jPanel1.setForeground(new java.awt.Color(0, 102, 51));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 0), 3, true), "View Profile", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 153, 51))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 0));
        jLabel14.setText("Account ID :");

        jTextFieldAnswer.setEditable(false);
        jTextFieldAnswer.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAnswer.setForeground(new java.awt.Color(0, 102, 0));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 51));
        jLabel7.setText("Gender :");

        jTextFieldDateofBirth.setEditable(false);
        jTextFieldDateofBirth.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldDateofBirth.setForeground(new java.awt.Color(0, 153, 51));

        jTextFieldAccountID.setEditable(false);
        jTextFieldAccountID.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountID.setForeground(new java.awt.Color(0, 153, 51));

        jTextFieldGender.setEditable(false);
        jTextFieldGender.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldGender.setForeground(new java.awt.Color(0, 153, 51));

        jTextFieldType.setEditable(false);
        jTextFieldType.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldType.setForeground(new java.awt.Color(0, 153, 51));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 51));
        jLabel10.setText("BC Number :");

        jTextFieldAddress.setEditable(false);
        jTextFieldAddress.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAddress.setForeground(new java.awt.Color(0, 153, 51));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 51));
        jLabel5.setText("Name :");

        jTextFieldName.setEditable(false);
        jTextFieldName.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldName.setForeground(new java.awt.Color(0, 153, 51));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 0));
        jLabel16.setText("Account Type :");

        jTextFieldReligion.setEditable(false);
        jTextFieldReligion.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldReligion.setForeground(new java.awt.Color(0, 153, 51));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 51));
        jLabel12.setText("Address :");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 0));
        jLabel2.setText("Account Number :");

        jButtonView.setBackground(new java.awt.Color(153, 255, 153));
        jButtonView.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonView.setForeground(new java.awt.Color(51, 153, 0));
        jButtonView.setText("VIEW");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });

        jTextFieldUserAccountNUmber.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jTextFieldUserAccountNUmber.setForeground(new java.awt.Color(51, 153, 0));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 0));
        jLabel13.setText("Account Number :");

        jTextFieldBCNumber.setEditable(false);
        jTextFieldBCNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldBCNumber.setForeground(new java.awt.Color(0, 153, 51));

        jTextFieldSecurityQuestion.setEditable(false);
        jTextFieldSecurityQuestion.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSecurityQuestion.setForeground(new java.awt.Color(51, 102, 0));

        jTextFieldNIDNumber.setEditable(false);
        jTextFieldNIDNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNIDNumber.setForeground(new java.awt.Color(0, 153, 51));

        jButtonEditInformation.setBackground(new java.awt.Color(153, 255, 153));
        jButtonEditInformation.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonEditInformation.setForeground(new java.awt.Color(0, 102, 0));
        jButtonEditInformation.setText("Edit ");
        jButtonEditInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditInformationActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 51));
        jLabel8.setText("Mobile Number :");

        jTextFieldMobileNumber.setEditable(false);
        jTextFieldMobileNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldMobileNumber.setForeground(new java.awt.Color(0, 153, 51));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 0));
        jLabel3.setText("Security Question :");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 51));
        jLabel9.setText("NID Number :");

        jButtonSaveInformation.setBackground(new java.awt.Color(153, 255, 153));
        jButtonSaveInformation.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonSaveInformation.setForeground(new java.awt.Color(0, 102, 0));
        jButtonSaveInformation.setText("Save ");
        jButtonSaveInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveInformationActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 51));
        jLabel6.setText("Date of Birth :");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 51));
        jLabel11.setText("Religion :");

        jTextFieldAccountNumber.setEditable(false);
        jTextFieldAccountNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountNumber.setForeground(new java.awt.Color(0, 153, 51));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 102, 0));
        jLabel15.setText("Answer :");

        jButtonClearVP.setBackground(new java.awt.Color(153, 255, 153));
        jButtonClearVP.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearVP.setForeground(new java.awt.Color(0, 102, 51));
        jButtonClearVP.setText("Clear");
        jButtonClearVP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearVPActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 0));
        jLabel20.setText("AC Opening Date :");

        jTextFieldAcOpeningDate.setEditable(false);
        jTextFieldAcOpeningDate.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAcOpeningDate.setForeground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldUserAccountNUmber)
                    .addComponent(jTextFieldMobileNumber, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNIDNumber, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBCNumber, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldReligion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addComponent(jTextFieldDateofBirth, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldGender, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(39, 39, 39)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonView)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(jButtonEditInformation)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonSaveInformation)
                            .addGap(16, 16, 16)
                            .addComponent(jButtonClearVP))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel13)
                                .addComponent(jLabel16)
                                .addComponent(jLabel3)
                                .addComponent(jLabel15)
                                .addComponent(jLabel20))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldSecurityQuestion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                .addComponent(jTextFieldType, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldAccountID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldAccountNumber, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldAnswer)
                                .addComponent(jTextFieldAcOpeningDate, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGap(21, 21, 21))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldUserAccountNUmber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonView))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldDateofBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextFieldNIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldBCNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(jTextFieldAcOpeningDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonClearVP)
                            .addComponent(jButtonSaveInformation)
                            .addComponent(jButtonEditInformation))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextFieldAccountID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldSecurityQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFieldAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View AC", jPanel1);

        jPanel3.setForeground(new java.awt.Color(51, 153, 0));
        jPanel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Deposit Money", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(51, 153, 0))); // NOI18N

        jTextFieldCurrentBalanced.setEditable(false);
        jTextFieldCurrentBalanced.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCurrentBalanced.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldCreditAmountd.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCreditAmountd.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldNewBalanceDeposit.setEditable(false);
        jTextFieldNewBalanceDeposit.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNewBalanceDeposit.setForeground(new java.awt.Color(0, 102, 0));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 0));
        jLabel21.setText("Mobile Number :");

        jTextFieldNamed.setEditable(false);
        jTextFieldNamed.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNamed.setForeground(new java.awt.Color(0, 102, 0));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 0));
        jLabel23.setText("Credit Amount :");

        jButtonConfirm.setBackground(new java.awt.Color(153, 255, 153));
        jButtonConfirm.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonConfirm.setForeground(new java.awt.Color(51, 102, 0));
        jButtonConfirm.setText("Confrim");
        jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmActionPerformed(evt);
            }
        });

        jButtonSearch.setBackground(new java.awt.Color(153, 255, 153));
        jButtonSearch.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonSearch.setForeground(new java.awt.Color(51, 102, 0));
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jTextFieldMobileNumberd.setEditable(false);
        jTextFieldMobileNumberd.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldMobileNumberd.setForeground(new java.awt.Color(0, 102, 0));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 0));
        jLabel24.setText("New Balance :");

        jTextFieldAccountNumberd.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountNumberd.setForeground(new java.awt.Color(0, 102, 0));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 0));
        jLabel18.setText("Name :");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 0));
        jLabel17.setText("Account Number :");

        jButtonDeposit.setBackground(new java.awt.Color(153, 255, 153));
        jButtonDeposit.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonDeposit.setForeground(new java.awt.Color(51, 102, 0));
        jButtonDeposit.setText("Deposit");
        jButtonDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDepositActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 0));
        jLabel22.setText("Current Balance :");

        jButtonClearDM.setBackground(new java.awt.Color(153, 255, 153));
        jButtonClearDM.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearDM.setForeground(new java.awt.Color(0, 102, 51));
        jButtonClearDM.setText("Clear");
        jButtonClearDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearDMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButtonClearDM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConfirm))
                    .addComponent(jButtonDeposit)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAccountNumberd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSearch))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCreditAmountd, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldCurrentBalanced, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldMobileNumberd, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldNamed)
                                    .addComponent(jTextFieldNewBalanceDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(3, 3, 3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldAccountNumberd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldNamed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldMobileNumberd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldCurrentBalanced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextFieldCreditAmountd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonDeposit)
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNewBalanceDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirm)
                    .addComponent(jButtonClearDM))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(332, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(352, 352, 352))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Deposit", jPanel3);

        jPanel2.setForeground(new java.awt.Color(51, 153, 0));
        jPanel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Withdraw Money", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(51, 153, 0))); // NOI18N

        jTextFieldCurrentBalancew.setEditable(false);
        jTextFieldCurrentBalancew.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCurrentBalancew.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldCreditAmountw.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCreditAmountw.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldNewBalancew.setEditable(false);
        jTextFieldNewBalancew.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNewBalancew.setForeground(new java.awt.Color(0, 102, 0));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 102, 0));
        jLabel47.setText("Mobile Number :");

        jTextFieldNamew.setEditable(false);
        jTextFieldNamew.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNamew.setForeground(new java.awt.Color(0, 102, 0));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 102, 0));
        jLabel48.setText("Debit Amount :");

        jButtonConfirmw.setBackground(new java.awt.Color(153, 255, 153));
        jButtonConfirmw.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonConfirmw.setForeground(new java.awt.Color(51, 102, 0));
        jButtonConfirmw.setText("Confrim");
        jButtonConfirmw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmwActionPerformed(evt);
            }
        });

        jButtonSearchw.setBackground(new java.awt.Color(153, 255, 153));
        jButtonSearchw.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonSearchw.setForeground(new java.awt.Color(51, 102, 0));
        jButtonSearchw.setText("Search");
        jButtonSearchw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchwActionPerformed(evt);
            }
        });

        jTextFieldMobileNumberw.setEditable(false);
        jTextFieldMobileNumberw.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldMobileNumberw.setForeground(new java.awt.Color(0, 102, 0));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 102, 0));
        jLabel49.setText("New Balance :");

        jTextFieldAccountNumberw.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountNumberw.setForeground(new java.awt.Color(0, 102, 0));

        jLabel50.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 102, 0));
        jLabel50.setText("Name :");

        jLabel51.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 102, 0));
        jLabel51.setText("Account Number :");

        jButtonDepositw.setBackground(new java.awt.Color(153, 255, 153));
        jButtonDepositw.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonDepositw.setForeground(new java.awt.Color(51, 102, 0));
        jButtonDepositw.setText("Withdraw");
        jButtonDepositw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDepositwActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 102, 0));
        jLabel52.setText("Current Balance :");

        jButtonClearWM.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClearWM.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearWM.setForeground(new java.awt.Color(0, 102, 0));
        jButtonClearWM.setText("Clear");
        jButtonClearWM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearWMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jButtonClearWM)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConfirmw))
                    .addComponent(jButtonDepositw)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                            .addComponent(jLabel51)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldAccountNumberw, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonSearchw, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel52)
                                .addComponent(jLabel47)
                                .addComponent(jLabel50)
                                .addComponent(jLabel48)
                                .addComponent(jLabel49))
                            .addGap(23, 23, 23)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldCreditAmountw, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldCurrentBalancew, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldMobileNumberw, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldNamew)
                                .addComponent(jTextFieldNewBalancew, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jTextFieldAccountNumberw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchw))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jTextFieldNamew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jTextFieldMobileNumberw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jTextFieldCurrentBalancew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jTextFieldCreditAmountw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonDepositw)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNewBalancew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmw)
                    .addComponent(jButtonClearWM))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(330, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(354, 354, 354))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Withdraw", jPanel2);

        jPanel4.setForeground(new java.awt.Color(51, 153, 0));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 0), 3, true), "Sender's Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N
        jPanel14.setForeground(new java.awt.Color(0, 102, 0));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 0));
        jLabel26.setText("Account Number :");

        jLabel30.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 102, 0));
        jLabel30.setText("Transfer Amount :");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 0));
        jLabel27.setText("Name :");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 102, 0));
        jLabel29.setText("Current Balance :");

        jTextFieldSenderCurrentBalance.setEditable(false);
        jTextFieldSenderCurrentBalance.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSenderCurrentBalance.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldSenderAccountNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSenderAccountNumber.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldTransferAmount.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldTransferAmount.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldSenderMobileNumber.setEditable(false);
        jTextFieldSenderMobileNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSenderMobileNumber.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldSenderName.setEditable(false);
        jTextFieldSenderName.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSenderName.setForeground(new java.awt.Color(0, 102, 0));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 0));
        jLabel28.setText("Mobile Number :");

        jButtonSearchSender.setBackground(new java.awt.Color(204, 255, 204));
        jButtonSearchSender.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonSearchSender.setForeground(new java.awt.Color(0, 153, 0));
        jButtonSearchSender.setText("Search");
        jButtonSearchSender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchSenderActionPerformed(evt);
            }
        });

        jButtonTransferMoney.setBackground(new java.awt.Color(204, 255, 204));
        jButtonTransferMoney.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonTransferMoney.setForeground(new java.awt.Color(0, 102, 0));
        jButtonTransferMoney.setText("Transfer");
        jButtonTransferMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTransferMoneyActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 102, 0));
        jLabel42.setText("New Balance :");

        jTextFieldSenderNewBalance.setEditable(false);
        jTextFieldSenderNewBalance.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSenderNewBalance.setForeground(new java.awt.Color(51, 102, 0));

        jButtonClearTM.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClearTM.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearTM.setForeground(new java.awt.Color(51, 153, 0));
        jButtonClearTM.setText("Clear");
        jButtonClearTM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearTMActionPerformed(evt);
            }
        });

        jButtonConfrimTransfer.setBackground(new java.awt.Color(153, 255, 153));
        jButtonConfrimTransfer.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonConfrimTransfer.setForeground(new java.awt.Color(0, 102, 51));
        jButtonConfrimTransfer.setText("Confrim Transfer");
        jButtonConfrimTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfrimTransferActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSenderAccountNumber)
                            .addComponent(jTextFieldSenderMobileNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldSenderCurrentBalance)
                            .addComponent(jTextFieldSenderName, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jTextFieldTransferAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldSenderNewBalance)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSearchSender, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonTransferMoney, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jButtonClearTM)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonConfrimTransfer)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextFieldSenderAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSearchSender)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextFieldSenderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextFieldSenderMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextFieldSenderCurrentBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextFieldTransferAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonTransferMoney)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextFieldSenderNewBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfrimTransfer)
                    .addComponent(jButtonClearTM))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 3, true), "Receiver's Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N
        jPanel17.setForeground(new java.awt.Color(51, 102, 0));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 0));
        jLabel41.setText("Account Number :");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 102, 0));
        jLabel43.setText("Name :");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 102, 0));
        jLabel44.setText("Current Balance :");

        jTextFieldReceiverCurrentBalance.setEditable(false);
        jTextFieldReceiverCurrentBalance.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldReceiverCurrentBalance.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldReceiverAccountNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldReceiverAccountNumber.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldReceiverMobileNumber.setEditable(false);
        jTextFieldReceiverMobileNumber.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldReceiverMobileNumber.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldReceiverName.setEditable(false);
        jTextFieldReceiverName.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldReceiverName.setForeground(new java.awt.Color(0, 102, 0));

        jLabel45.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 102, 0));
        jLabel45.setText("Mobile Number :");

        jButtonSearchReceiver.setBackground(new java.awt.Color(204, 255, 204));
        jButtonSearchReceiver.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonSearchReceiver.setForeground(new java.awt.Color(0, 102, 0));
        jButtonSearchReceiver.setText("Search");
        jButtonSearchReceiver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchReceiverActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 102, 0));
        jLabel46.setText("New Balance :");

        jTextFieldReceiverNewBalance.setEditable(false);
        jTextFieldReceiverNewBalance.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldReceiverNewBalance.setForeground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSearchReceiver, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel43)
                            .addComponent(jLabel41)
                            .addComponent(jLabel46))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldReceiverAccountNumber)
                            .addComponent(jTextFieldReceiverMobileNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldReceiverCurrentBalance)
                            .addComponent(jTextFieldReceiverName, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(jTextFieldReceiverNewBalance))))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextFieldReceiverAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSearchReceiver)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextFieldReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jTextFieldReceiverMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextFieldReceiverCurrentBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jTextFieldReceiverNewBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addGap(56, 56, 56)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel25))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Transfer Money", jPanel4);

        jPanel7.setForeground(new java.awt.Color(51, 153, 0));
        jPanel7.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Check Balance", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(51, 153, 0))); // NOI18N

        jTextFieldCurrentBalanceCB.setEditable(false);
        jTextFieldCurrentBalanceCB.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCurrentBalanceCB.setForeground(new java.awt.Color(0, 102, 0));

        jLabel53.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 102, 0));
        jLabel53.setText("Mobile Number :");

        jTextFieldNameCB.setEditable(false);
        jTextFieldNameCB.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNameCB.setForeground(new java.awt.Color(0, 102, 0));

        jButtonSearchw1.setBackground(new java.awt.Color(153, 255, 153));
        jButtonSearchw1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonSearchw1.setForeground(new java.awt.Color(51, 102, 0));
        jButtonSearchw1.setText("Search");
        jButtonSearchw1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchw1ActionPerformed(evt);
            }
        });

        jTextFieldMobileNumberCB.setEditable(false);
        jTextFieldMobileNumberCB.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldMobileNumberCB.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldAccountNumberCB.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountNumberCB.setForeground(new java.awt.Color(0, 102, 0));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 102, 0));
        jLabel56.setText("Name :");

        jLabel57.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 102, 0));
        jLabel57.setText("Account Number :");

        jLabel58.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 102, 0));
        jLabel58.setText("Current Balance :");

        jButtonClearCB.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClearCB.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearCB.setForeground(new java.awt.Color(0, 102, 0));
        jButtonClearCB.setText("Clear");
        jButtonClearCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonClearCB)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addComponent(jLabel57)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldAccountNumberCB, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonSearchw1))
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel58)
                                .addComponent(jLabel53)
                                .addComponent(jLabel56))
                            .addGap(23, 23, 23)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldCurrentBalanceCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                .addComponent(jTextFieldMobileNumberCB, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldNameCB)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jTextFieldAccountNumberCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchw1))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jTextFieldNameCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jTextFieldMobileNumberCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jTextFieldCurrentBalanceCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonClearCB)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(335, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Check Balance", jPanel7);

        jPanel8.setForeground(new java.awt.Color(51, 153, 0));
        jPanel8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Password Change", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N

        jButtonChangePasswordPC.setBackground(new java.awt.Color(153, 255, 153));
        jButtonChangePasswordPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonChangePasswordPC.setForeground(new java.awt.Color(0, 153, 0));
        jButtonChangePasswordPC.setText("Change Password");
        jButtonChangePasswordPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangePasswordPCActionPerformed(evt);
            }
        });

        jTextFieldEmployeeNamePC.setEditable(false);
        jTextFieldEmployeeNamePC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldEmployeeNamePC.setForeground(new java.awt.Color(0, 102, 0));

        jTextFieldCurrenPasswordPC.setEditable(false);
        jTextFieldCurrenPasswordPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCurrenPasswordPC.setForeground(new java.awt.Color(0, 102, 0));

        jLabel65.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 102, 0));
        jLabel65.setText("Current Password :");

        jTextFieldNewPasswordPC.setEditable(false);
        jTextFieldNewPasswordPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNewPasswordPC.setForeground(new java.awt.Color(0, 102, 0));

        jButtonEmployeeNumberSearchPC.setBackground(new java.awt.Color(153, 255, 153));
        jButtonEmployeeNumberSearchPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonEmployeeNumberSearchPC.setForeground(new java.awt.Color(0, 153, 0));
        jButtonEmployeeNumberSearchPC.setText("Search");
        jButtonEmployeeNumberSearchPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmployeeNumberSearchPCActionPerformed(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 102, 0));
        jLabel66.setText("New Password :");

        jTextFieldEmployeeNumberPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldEmployeeNumberPC.setForeground(new java.awt.Color(0, 102, 0));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 102, 0));
        jLabel63.setText("Employee ID :");

        jTextFieldEmployeeIDPC.setEditable(false);
        jTextFieldEmployeeIDPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldEmployeeIDPC.setForeground(new java.awt.Color(0, 102, 0));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 102, 0));
        jLabel64.setText("Employee Name :");

        jLabel62.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 102, 0));
        jLabel62.setText("Employee Number :");

        jButtonEmployeePasswordCreate.setBackground(new java.awt.Color(153, 255, 153));
        jButtonEmployeePasswordCreate.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonEmployeePasswordCreate.setForeground(new java.awt.Color(0, 153, 0));
        jButtonEmployeePasswordCreate.setText("Create");
        jButtonEmployeePasswordCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmployeePasswordCreateActionPerformed(evt);
            }
        });

        jButtonClearPC.setBackground(new java.awt.Color(153, 255, 153));
        jButtonClearPC.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearPC.setForeground(new java.awt.Color(0, 153, 0));
        jButtonClearPC.setText("Clear");
        jButtonClearPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearPCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonChangePasswordPC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonClearPC))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addComponent(jTextFieldNewPasswordPC)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonEmployeePasswordCreate))
                                    .addComponent(jTextFieldCurrenPasswordPC, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jTextFieldEmployeeNumberPC, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonEmployeeNumberSearchPC))
                            .addComponent(jTextFieldEmployeeIDPC)
                            .addComponent(jTextFieldEmployeeNamePC))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jTextFieldEmployeeNumberPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEmployeeNumberSearchPC))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jTextFieldEmployeeIDPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jTextFieldEmployeeNamePC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jTextFieldCurrenPasswordPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jTextFieldNewPasswordPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEmployeePasswordCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClearPC)
                    .addComponent(jButtonChangePasswordPC))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(303, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(320, 320, 320))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Change Password", jPanel8);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "AC List", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(51, 153, 0));
        jPanel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jTableAcList.setForeground(new java.awt.Color(0, 153, 51));
        jTableAcList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableAcList);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("AC List", jPanel6);

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));
        jPanel11.setForeground(new java.awt.Color(51, 153, 0));
        jPanel11.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Print", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N

        jButtonClearPrint.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClearPrint.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearPrint.setForeground(new java.awt.Color(51, 153, 0));
        jButtonClearPrint.setText("Clear ");
        jButtonClearPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearPrintActionPerformed(evt);
            }
        });

        jTextAreaPrint.setColumns(20);
        jTextAreaPrint.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaPrint.setRows(5);
        jScrollPane2.setViewportView(jTextAreaPrint);

        jButtonPrint.setBackground(new java.awt.Color(204, 255, 204));
        jButtonPrint.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonPrint.setForeground(new java.awt.Color(51, 153, 0));
        jButtonPrint.setText("Print ");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonClearPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButtonPrint)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonClearPrint)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Print PDF", jPanel11);

        jPanel10.setForeground(new java.awt.Color(51, 153, 0));
        jPanel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Delete Account", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(51, 153, 0))); // NOI18N

        jLabel74.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 153, 0));
        jLabel74.setText("Account Type :");

        jButtonDeleteDA.setBackground(new java.awt.Color(153, 255, 153));
        jButtonDeleteDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonDeleteDA.setForeground(new java.awt.Color(0, 153, 51));
        jButtonDeleteDA.setText("Delete Account");
        jButtonDeleteDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteDAActionPerformed(evt);
            }
        });

        jTypeDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTypeDA.setForeground(new java.awt.Color(0, 153, 0));

        jLabel67.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 153, 0));
        jLabel67.setText("Account Number :");

        jTextFieldAccountNumberDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAccountNumberDA.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldDoBDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldDoBDA.setForeground(new java.awt.Color(0, 153, 0));
        jTextFieldDoBDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDoBDAActionPerformed(evt);
            }
        });

        jTextFieldGenderDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldGenderDA.setForeground(new java.awt.Color(0, 153, 0));
        jTextFieldGenderDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGenderDAActionPerformed(evt);
            }
        });

        jTextFieldMobileNumberDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldMobileNumberDA.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldNameDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNameDA.setForeground(new java.awt.Color(0, 153, 0));

        jLabel69.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 153, 0));
        jLabel69.setText("Gender :");

        jLabel71.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 153, 0));
        jLabel71.setText("Mobile Number :");

        jLabel75.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 153, 0));
        jLabel75.setText("Balance :");

        jLabel70.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 153, 0));
        jLabel70.setText("Date of Birth :");

        jTextFieldNIDNumberDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNIDNumberDA.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldBCNumberDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldBCNumberDA.setForeground(new java.awt.Color(0, 153, 0));
        jTextFieldBCNumberDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBCNumberDAActionPerformed(evt);
            }
        });

        jButtonAccountNumberSearchDA.setBackground(new java.awt.Color(204, 255, 204));
        jButtonAccountNumberSearchDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonAccountNumberSearchDA.setForeground(new java.awt.Color(0, 153, 0));
        jButtonAccountNumberSearchDA.setText("Search");
        jButtonAccountNumberSearchDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountNumberSearchDAActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 153, 0));
        jLabel68.setText("Name :");

        jLabel72.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 153, 0));
        jLabel72.setText("NID Number :");

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 153, 0));
        jLabel73.setText("BC Number :");

        jTextFieldBalanceDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldBalanceDA.setForeground(new java.awt.Color(0, 153, 0));

        jButtonClearDA.setBackground(new java.awt.Color(153, 255, 153));
        jButtonClearDA.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearDA.setForeground(new java.awt.Color(0, 153, 51));
        jButtonClearDA.setText("Clear");
        jButtonClearDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearDAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldAccountNumberDA, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAccountNumberSearchDA)
                        .addGap(28, 28, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)
                            .addComponent(jLabel72)
                            .addComponent(jLabel75))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNIDNumberDA)
                            .addComponent(jTypeDA)
                            .addComponent(jTextFieldBalanceDA)
                            .addComponent(jTextFieldBCNumberDA, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71)
                            .addComponent(jLabel70)
                            .addComponent(jLabel69)
                            .addComponent(jLabel68))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMobileNumberDA, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldDoBDA, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldGenderDA, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNameDA, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDeleteDA)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonClearDA)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(jTextFieldAccountNumberDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAccountNumberSearchDA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jTextFieldNameDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(jTextFieldGenderDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(jTextFieldDoBDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(jTextFieldNIDNumberDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(jTextFieldBCNumberDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTypeDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(jTextFieldBalanceDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel71)
                        .addComponent(jTextFieldMobileNumberDA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDeleteDA)
                            .addComponent(jButtonClearDA))))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delete AC", jPanel10);

        jPanel12.setForeground(new java.awt.Color(51, 153, 0));
        jPanel12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 3, true), "Statement", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N

        jLabel76.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 102, 51));
        jLabel76.setText("Account Number : ");

        jTableStatement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableStatement);

        jTextFieldSearchST.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldSearchST.setForeground(new java.awt.Color(0, 102, 51));

        jButtonViewH.setBackground(new java.awt.Color(204, 255, 204));
        jButtonViewH.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonViewH.setForeground(new java.awt.Color(0, 102, 0));
        jButtonViewH.setText("View");
        jButtonViewH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldSearchST, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonViewH)
                        .addGap(433, 433, 433))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(jTextFieldSearchST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonViewH))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Statement", jPanel12);

        jPanel13.setForeground(new java.awt.Color(51, 153, 0));
        jPanel13.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "History", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(0, 153, 0))); // NOI18N

        jTableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTableHistory);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1274, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("History", jPanel13);

        jPanel23.setForeground(new java.awt.Color(51, 153, 0));
        jPanel23.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 3, true), "Change Money", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 24), new java.awt.Color(0, 204, 0))); // NOI18N

        jTextFieldCoin1.setEditable(false);
        jTextFieldCoin1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldCoin1.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldAmountCM.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldAmountCM.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldNote100.setEditable(false);
        jTextFieldNote100.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote100.setForeground(new java.awt.Color(0, 153, 0));
        jTextFieldNote100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNote100ActionPerformed(evt);
            }
        });

        jTextFieldNote500.setEditable(false);
        jTextFieldNote500.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote500.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldNote1000.setEditable(false);
        jTextFieldNote1000.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote1000.setForeground(new java.awt.Color(0, 153, 0));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 153, 0));
        jLabel84.setText("Note 5 :");

        jLabel77.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 153, 0));
        jLabel77.setText("Enter Amount :");

        jLabel83.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 153, 0));
        jLabel83.setText("Note 10 :");

        jLabel86.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 153, 0));
        jLabel86.setText("Coin 1 :");

        jTextFieldNote10.setEditable(false);
        jTextFieldNote10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote10.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldNote20.setEditable(false);
        jTextFieldNote20.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote20.setForeground(new java.awt.Color(0, 153, 0));

        jLabel82.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 153, 0));
        jLabel82.setText("Note 20 :");

        jTextFieldNote2.setEditable(false);
        jTextFieldNote2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote2.setForeground(new java.awt.Color(0, 153, 0));

        jLabel81.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(0, 153, 0));
        jLabel81.setText("Note 50 :");

        jLabel79.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 153, 0));
        jLabel79.setText("Note 500 :");

        jLabel85.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 153, 0));
        jLabel85.setText("Note 2 :");

        jTextFieldNote5.setEditable(false);
        jTextFieldNote5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote5.setForeground(new java.awt.Color(0, 153, 0));

        jTextFieldNote50.setEditable(false);
        jTextFieldNote50.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldNote50.setForeground(new java.awt.Color(0, 153, 0));

        jLabel78.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 153, 0));
        jLabel78.setText("Note 1000 :");

        jButtonViewCM.setBackground(new java.awt.Color(204, 255, 204));
        jButtonViewCM.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonViewCM.setForeground(new java.awt.Color(0, 153, 0));
        jButtonViewCM.setText("View");
        jButtonViewCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewCMActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 153, 0));
        jLabel80.setText("Note 100 :");

        jButtonClearCM.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClearCM.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClearCM.setForeground(new java.awt.Color(0, 153, 51));
        jButtonClearCM.setText("Clear");
        jButtonClearCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearCMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78)
                            .addComponent(jLabel79)
                            .addComponent(jLabel80)
                            .addComponent(jLabel81))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldNote100, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNote500, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNote1000, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel28Layout.createSequentialGroup()
                                .addComponent(jTextFieldAmountCM, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonViewCM, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldNote50, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(jLabel83)
                            .addComponent(jLabel84)
                            .addComponent(jLabel85)
                            .addComponent(jLabel86))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldNote2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNote5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNote10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNote20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCoin1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonClearCM, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82)
                            .addComponent(jTextFieldNote20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(jTextFieldNote10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel84)
                            .addComponent(jTextFieldNote5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel85)
                            .addComponent(jTextFieldNote2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel86)
                            .addComponent(jTextFieldCoin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(jTextFieldAmountCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonViewCM))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78)
                            .addComponent(jTextFieldNote1000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(jTextFieldNote500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(jTextFieldNote100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81)
                            .addComponent(jTextFieldNote50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonClearCM)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Change Money", jPanel23);

        jPanel24.setForeground(new java.awt.Color(51, 153, 0));
        jPanel24.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel87.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\eBanking - r2hbd\\src\\banklogo.png")); // NOI18N

        jLabel88.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 153, 51));
        jLabel88.setText("Dingital Bank Management Software.");

        jLabel89.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 153, 51));
        jLabel89.setText("Beta Version 2018.01");

        jButtonClose.setBackground(new java.awt.Color(204, 255, 204));
        jButtonClose.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonClose.setForeground(new java.awt.Color(204, 0, 0));
        jButtonClose.setText("Exit");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonUpdateDatabase.setBackground(new java.awt.Color(153, 255, 153));
        jButtonUpdateDatabase.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButtonUpdateDatabase.setForeground(new java.awt.Color(0, 102, 0));
        jButtonUpdateDatabase.setText("Update Database");
        jButtonUpdateDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateDatabaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addGap(412, 412, 412))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonUpdateDatabase)
                            .addComponent(jLabel89))
                        .addGap(495, 495, 495))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addComponent(jButtonClose)
                        .addGap(590, 590, 590))))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addComponent(jLabel87)
                .addContainerGap(340, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel87)
                .addGap(18, 18, 18)
                .addComponent(jLabel88)
                .addGap(18, 18, 18)
                .addComponent(jLabel89)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdateDatabase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonClose)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("About", jPanel24);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Date : ");

        jLabelDate.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelDate.setForeground(new java.awt.Color(0, 153, 0));
        jLabelDate.setText("?");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 0));
        jLabel19.setText("Time :");

        jLabelTime.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabelTime.setForeground(new java.awt.Color(0, 153, 0));
        jLabelTime.setText("?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel19))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelDate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelTime))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel19))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1366, 751));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClearPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearPCActionPerformed
        // TODO add your handling code here:
        jTextFieldEmployeeNumberPC.setText("");
        jTextFieldEmployeeIDPC.setText("");
        jTextFieldEmployeeNamePC.setText("");
        jTextFieldCurrenPasswordPC.setText("");
        jTextFieldNewPasswordPC.setText("");
    }//GEN-LAST:event_jButtonClearPCActionPerformed

    private void jButtonEmployeePasswordCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmployeePasswordCreateActionPerformed
        // TODO add your handling code here:

        randomPasswordChangeEployee();
    }//GEN-LAST:event_jButtonEmployeePasswordCreateActionPerformed

    private void jButtonEmployeeNumberSearchPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmployeeNumberSearchPCActionPerformed
        // TODO add your handling code here:
        String query = "select * from dbemployeeinformationebanking where Number = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldEmployeeNumberPC.getText());
            rs = pst.executeQuery();

            if(rs.next()){
                String add61 = rs.getString("ID");
                jTextFieldEmployeeIDPC.setText(add61);
                String add62 = rs.getString("Name");
                jTextFieldEmployeeNamePC.setText(add62);
                String add63 = rs.getString("Password");
                jTextFieldCurrenPasswordPC.setText(add63);
                rs.close();
                pst.close();
                Calendar();
            }
            else{
                JOptionPane.showMessageDialog(null, "Enter Correct Employee Number!");
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }

    }//GEN-LAST:event_jButtonEmployeeNumberSearchPCActionPerformed

    private void jButtonChangePasswordPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangePasswordPCActionPerformed
        // TODO add your handling code here:
        try{
            String employeeNumberPC = jTextFieldEmployeeNumberPC.getText();
            String newPassword = jTextFieldNewPasswordPC.getText();
            String query = "update dbemployeeinformationebanking set Password = '"+newPassword+"' where Number = '"+employeeNumberPC+"'";
            pst = con.prepareStatement(query);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Password has been changed successfully!");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonChangePasswordPCActionPerformed

    private void jButtonClearCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearCBActionPerformed
        // TODO add your handling code here:
        jTextFieldAccountNumberCB.setText("");
        jTextFieldNameCB.setText("");
        jTextFieldMobileNumberCB.setText("");
        jTextFieldCurrentBalanceCB.setText("");
    }//GEN-LAST:event_jButtonClearCBActionPerformed

    private void jButtonSearchw1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchw1ActionPerformed
        // TODO add your handling code here:
        String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldAccountNumberCB.getText());
            rs = pst.executeQuery();

            if(rs.next()){
                String add51 = rs.getString("Name");
                jTextFieldNameCB.setText(add51);
                String add52 = rs.getString("MobileNumber");
                jTextFieldMobileNumberCB.setText(add52);
                String add54 = rs.getString("Balance");
                jTextFieldCurrentBalanceCB.setText(add54);
                rs.close();
                pst.close();
                Calendar();
            }
            else{
                JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }

    }//GEN-LAST:event_jButtonSearchw1ActionPerformed

    private void jButtonConfrimTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfrimTransferActionPerformed
        // TODO add your handling code here:
        SenderTransfer();
        TransferSendHistory();
        ReceiverTransfer();
        TransferReceiveHistory();
        TransferPrint();
        Calendar();
    }//GEN-LAST:event_jButtonConfrimTransferActionPerformed

    private void jButtonSearchReceiverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchReceiverActionPerformed
        // TODO add your handling code here:
        String acSearch = jTextFieldSenderAccountNumber.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListBS(acSearchValue);
        
        if(result == 1 ){
                System.out.print("Found");
                String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldReceiverAccountNumber.getText());
            rs = pst.executeQuery();

            if(rs.next()){
                String add41 = rs.getString("Name");
                jTextFieldReceiverName.setText(add41);
                String add42 = rs.getString("MobileNumber");
                jTextFieldReceiverMobileNumber.setText(add42);
                String add43 = rs.getString("Balance");
                jTextFieldReceiverCurrentBalance.setText(add43);
                rs.close();
                pst.close();
            }
            else{
                //JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }
            }
        if((result == 0 )){
                System.out.print("Not Found");
                JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }

        
    }//GEN-LAST:event_jButtonSearchReceiverActionPerformed

    private void jButtonTransferMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTransferMoneyActionPerformed
        // TODO add your handling code here:
        try{
            String senderCurrentAmount = jTextFieldSenderCurrentBalance.getText();
            String receiverCurrentAmount = jTextFieldReceiverCurrentBalance.getText();
            String transferAmount = jTextFieldTransferAmount.getText();
            int senderNewBalance = Integer.parseInt(senderCurrentAmount) - Integer.parseInt(transferAmount);
            int receiverNewBalance = Integer.parseInt(receiverCurrentAmount) + Integer.parseInt(transferAmount);
            String senderNewAmount = String.valueOf(senderNewBalance);
            String receiverNewAmount = String.valueOf(receiverNewBalance);
            jTextFieldSenderNewBalance.setText(senderNewAmount);
            jTextFieldReceiverNewBalance.setText(receiverNewAmount);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButtonTransferMoneyActionPerformed

    private void jButtonSearchSenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchSenderActionPerformed
        // TODO add your handling code here:
        String acSearch = jTextFieldSenderAccountNumber.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListBS(acSearchValue);
        
        if(result == 1 ){
                System.out.print("Found");
                String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, acSearch);
            rs = pst.executeQuery();

            if(rs.next()){
                String add31 = rs.getString("Name");
                jTextFieldSenderName.setText(add31);
                String add32 = rs.getString("MobileNumber");
                jTextFieldSenderMobileNumber.setText(add32);
                String add33 = rs.getString("Balance");
                jTextFieldSenderCurrentBalance.setText(add33);
                rs.close();
                pst.close();
            }
            else{
                //JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }
            }
        if((result == 0 )){
                System.out.print("  Not Found");
                JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }
        
    }//GEN-LAST:event_jButtonSearchSenderActionPerformed

    private void jButtonDepositwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDepositwActionPerformed
        // TODO add your handling code here:
        try{
            String currentAmountw = jTextFieldCurrentBalancew.getText();
            String debitAmountw = jTextFieldCreditAmountw.getText();
            int newBalancew = Integer.parseInt(currentAmountw) - Integer.parseInt(debitAmountw);
            String newAmountw = String.valueOf(newBalancew);
            jTextFieldNewBalancew.setText(newAmountw);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButtonDepositwActionPerformed

    private void jButtonSearchwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchwActionPerformed
        // TODO add your handling code here:
        String acSearch = jTextFieldAccountNumberw.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListLS(acSearchValue);
        
        if(result == 1){
            String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, acSearch);
            rs = pst.executeQuery();

            if(rs.next()){
                String add21 = rs.getString("Name");
                jTextFieldNamew.setText(add21);
                String add22 = rs.getString("MobileNumber");
                jTextFieldMobileNumberw.setText(add22);
                String add23 = rs.getString("Balance");
                jTextFieldCurrentBalancew.setText(add23);
                rs.close();
                pst.close();
            }
            else{
                //JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }
        }
        if(result == 0){
            JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
        }
        
    }//GEN-LAST:event_jButtonSearchwActionPerformed

    private void jButtonConfirmwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmwActionPerformed
        // TODO add your handling code here:
        try{
            String debitAccountName = jTextFieldAccountNumberw.getText();
            String newAmountUpdate = jTextFieldNewBalancew.getText();
            String query7 = "update dbaccountinformation set Balance = '"+newAmountUpdate+"' where AccountNumber = '"+debitAccountName+"'";
            pst = con.prepareStatement(query7);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Withdraw has been done successfully!");
            DebitHistory();
            WithdrawPrint();
            Calendar();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonConfirmwActionPerformed

    private void jButtonDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDepositActionPerformed
        // TODO add your handling code here:
        try{
            String currentAmount = jTextFieldCurrentBalanced.getText();
            String creditAmount = jTextFieldCreditAmountd.getText();
            int newBalance = Integer.parseInt(currentAmount) + Integer.parseInt(creditAmount);
            String newAmount = String.valueOf(newBalance);
            jTextFieldNewBalanceDeposit.setText(newAmount);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonDepositActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        // TODO add your handling code here:
        String acSearch = jTextFieldAccountNumberd.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListLS(acSearchValue);
        if(result == 1 ){
                System.out.print("Found");
                String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, acSearch);
            rs = pst.executeQuery();

            if(rs.next()){
                String add21 = rs.getString("Name");
                jTextFieldNamed.setText(add21);
                String add22 = rs.getString("MobileNumber");
                jTextFieldMobileNumberd.setText(add22);
                String add23 = rs.getString("Balance");
                jTextFieldCurrentBalanced.setText(add23);
                rs.close();
                pst.close();
            }
            else{
                //JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
            }
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }
            }
        if((result == 0 )){
                //System.out.print("Not Found");
                JOptionPane.showMessageDialog(null, "Enter Correct Account Number!");
                
            }
        
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmActionPerformed
        // TODO add your handling code here:
        try{
            String depositAccountName = jTextFieldAccountNumberd.getText();
            String newAmountUpdate = jTextFieldNewBalanceDeposit.getText();
            String query = "update dbaccountinformation set Balance = '"+newAmountUpdate+"' where AccountNumber = '"+depositAccountName+"'";
            pst = con.prepareStatement(query);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deposit has been done successfully!");
            CreditHistory();
            DepositPrint();
            Calendar();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonConfirmActionPerformed

    public void DepositPrint(){
        Date obj = new Date();
        date = obj.toString();
        String tittle1 = "\n  eBanking\n  Digital Internet Based Banking System\n  " + date + "\n\n  ";
        String accountNumber = "\n  Account Number : " + jTextFieldAccountNumberd.getText();
        String accountHolderName = "\n  Account Holdeer Name : " +jTextFieldNamed.getText();
        String mobilNumber = "\n  Mobile Number : " + jTextFieldMobileNumberd.getText();
        String amount = "\n  Deposit Amount : " + jTextFieldCreditAmountd.getText() + " BDT";
        String sign = "\n\n\n\n\n  Signature of Officer";
        String printTextDeposit = tittle1 + accountNumber + accountHolderName + mobilNumber + amount + sign;
        jTextAreaPrint.setText(printTextDeposit);
    }
    
    public void WithdrawPrint(){
        Date obj = new Date();
        date = obj.toString();
        String tittle1 = "\n  eBanking\n  Digital Internet Based Banking System\n  " + date + "\n\n  ";
        String accountNumber = "\n  Account Number : " + jTextFieldAccountNumberw.getText();
        String accountHolderName = "\n  Account Holdeer Name : " +jTextFieldNamew.getText();
        String mobilNumber = "\n  Mobile Number : " + jTextFieldMobileNumberw.getText();
        String amount = "\n  Withdraw Amount : " + jTextFieldCreditAmountw.getText() + " BDT";
        String sign = "\n\n\n\n\n  Signature of Officer";
        String printTextDeposit = tittle1 + accountNumber + accountHolderName + mobilNumber + amount + sign;
        jTextAreaPrint.setText(printTextDeposit);
    }
    
    public void TransferPrint(){
        Date obj = new Date();
        date = obj.toString();
        String tittle1 = "\n  eBanking\n  Digital Internet Based Banking System\n  " + date + "\n\n  ";
        String accountNumber = "\n  Sender Account Number : " + jTextFieldSenderAccountNumber.getText();
        String accountHolderName = "\n  Sender Account Holdeer Name : " + jTextFieldSenderName.getText();
        String mobilNumber = "\n  Sender Mobile Number : " + jTextFieldSenderMobileNumber.getText();
        String raccountNumber = "\n\n  Receiver Account Number : " + jTextFieldReceiverAccountNumber.getText();
        String raccountHolderName = "\n  Receiver Account Holdeer Name : " + jTextFieldReceiverName.getText();
        String rmobilNumber = "\n  Receiver Mobile Number : " + jTextFieldReceiverMobileNumber.getText();
        String amount = "\n\n  Transfer Amount : " + jTextFieldTransferAmount.getText() + " BDT";
        String sign = "\n\n\n\n\n  Signature of Officer";
        String printTextDeposit = tittle1 + accountNumber + accountHolderName + mobilNumber + raccountNumber + raccountHolderName + rmobilNumber + amount + sign;
        jTextAreaPrint.setText(printTextDeposit);
    }
    
    private void jButtonClearVPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearVPActionPerformed
        // TODO add your handling code here:
        jTextFieldDateofBirth.setText("");
        jTextFieldSecurityQuestion.setText("");
        jTextFieldGender.setText("");
        jTextFieldAccountNumber.setText("");
        jTextFieldAccountID.setText("");
        jTextFieldAnswer.setText("");
        jTextFieldName.setText("");
        jTextFieldMobileNumber.setText("");
        jTextFieldAddress.setText("");
        jTextFieldNIDNumber.setText("");
        jTextFieldBCNumber.setText("");
        jTextFieldType.setText("");
        jTextFieldReligion.setText("");
        jTextFieldAcOpeningDate.setText("");
    }//GEN-LAST:event_jButtonClearVPActionPerformed

    private void jButtonSaveInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveInformationActionPerformed
        // TODO add your handling code here:
        try{
            String acNum = jTextFieldAccountNumber.getText();
            String editGender = jTextFieldGender.getText();
            String editMobileNumber = jTextFieldMobileNumber.getText();
            String editReligion = jTextFieldReligion.getText();
            String editAddress = jTextFieldAddress.getText();
            String editQuestion = jTextFieldSecurityQuestion.getText();
            String editAnswer = jTextFieldAnswer.getText();

            String query = "update dbaccountinformation set Gender = '"+editGender+"', MobileNumber = '"+editMobileNumber+"',Religon = '"+editReligion+"',Address = '"+editAddress+"', SecurityQuestion = '"+editQuestion+"', Answer = '"+editAnswer+"' where AccountNumber = '"+acNum+"'";
            pst = con.prepareStatement(query);
            pst.execute();
            
            
            JOptionPane.showMessageDialog(null, "Account information has been updated!");
            jTextFieldName.setText("");
            jTextFieldDateofBirth.setText("");
            jTextFieldGender.setText("");
            jTextFieldMobileNumber.setText("");
            jTextFieldNIDNumber.setText("");
            jTextFieldBCNumber.setText("");
            jTextFieldReligion.setText("");
            jTextFieldAddress.setText("");
            jTextFieldAccountNumber.setText("");
            jTextFieldAccountID.setText("");
            jTextFieldType.setText("");
            jTextFieldSecurityQuestion.setText("");
            jTextFieldAnswer.setText("");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonSaveInformationActionPerformed

    private void jButtonEditInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditInformationActionPerformed
        // TODO add your handling code here:
        //jTextFieldName.setEditable(true);
        //jTextFieldDateofBirth.setEditable(true);
        jTextFieldGender.setEditable(true);
        jTextFieldMobileNumber.setEditable(true);
        //jTextFieldNIDNumber.setEditable(true);
        //jTextFieldBCNumber.setEditable(true);
        jTextFieldReligion.setEditable(true);
        jTextFieldAddress.setEditable(true);
        jTextFieldSecurityQuestion.setEditable(true);
        jTextFieldAnswer.setEditable(true);
    }//GEN-LAST:event_jButtonEditInformationActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        // TODO add your handling code here:
        String acSearch = jTextFieldUserAccountNUmber.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListLS(acSearchValue);
        
        if(result == 1){
        String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldUserAccountNUmber.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                String add1 = rs.getString("Name");
                jTextFieldName.setText(add1);
                String add2 = rs.getString("DateofBirth");
                jTextFieldDateofBirth.setText(add2);
                String add3 = rs.getString("Gender");
                jTextFieldGender.setText(add3);
                String add4 = rs.getString("MobileNumber");
                jTextFieldMobileNumber.setText(add4);
                String add5 = rs.getString("NIDNumber");
                jTextFieldNIDNumber.setText(add5);
                String add6 = rs.getString("BCNumber");
                jTextFieldBCNumber.setText(add6);
                String add7 = rs.getString("Religon");
                jTextFieldReligion.setText(add7);
                String add8 = rs.getString("Address");
                jTextFieldAddress.setText(add8);
                String add9 = rs.getString("AccountNumber");
                jTextFieldAccountNumber.setText(add9);
                String add10 = rs.getString("AccountID");
                jTextFieldAccountID.setText(add10);
                String add12 = rs.getString("AccountType");
                jTextFieldType.setText(add12);
                String add131 = rs.getString("SecurityQuestion");
                jTextFieldSecurityQuestion.setText(add131);
                String add141 = rs.getString("Answer");
                jTextFieldAnswer.setText(add141);
                String add151 = rs.getString("Ac_Opening_Date");
                jTextFieldAcOpeningDate.setText(add151);
                jTextFieldUserAccountNUmber.setText("");
                
                rs.close();
                pst.close();
                Calendar();
            }

            else{
                JOptionPane.showMessageDialog(null, "Enter Correct Account Number.");
            }

        }
        

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }
        }
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jButtonPasswordCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasswordCreateActionPerformed
        // TODO add your handling code here:
        randomPassword();
    }//GEN-LAST:event_jButtonPasswordCreateActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        jTextFieldAccountNumber1.setText("");
        jTextFieldAccountID1.setText("");
        jTextFieldPassword1.setText("");
        jTextFieldanswer1.setText("");
        jTextFieldCreditAmount1.setText("");
        jTextFieldName1.setText("");
        jTextFieldMobileNumber1.setText("");
        jTextFieldAddress1.setText("");
        jTextFieldNIDNumber1.setText("");
        jTextFieldBCNumber1.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonNewAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewAccountActionPerformed
        // TODO add your handling code here:

        String query = "insert into dbaccountinformation(AccountNumber,AccountID,Ac_Opening_Date,Password,AccountType,SecurityQuestion,Answer,Name,DateofBirth,Gender,Religon,MobileNumber,Address,NIDNumber,BCNumber,Balance)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldAccountNumber1.getText());
            pst.setString(2, jTextFieldAccountID1.getText());
            pst.setString(3, date);
            pst.setString(4, jTextFieldPassword1.getText());
            pst.setString(5, (String)jComboBoxAccountType1.getSelectedItem());
            pst.setString(6, (String)jTextFieldQuestion1.getSelectedItem());
            pst.setString(7, jTextFieldanswer1.getText());
            pst.setString(8, jTextFieldName1.getText());
            pst.setString(9, ((JTextField)jDateChooserBirthDate1.getDateEditor().getUiComponent()).getText());
            jRadioButtonMale1.setActionCommand("Male");
            jRadioButtonFemale1.setActionCommand("Female");
            jRadioButtonOther1.setActionCommand("Other");
            pst.setString(10, buttonGroup1.getSelection().getActionCommand());
            pst.setString(11, (String)jComboBoxReligon1.getSelectedItem());
            pst.setString(12, jTextFieldMobileNumber1.getText());
            pst.setString(13, jTextFieldAddress1.getText());
            pst.setString(14, jTextFieldNIDNumber1.getText());
            pst.setString(15, jTextFieldBCNumber1.getText());
            pst.setString(16, jTextFieldCreditAmount1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Account has been created!");
            //balance();
            Calendar();
            

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        AccountOpeningHistory();
    }//GEN-LAST:event_jButtonNewAccountActionPerformed

    private void jTextFieldGenderDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGenderDAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGenderDAActionPerformed

    private void jTextFieldDoBDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDoBDAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDoBDAActionPerformed

    private void jTextFieldBCNumberDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBCNumberDAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBCNumberDAActionPerformed

    private void jButtonAccountNumberSearchDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountNumberSearchDAActionPerformed
        // TODO add your handling code here:
        
        String acSearch = jTextFieldAccountNumberDA.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListLS(acSearchValue);
        
        if(result == 1){
        String query = "select * from dbaccountinformation where AccountNumber = ?";
        try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldAccountNumberDA.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                String add71 = rs.getString("Name");
                jTextFieldNameDA.setText(add71);
                String add72 = rs.getString("DateofBirth");
                jTextFieldDoBDA.setText(add72);
                String add73 = rs.getString("Gender");
                jTextFieldGenderDA.setText(add73);
                String add74 = rs.getString("MobileNumber");
                jTextFieldMobileNumberDA.setText(add74);
                String add75 = rs.getString("NIDNumber");
                jTextFieldNIDNumberDA.setText(add75);
                String add76 = rs.getString("BCNumber");
                jTextFieldBCNumberDA.setText(add76);
                String add77 = rs.getString("AccountType");
                jTypeDA.setText(add77);
                String add78 = rs.getString("Balance");
                jTextFieldBalanceDA.setText(add78);
                rs.close();
                pst.close();
                Calendar();
                
            }

            else{
                JOptionPane.showMessageDialog(null, "Enter Correct Account Number.");
            }

        }
        
        

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){

            }
        }
        }
        
    }//GEN-LAST:event_jButtonAccountNumberSearchDAActionPerformed

    private void jButtonClearDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearDAActionPerformed
        // TODO add your handling code here:
        jTextFieldAccountNumberDA.setText("");
        jTextFieldGenderDA.setText("");
        jTextFieldNameDA.setText("");
        jTextFieldDoBDA.setText("");
        jTextFieldMobileNumberDA.setText("");
        jTextFieldNIDNumberDA.setText("");
        jTextFieldBCNumberDA.setText("");
        jTypeDA.setText("");
        jTextFieldBalanceDA.setText("");
    }//GEN-LAST:event_jButtonClearDAActionPerformed

    private void jButtonDeleteDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteDAActionPerformed
        // TODO add your handling code here:
         try{
            String deletewAccountNumber = jTextFieldAccountNumberDA.getText();
            String query1 = "delete from dbaccountinformation where AccountNumber = '"+deletewAccountNumber+"'";
            pst = con.prepareStatement(query1);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Account has been deleted successfully!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonDeleteDAActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        // TODO add your handling code here:
         try{
            
            jTextAreaPrint.print();
            Calendar();
        }
        
        catch(Exception e){
            
            System.err.format("No Printer Prond", e.getMessage());
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jButtonClearTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearTMActionPerformed
        // TODO add your handling code here:
            jTextFieldSenderAccountNumber.setText("");
            jTextFieldSenderName.setText("");
            jTextFieldSenderMobileNumber.setText("");
            jTextFieldSenderCurrentBalance.setText("");
            jTextFieldTransferAmount.setText("");
            jTextFieldSenderNewBalance.setText("");
            jTextFieldReceiverAccountNumber.setText("");
            jTextFieldReceiverName.setText("");
            jTextFieldReceiverMobileNumber.setText("");
            jTextFieldReceiverCurrentBalance.setText("");
            jTextFieldReceiverNewBalance.setText("");
    }//GEN-LAST:event_jButtonClearTMActionPerformed

    private void jButtonViewHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewHActionPerformed
        // TODO add your handling code here:
        
        String acSearch = jTextFieldSearchST.getText();
        int acSearchValue = Integer.parseInt(acSearch);
        int result = AccountNumberListBS(acSearchValue);
        
        if(result == 1){
           
        
        String query = "select 	SL, AccountNumber, Ac_Opening_Date, Date, Credit, Debit, T_Send, T_Receive, T_Number, Balance From  statement where AccountNumber = ?";
         try{
            pst = con.prepareStatement(query);
            pst.setString(1, jTextFieldSearchST.getText());
            rs = pst.executeQuery();
            jTableStatement.setModel(DbUtils.resultSetToTableModel(rs));
            Calendar();
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Enter correct account number!");
        }
        
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        }
        
    }//GEN-LAST:event_jButtonViewHActionPerformed

    private void jTextFieldNote100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNote100ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNote100ActionPerformed

    private void jButtonClearDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearDMActionPerformed
        // TODO add your handling code here:
            jTextFieldAccountNumberd.setText("");
            jTextFieldNamed.setText("");
            jTextFieldMobileNumberd.setText("");
            jTextFieldCurrentBalanced.setText("");
            jTextFieldCreditAmountd.setText("");
            jTextFieldNewBalanceDeposit.setText("");
    }//GEN-LAST:event_jButtonClearDMActionPerformed

    private void jButtonClearWMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearWMActionPerformed
        // TODO add your handling code here:
        jTextFieldAccountNumberw.setText("");
        jTextFieldNamew.setText("");
        jTextFieldMobileNumberw.setText("");
        jTextFieldCurrentBalancew.setText("");
        jTextFieldCreditAmountw.setText("");
        jTextFieldNewBalancew.setText("");
    }//GEN-LAST:event_jButtonClearWMActionPerformed

    private void jButtonViewCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewCMActionPerformed
        // TODO add your handling code here:
        
        String amoumtCM = jTextFieldAmountCM.getText();
        int amount = Integer.parseInt(amoumtCM);
        int note1000 = amount / 1000;
        amount = amount % 1000;
        int note500 = amount / 500;
        amount = amount % 500;
        int note100 = amount / 100;
        amount = amount % 100;
        int note50 = amount / 50;
        amount = amount % 50;
        int note20 = amount / 20;
        amount = amount % 20;
        int note10 = amount / 10;
        amount = amount % 10;
        int note5 = amount / 5;
        amount = amount % 5;
        int note2 = amount / 2;
        amount = amount % 2;
        int coin1 = amount / 1;
        
        String nNote1000 = String.valueOf(note1000);
        jTextFieldNote1000.setText(nNote1000);
        String nNote500 = String.valueOf(note500);
        jTextFieldNote500.setText(nNote500);
        String nNote100 = String.valueOf(note100);
        jTextFieldNote100.setText(nNote100);
        String nNote50 = String.valueOf(note50);
        jTextFieldNote50.setText(nNote50);
        String nNote20 = String.valueOf(note20);
        jTextFieldNote20.setText(nNote20);
        String nNote10 = String.valueOf(note10);
        jTextFieldNote10.setText(nNote10);
        String nNote5 = String.valueOf(note5);
        jTextFieldNote5.setText(nNote5);
        String nNote2 = String.valueOf(note2);
        jTextFieldNote2.setText(nNote2);
        String nCoin1= String.valueOf(coin1);
        jTextFieldCoin1.setText(nCoin1);
        Calendar();
        
    }//GEN-LAST:event_jButtonViewCMActionPerformed

    private void jButtonClearCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearCMActionPerformed
        // TODO add your handling code here:
        jTextFieldAmountCM.setText("");
        jTextFieldNote1000.setText("");
        jTextFieldNote500.setText("");
        jTextFieldNote100.setText("");
        jTextFieldNote50.setText("");
        jTextFieldNote20.setText("");
        jTextFieldNote10.setText("");
        jTextFieldNote5.setText("");
        jTextFieldNote2.setText("");
        jTextFieldCoin1.setText("");
    }//GEN-LAST:event_jButtonClearCMActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonClearPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearPrintActionPerformed
        // TODO add your handling code here:
        PrintTittle();
    }//GEN-LAST:event_jButtonClearPrintActionPerformed

    private void jButtonUpdateDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateDatabaseActionPerformed
        // TODO add your handling code here:
        TalbleACList();
        HistoryTable();
        Calendar();
    }//GEN-LAST:event_jButtonUpdateDatabaseActionPerformed

    public void HistoryTable(){
        
        String query = "select SL, AccountNumber, Ac_Opening_Date, Date, Credit, Debit, T_Send, T_Receive, T_Number, Balance From  statement";
         try{
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            jTableHistory.setModel(DbUtils.resultSetToTableModel(rs));
        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally{
            try{
                rs.close();
                pst.close();
            }

            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
        
    
    public void SenderTransfer(){
        try{
            String senderAccountNumber = jTextFieldSenderAccountNumber.getText();
            String senderNewAmountUpdate = jTextFieldSenderNewBalance.getText();
            String query1 = "update dbaccountinformation set Balance = '"+senderNewAmountUpdate+"' where AccountNumber = '"+senderAccountNumber+"'";
            pst = con.prepareStatement(query1);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Deposit has been done successfully!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void randomPassword(){
        Random ran = new Random();
        jTextFieldPassword1.setText("" + ran.nextInt(1000000 + 1));
    }
        
    
    
    public void randomPasswordChangeEployee(){
        Random ran = new Random();
        jTextFieldNewPasswordPC.setText("" + ran.nextInt(1000000 + 1));
    }
    public void ReceiverTransfer(){
        try{
            String receiverAccountNumber = jTextFieldReceiverAccountNumber.getText();
            String receiverNewAmountUpdate = jTextFieldReceiverNewBalance.getText();
            String query2 = "update dbaccountinformation set Balance = '"+receiverNewAmountUpdate+"' where AccountNumber = '"+receiverAccountNumber+"'";
            pst = con.prepareStatement(query2);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deposit has been done successfully!");
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAccountNumberSearchDA;
    private javax.swing.JButton jButtonChangePasswordPC;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonClearCB;
    private javax.swing.JButton jButtonClearCM;
    private javax.swing.JButton jButtonClearDA;
    private javax.swing.JButton jButtonClearDM;
    private javax.swing.JButton jButtonClearPC;
    private javax.swing.JButton jButtonClearPrint;
    private javax.swing.JButton jButtonClearTM;
    private javax.swing.JButton jButtonClearVP;
    private javax.swing.JButton jButtonClearWM;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JButton jButtonConfirmw;
    private javax.swing.JButton jButtonConfrimTransfer;
    private javax.swing.JButton jButtonDeleteDA;
    private javax.swing.JButton jButtonDeposit;
    private javax.swing.JButton jButtonDepositw;
    private javax.swing.JButton jButtonEditInformation;
    private javax.swing.JButton jButtonEmployeeNumberSearchPC;
    private javax.swing.JButton jButtonEmployeePasswordCreate;
    private javax.swing.JButton jButtonNewAccount;
    private javax.swing.JButton jButtonPasswordCreate;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonSaveInformation;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSearchReceiver;
    private javax.swing.JButton jButtonSearchSender;
    private javax.swing.JButton jButtonSearchw;
    private javax.swing.JButton jButtonSearchw1;
    private javax.swing.JButton jButtonTransferMoney;
    private javax.swing.JButton jButtonUpdateDatabase;
    private javax.swing.JButton jButtonView;
    private javax.swing.JButton jButtonViewCM;
    private javax.swing.JButton jButtonViewH;
    private javax.swing.JComboBox<String> jComboBoxAccountType1;
    private javax.swing.JComboBox<String> jComboBoxReligon1;
    private com.toedter.calendar.JDateChooser jDateChooserBirthDate1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonFemale1;
    private javax.swing.JRadioButton jRadioButtonMale1;
    private javax.swing.JRadioButton jRadioButtonOther1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAcList;
    private javax.swing.JTable jTableHistory;
    private javax.swing.JTable jTableStatement;
    private javax.swing.JTextArea jTextAreaPrint;
    private javax.swing.JTextField jTextFieldAcOpeningDate;
    private javax.swing.JTextField jTextFieldAccountID;
    private javax.swing.JTextField jTextFieldAccountID1;
    private javax.swing.JTextField jTextFieldAccountNumber;
    private javax.swing.JTextField jTextFieldAccountNumber1;
    private javax.swing.JTextField jTextFieldAccountNumberCB;
    private javax.swing.JTextField jTextFieldAccountNumberDA;
    private javax.swing.JTextField jTextFieldAccountNumberd;
    private javax.swing.JTextField jTextFieldAccountNumberw;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldAddress1;
    private javax.swing.JTextField jTextFieldAmountCM;
    private javax.swing.JTextField jTextFieldAnswer;
    private javax.swing.JTextField jTextFieldBCNumber;
    private javax.swing.JTextField jTextFieldBCNumber1;
    private javax.swing.JTextField jTextFieldBCNumberDA;
    private javax.swing.JTextField jTextFieldBalanceDA;
    private javax.swing.JTextField jTextFieldCoin1;
    private javax.swing.JTextField jTextFieldCreditAmount1;
    private javax.swing.JTextField jTextFieldCreditAmountd;
    private javax.swing.JTextField jTextFieldCreditAmountw;
    private javax.swing.JTextField jTextFieldCurrenPasswordPC;
    private javax.swing.JTextField jTextFieldCurrentBalanceCB;
    private javax.swing.JTextField jTextFieldCurrentBalanced;
    private javax.swing.JTextField jTextFieldCurrentBalancew;
    private javax.swing.JTextField jTextFieldDateofBirth;
    private javax.swing.JTextField jTextFieldDoBDA;
    private javax.swing.JTextField jTextFieldEmployeeIDPC;
    private javax.swing.JTextField jTextFieldEmployeeNamePC;
    private javax.swing.JTextField jTextFieldEmployeeNumberPC;
    private javax.swing.JTextField jTextFieldGender;
    private javax.swing.JTextField jTextFieldGenderDA;
    private javax.swing.JTextField jTextFieldMobileNumber;
    private javax.swing.JTextField jTextFieldMobileNumber1;
    private javax.swing.JTextField jTextFieldMobileNumberCB;
    private javax.swing.JTextField jTextFieldMobileNumberDA;
    private javax.swing.JTextField jTextFieldMobileNumberd;
    private javax.swing.JTextField jTextFieldMobileNumberw;
    private javax.swing.JTextField jTextFieldNIDNumber;
    private javax.swing.JTextField jTextFieldNIDNumber1;
    private javax.swing.JTextField jTextFieldNIDNumberDA;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldName1;
    private javax.swing.JTextField jTextFieldNameCB;
    private javax.swing.JTextField jTextFieldNameDA;
    private javax.swing.JTextField jTextFieldNamed;
    private javax.swing.JTextField jTextFieldNamew;
    private javax.swing.JTextField jTextFieldNewBalanceDeposit;
    private javax.swing.JTextField jTextFieldNewBalancew;
    private javax.swing.JTextField jTextFieldNewPasswordPC;
    private javax.swing.JTextField jTextFieldNote10;
    private javax.swing.JTextField jTextFieldNote100;
    private javax.swing.JTextField jTextFieldNote1000;
    private javax.swing.JTextField jTextFieldNote2;
    private javax.swing.JTextField jTextFieldNote20;
    private javax.swing.JTextField jTextFieldNote5;
    private javax.swing.JTextField jTextFieldNote50;
    private javax.swing.JTextField jTextFieldNote500;
    private javax.swing.JTextField jTextFieldPassword1;
    private javax.swing.JComboBox<String> jTextFieldQuestion1;
    private javax.swing.JTextField jTextFieldReceiverAccountNumber;
    private javax.swing.JTextField jTextFieldReceiverCurrentBalance;
    private javax.swing.JTextField jTextFieldReceiverMobileNumber;
    private javax.swing.JTextField jTextFieldReceiverName;
    private javax.swing.JTextField jTextFieldReceiverNewBalance;
    private javax.swing.JTextField jTextFieldReligion;
    private javax.swing.JTextField jTextFieldSearchST;
    private javax.swing.JTextField jTextFieldSecurityQuestion;
    private javax.swing.JTextField jTextFieldSenderAccountNumber;
    private javax.swing.JTextField jTextFieldSenderCurrentBalance;
    private javax.swing.JTextField jTextFieldSenderMobileNumber;
    private javax.swing.JTextField jTextFieldSenderName;
    private javax.swing.JTextField jTextFieldSenderNewBalance;
    private javax.swing.JTextField jTextFieldTransferAmount;
    private javax.swing.JTextField jTextFieldType;
    private javax.swing.JTextField jTextFieldUserAccountNUmber;
    private javax.swing.JTextField jTextFieldanswer1;
    private javax.swing.JTextField jTypeDA;
    // End of variables declaration//GEN-END:variables
}
