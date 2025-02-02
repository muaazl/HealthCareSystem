package model;

public class AppointmentModel {
    private String Appointment_id;
    private String Patient_id;
    private String Doctor_id;
    private String Appointment_description;
    private String Appointment_status;
    private String Appointment_date;


    public AppointmentModel(String Appointment_id,String Patient_id, String Doctor_id,String Appointment_description,String Appointment_status, String Appointment_date ){

        this.Appointment_id = Appointment_id;
        this.Patient_id = Patient_id;
        this.Doctor_id= Doctor_id;
        this.Appointment_description = Appointment_description;
        this.Appointment_status= Appointment_status;
        this.Appointment_date= Appointment_date;


    }

    public String getAppointmentId() {
        return Appointment_id;
    }
    public void setAppointmentId(String appointment_id) {
        Appointment_id = appointment_id;
    }
    public String getPatientId() {
        return Patient_id;
    }
    public void setPatientId(String patientId) {
        Patient_id = patientId;
    }
    public String getDoctorId() {
        return Doctor_id;
    }
    public void setDoctorId(String doctorId) {
        Doctor_id = doctorId;
    }
    public String getAppointmentDescription() {
        return Appointment_description;
    }
    public void setAppointmentDescription(String appointmentDescription) {
        Appointment_description = appointmentDescription;
    }

    public String getAppointmentStatus() {
        return Appointment_status;
    }
    public void setAppointmentStatus(String appointmentStatus) {
        Appointment_status = appointmentStatus;
    }

    public String getAppointmentDate() {
        return Appointment_date;
    }
    public void setAppointmentDate(String appointmentDate) {
        Appointment_date= appointmentDate;
    }
}
