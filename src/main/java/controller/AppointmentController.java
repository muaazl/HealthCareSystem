package controller;

import model.AppointmentModel;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    private Connection connection;


    public AppointmentController() {
        this.connection = DatabaseConnection.getConnection();
    }


    public List<AppointmentModel> getAllAppointments()  {
        List<AppointmentModel> appointmentList = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try( PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()){
            while(rs.next()) {
                AppointmentModel appointment = new AppointmentModel(
                        rs.getString("Appointment_id"),
                        rs.getString("Patient_id"),
                        rs.getString("Doctor_id"),
                        rs.getString("Appointment_description"),
                        rs.getString("Appointment_status"),
                        rs.getString("Appointment_date")


                );

                appointmentList.add(appointment);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return appointmentList;

    }

    public void addAppointment(AppointmentModel appointment)  {

        String query = "INSERT INTO appointments (Patient_id, Doctor_id, Appointment_description, Appointment_status, Appointment_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, appointment.getPatientId());
            stmt.setString(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentDescription());
            stmt.setString(4, appointment.getAppointmentStatus());
            stmt.setString(5, appointment.getAppointmentDate());

            stmt.executeUpdate();
        }
        catch(SQLException ex)
        {

            ex.printStackTrace();

        }

    }


    public void updateAppointment(AppointmentModel appointment)  {
        String query = "UPDATE appointments SET  Appointment_description = ?, Appointment_status = ?, Appointment_date = ?  WHERE  Appointment_id = ?";
        try(  PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,appointment.getAppointmentDescription());
            stmt.setString(2,appointment.getAppointmentStatus());
            stmt.setString(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getAppointmentId());

            stmt.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    public void removeAppointment(String  appointmentId) {
        String query = "DELETE FROM appointments WHERE Appointment_id = ?";
        try( PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,appointmentId);
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }




}