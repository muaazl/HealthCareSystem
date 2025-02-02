package model;

public class PatientModel {
    private String Patient_id;
    private String Patient_name;
    private String Patient_email;
    private String Patient_no;
    private String Patient_since;
    private String Patient_address;

    public PatientModel(String Patient_id,String Patient_name, String Patient_email, String Patient_no,String Patient_since,String Patient_address){
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;
        this.Patient_email = Patient_email;
        this.Patient_no = Patient_no;
        this.Patient_since = Patient_since;
        this.Patient_address= Patient_address;


    }


    public String getPatient_address() {
        return Patient_address;
    }

    public void setPatient_address(String patient_address) {
        Patient_address = patient_address;
    }
    public String getPatient_since() {
        return Patient_since;
    }
    public void setPatient_since(String patient_since) {
        Patient_since = patient_since;
    }

    public String getPatient_id() {
        return Patient_id;
    }
    public void setPatient_id(String patient_id) {
        Patient_id = patient_id;
    }
    public String getPatient_name() {
        return Patient_name;
    }
    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }
    public String getPatient_email() {
        return Patient_email;
    }
    public void setPatient_email(String patient_email) {
        Patient_email = patient_email;
    }
    public String getPatient_no() {
        return Patient_no;
    }
    public void setPatient_no(String patient_no) {
        Patient_no = patient_no;
    }
}
