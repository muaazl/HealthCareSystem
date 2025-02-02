package controller;

import model.DoctorModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;


public class DoctorController {

    private Connection connection;

    public DoctorController() {
        this.connection = DatabaseConnection.getConnection();

    }


    // Add a new doctor to the database
    public void addDoctor(String d_id, String d_name, String d_No, String email, String specialization, String location) throws SQLException {
        String query = "INSERT INTO doctors (Doctor_id, Doctor_name, Doctor_no, Doctor_email, Specialization, location) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, d_id);
            pstmt.setString(2, d_name);
            pstmt.setString(3, d_No);
            pstmt.setString(4, email);
            pstmt.setString(5, specialization);
            pstmt.setString(6, location);
            pstmt.executeUpdate();
        }
    }

    // Retrieve all doctors from the database
    public List<DoctorModel> getAllDoctors() throws SQLException {
        List<DoctorModel> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)){

            while (rs.next()) {
                DoctorModel doctor = new DoctorModel(
                        rs.getString("Doctor_id"),
                        rs.getString("Doctor_name"),
                        rs.getString("Doctor_no"),
                        rs.getString("Doctor_email"),
                        rs.getString("Specialization"),
                        rs.getString("location")
                );
                doctors.add(doctor);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return doctors;
    }

    public DoctorModel getDoctorById(String doctorId)  {
        DoctorModel doctor = null;
        String query = "SELECT * FROM doctors WHERE Doctor_id = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query))
        {
            stmt.setString(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                doctor = new DoctorModel(
                        rs.getString("Doctor_id"),
                        rs.getString("Doctor_name"),
                        rs.getString("Doctor_no"),
                        rs.getString("Doctor_email"),
                        rs.getString("Specialization"),
                        rs.getString("location")
                );
            }


        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return doctor;

    }
    // Update an existing doctor in the database
    public void updateDoctor(String d_id, String d_name, String d_No, String email, String specialization, String location) throws SQLException {
        String query = "UPDATE doctors SET Doctor_name = ?, Doctor_no = ?, Doctor_email = ?, Specialization = ?, location = ? WHERE Doctor_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, d_name);
            pstmt.setString(2, d_No);
            pstmt.setString(3, email);
            pstmt.setString(4, specialization);
            pstmt.setString(5, location);
            pstmt.setString(6, d_id);
            pstmt.executeUpdate();
        }
    }

    // Delete a doctor from the database
    public void deleteDoctor(String d_id) throws SQLException {
        String query = "DELETE FROM doctors WHERE Doctor_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, d_id);
            pstmt.executeUpdate();
        }
    }


}