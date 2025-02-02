package controller;

import model.PatientModel;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private Connection connection;

    public PatientController() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<PatientModel> getAllPatients() {
        List<PatientModel> patients = new ArrayList<>();
        String query = "SELECT * FROM patients"; // Adjust based on your patients table
        try (PreparedStatement pst = connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                PatientModel patient = new PatientModel(
                        rs.getString("Patient_id"),
                        rs.getString("Patient_name"),
                        rs.getString("Patient_email"),
                        rs.getString("Patient_no"),
                        rs.getString("Patient_since"),
                        rs.getString("Patient_address")

                );

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }


    public PatientModel getPatientById(String patientId) {
        PatientModel patient = null;
        String query = "SELECT * FROM patients WHERE patient_id = ?"; // Adjust based on your patient table and query for 1 record
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, patientId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                patient = new PatientModel(
                        rs.getString("Patient_id"),
                        rs.getString("Patient_name"),
                        rs.getString("Patient_email"),
                        rs.getString("Patient_no"),
                        rs.getString("Patient_since"),
                        rs.getString("Patient_address")
                );

            }
        }catch(SQLException e){
            e.printStackTrace();

        }
        return patient;
    }


    public void createPatient(PatientModel patient) {
        String query = "INSERT INTO patients (Patient_id, Patient_name, Patient_email, Patient_no, Patient_since, Patient_address) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getPatient_id());
            stmt.setString(2, patient.getPatient_name());
            stmt.setString(3, patient.getPatient_email());
            stmt.setString(4, patient.getPatient_no());
            stmt.setString(5, patient.getPatient_since());
            stmt.setString(6, patient.getPatient_address());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePatient(PatientModel patient) {
        String query = "UPDATE patients SET Patient_name = ?, Patient_email = ?, Patient_no = ?, Patient_since = ?, Patient_address = ? WHERE Patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getPatient_name());
            stmt.setString(2, patient.getPatient_email());
            stmt.setString(3, patient.getPatient_no());
            stmt.setString(4, patient.getPatient_since());
            stmt.setString(5, patient.getPatient_address());
            stmt.setString(6, patient.getPatient_id());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void removePatient(String patientId) {
        String query = "DELETE FROM patients WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1,patientId);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}