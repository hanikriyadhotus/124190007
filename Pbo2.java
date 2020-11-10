/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Akira
 */
public class Pbo2 extends JFrame implements ActionListener, ListSelectionListener{

    private JTextField nim, nama;
    private JRadioButton lk, pr;
    private ButtonGroup group;
    private JTextArea alamat;
    private JTable tabel;
    private DefaultTableModel model;
    private JButton simpan, batal;
    
    public Pbo2() {
        
        setTitle("DATA MAHASISWA SISTEM INFORMASI");
        
        setSize(420, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        tampilanUser();
        setVisible(true);
    }
    
    private void tampilanUser(){
        JPanel pan1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pan1.setPreferredSize(new Dimension(400, 250));
        
        JLabel l_nim = new JLabel("NIM");
        l_nim.setPreferredSize(new Dimension(120, 30));
        pan1.add(l_nim);
        nim = new JTextField();
        nim.setPreferredSize(new Dimension(250, 30));
        pan1.add(nim);
        
        JLabel l_nama = new JLabel("Nama");
        l_nama.setPreferredSize(new Dimension(120, 30));
        pan1.add(l_nama);
        nama = new JTextField();
        nama.setPreferredSize(new Dimension(250, 30));
        pan1.add(nama);
        
        JLabel l_jns = new JLabel("Jenis Kelamin");
        l_jns.setPreferredSize(new Dimension(120, 30));
        pan1.add(l_jns);
        JPanel pan2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lk = new JRadioButton("Laki - Laki");
        lk.setPreferredSize(new Dimension(100, 30));
        lk.setActionCommand("L");
        pan2.add(lk);
        
        pr = new JRadioButton("Perempuan");
        pr.setPreferredSize(new Dimension(100, 30));
        pr.setActionCommand("P");
        pan2.add(pr);
        
        group = new ButtonGroup();
        group.add(lk);
        group.add(pr);
        
        pan1.add(pan2);
        
        JLabel l_alamat = new JLabel("Alamat");
        l_alamat.setPreferredSize(new Dimension(120, 30));
        pan1.add(l_alamat);
        alamat = new JTextArea();
        alamat.setPreferredSize(new Dimension(250, 60));
        pan1.add(alamat);
        
        JPanel pan3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pan3.setPreferredSize(new Dimension(390, 50));
        simpan = new JButton("Simpan");
        simpan.setPreferredSize(new Dimension(80, 30));
        pan3.add(simpan);
        simpan.addActionListener(this);
        
        batal = new JButton("Batal");
        batal.setPreferredSize(new Dimension(80, 30));
        pan3.add(batal);
        batal.addActionListener(this);
        
        pan1.add(pan3);
        
        model = new DefaultTableModel();
        tabel = new JTable(model);
        model.setColumnIdentifiers(new Object[]{"NIM","Nama", "Jenis Kelamin", "Alamat"});
        tabel.getSelectionModel().addListSelectionListener(this);
        getContentPane().add(new JScrollPane(tabel));
        
        getContentPane().add(pan1, "North");
    }
    
    private void clear(){
        nama.setText("");
        group.clearSelection();
        alamat.setText("");
    }
    
    private void simpan(){
        String jns = "";
        if(lk.isSelected()){
            jns = lk.getActionCommand();
        }else{
            jns = pr.getActionCommand();
        }
        model.addRow(new Object[]{nim.getText(),nama.getText(), jns, alamat.getText()});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==simpan){
            simpan();
            clear();
        } else 
            if(e.getSource()==batal){
                clear();
            }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = tabel.getSelectedRow();
        if(row >= 0){
            nama.setText(tabel.getValueAt(row, 0).toString());
            if(tabel.getValueAt(row, 1).toString().equals("L")){
                lk.setSelected(true);
            } else {
                pr.setSelected(true);
            }
            alamat.setText(tabel.getValueAt(row, 2).toString());
        }
    }
    
    public static void main(String[] args){
        Pbo2 obj = new Pbo2();
    }

    private static class c {

        public c() {
        }

        private static class setBackground {

            public setBackground() {
            }
        }
    }
}