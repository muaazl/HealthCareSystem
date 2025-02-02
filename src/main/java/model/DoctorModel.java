package model;

public class DoctorModel {
    private String Doctor_id;
    private String Doctor_name;
    private String Doctor_no;
    private String Doctor_email;
    private String Specialization;
    private String location;


    public DoctorModel(String Doctor_id, String Doctor_name, String Doctor_no, String Doctor_email,String Specialization, String location ) {
        this.Doctor_id = Doctor_id;
        this.Doctor_name = Doctor_name;
        this.Doctor_no = Doctor_no;
        this.Doctor_email = Doctor_email;
        this.Specialization= Specialization;
        this.location = location;

    }

    public String getDoctor_id() {
        return Doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        Doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        Doctor_name = doctor_name;
    }

    public String getDoctor_no() {
        return Doctor_no;
    }

    public void setDoctor_no(String doctor_no) {
        Doctor_no = doctor_no;
    }
    public String getDoctor_email() {
        return Doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        Doctor_email = doctor_email;
    }
    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}