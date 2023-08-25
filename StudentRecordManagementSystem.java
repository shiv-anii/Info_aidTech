package info_aidtech;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private String name;
    private int rollNumber;
    private String address;
    private String phoneNo;

    public Student(String name, int rollNumber, String address, String phoneNo) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}

public class StudentRecordManagementSystem extends JFrame {
    private ArrayList<Student> students;
    private JTextArea outputTextArea;

    public StudentRecordManagementSystem() {
        students = new ArrayList<>();
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        
        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        JButton viewButton = new JButton("View Students");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStudents();
            }
        });
        
        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = "students_data.dat";
                saveToFile(filename);
            }
        });

        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filename = "students_data.dat";
                loadFromFile(filename);
            }
        });
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog(this, "Enter student name:");
        if (name != null && !name.isEmpty()) {
            String rollNumberStr = JOptionPane.showInputDialog(this, "Enter roll number:");
            if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
                int rollNumber = Integer.parseInt(rollNumberStr);

                String address = JOptionPane.showInputDialog(this, "Enter student address:");
                if (address != null && !address.isEmpty()) {
                    String phoneNo = JOptionPane.showInputDialog(this, "Enter student phone number:");
                    if (phoneNo != null && !phoneNo.isEmpty()) {
                        Student student = new Student(name, rollNumber, address, phoneNo);
                        students.add(student);
                        outputTextArea.append("Added: " + name + " (Roll Number: " + rollNumber + ", Address: " + address + ", Phone: " + phoneNo + ")\n");
                    }
                }
            }
        }
    }
    
    private void removeStudent() {
        if (students.isEmpty()) {
            outputTextArea.setText("No students found.");
            return;
        }

        String rollNumberStr = JOptionPane.showInputDialog(this, "Enter the roll number of the student to remove:");
        if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
            int rollNumberToRemove = Integer.parseInt(rollNumberStr);
            Student studentToRemove = null;

            for (Student student : students) {
                if (student.getRollNumber() == rollNumberToRemove) {
                    studentToRemove = student;
                    break;
                }
            }

            if (studentToRemove != null) {
                students.remove(studentToRemove);
                outputTextArea.setText("Student with Roll Number " + rollNumberToRemove + " removed successfully.");
            } else {
                outputTextArea.setText("Student with Roll Number " + rollNumberToRemove + " not found.");
            }
        }
    }

    private void viewStudents() {
        if (students.isEmpty()) {
            outputTextArea.setText("No students found.");
            return;
        }

        outputTextArea.setText("List of Students:\n");
        for (Student student : students) {
            outputTextArea.append("Roll Number: " + student.getRollNumber() +
                                  ", Name: " + student.getName() +
                                  ", Address: " + student.getAddress() +
                                  ", Phone: " + student.getPhoneNo() + "\n");
        }
    }
    
    private void searchStudent() {
        if (students.isEmpty()) {
            outputTextArea.setText("No students found.");
            return;
        }

        String rollNumberStr = JOptionPane.showInputDialog(this, "Enter the roll number of the student to search:");
        if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
            int rollNumberToSearch = Integer.parseInt(rollNumberStr);
            Student foundStudent = null;

            for (Student student : students) {
                if (student.getRollNumber() == rollNumberToSearch) {
                    foundStudent = student;
                    break;
                }
            }

            if (foundStudent != null) {
                outputTextArea.setText("Student Found:\n");
                outputTextArea.append("Roll Number: " + foundStudent.getRollNumber() +
                                      ", Name: " + foundStudent.getName() +
                                      ", Address: " + foundStudent.getAddress() +
                                      ", Phone: " + foundStudent.getPhoneNo() + "\n");
            } else {
                outputTextArea.setText("Student with Roll Number " + rollNumberToSearch + " not found.");
            }
        }
    }
        
        public void saveToFile(String filename) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(students);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void loadFromFile(String filename) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                Object data = ois.readObject();

                if (data instanceof ArrayList<?> && !((ArrayList<?>) data).isEmpty() && ((ArrayList<?>) data).get(0) instanceof Student) {
                    students = (ArrayList<Student>) data;
                    outputTextArea.setText("Data loaded successfully from file: " + filename);
                } else {
                    outputTextArea.setText("Invalid data format in the file.");
                }
            } catch (IOException | ClassNotFoundException e) {
                outputTextArea.setText("Error loading data from file: " + filename);
         }
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentRecordManagementSystem().setVisible(true);
            }
        });
    }
}
