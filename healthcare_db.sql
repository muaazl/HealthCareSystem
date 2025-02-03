-- Drop the database if it exists
DROP DATABASE IF EXISTS healthcare_db;

-- Create the database
CREATE DATABASE healthcare_db;
USE healthcare_db;

-- Drop and create the appointments table
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments` (
  `Appointment_id` INT NOT NULL AUTO_INCREMENT,
  `Patient_id` VARCHAR(50) DEFAULT NULL,
  `Doctor_id` VARCHAR(50) DEFAULT NULL,
  `Appointment_description` VARCHAR(255) DEFAULT NULL,
  `Appointment_status` ENUM('Pending','Confirmed','Cancelled','Completed') DEFAULT 'Pending',
  `Appointment_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`Appointment_id`),
  KEY `Patient_id` (`Patient_id`),
  KEY `Doctor_id` (`Doctor_id`)
);

-- Insert sample data into appointments
INSERT INTO `appointments` (`Patient_id`, `Doctor_id`, `Appointment_description`, `Appointment_status`, `Appointment_date`) VALUES
('P001', 'D003', 'Routine check-up for flu symptoms', 'Confirmed', '2025-02-05 10:30:00'),
('P002', 'D005', 'Consultation for knee pain', 'Pending', '2025-02-06 14:00:00'),
('P003', 'D002', 'Follow-up on previous dental surgery', 'Completed', '2025-01-30 09:00:00'),
('P002', 'D001', 'Annual physical examination', 'Cancelled', '2025-02-07 11:00:00'),
('P002', 'D002', 'Corona virus consultation', 'Confirmed', '2025-05-05 12:30:00'),
('P001', 'D002', 'Malaria virus check-up', 'Pending', '2024-01-03 12:21:00');

-- Drop and create the doctors table
DROP TABLE IF EXISTS `doctors`;
CREATE TABLE `doctors` (
  `Doctor_id` VARCHAR(50) NOT NULL,
  `Doctor_name` VARCHAR(100) NOT NULL,
  `Doctor_no` VARCHAR(15) NOT NULL,
  `Doctor_email` VARCHAR(100) DEFAULT NULL,
  `Specialization` VARCHAR(255) DEFAULT NULL,
  `Location` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`Doctor_id`)
);

-- Insert sample data into doctors
INSERT INTO `doctors` (`Doctor_id`, `Doctor_name`, `Doctor_no`, `Doctor_email`, `Specialization`, `Location`) VALUES
('D001', 'John Doe', '1234567890', 'contact@medisupply.com', 'Cardiologist', 'New York, USA'),
('D002', 'Abdullah', '0987654321', 'info@pharmaworld.com', 'Heart Specialist', 'Los Angeles, USA'),
('D003', 'Rathnayake', '1122334455', 'support@healthplus.com', 'Therapist', 'Chicago, USA');

-- Drop and create the doctor_schedules table
DROP TABLE IF EXISTS `doctor_schedules`;
CREATE TABLE `doctor_schedules` (
  `Schedule_id` INT NOT NULL AUTO_INCREMENT,
  `Doctor_id` VARCHAR(50) DEFAULT NULL,
  `Day_of_week` VARCHAR(20) NOT NULL,
  `Status` VARCHAR(20) DEFAULT 'Available',
  `Room_number` VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`Schedule_id`),
  KEY `Doctor_id` (`Doctor_id`)
);

-- Insert sample data into doctor_schedules
INSERT INTO `doctor_schedules` (`Doctor_id`, `Day_of_week`, `Status`, `Room_number`) VALUES
('D001', 'Monday', 'Available', '101'),
('D001', 'Wednesday', 'Available', '101'),
('D002', 'Tuesday', 'Available', '102'),
('D003', 'Monday', 'Available', '122'),
('D004', 'Monday', 'Available', '234');

-- Drop and create the inventory table
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `Drug_id` INT NOT NULL AUTO_INCREMENT,
  `Drug_name` VARCHAR(255) NOT NULL,
  `Quantity_stock` INT NOT NULL,
  `Min_stock_level` INT NOT NULL,
  `Supplier_info` TEXT,
  `Purchase_price` DECIMAL(10,2) NOT NULL,
  `Selling_price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`Drug_id`)
);

-- Insert sample data into inventory
INSERT INTO `inventory` (`Drug_name`, `Quantity_stock`, `Min_stock_level`, `Supplier_info`, `Purchase_price`, `Selling_price`) VALUES
('Paracetamol', 100, 10, 'Supplier A, Contact: 123-456-7890', 2.50, 5.99),
('Amoxicillin', 50, 5, 'Supplier B, Contact: 234-567-8901', 4.00, 9.99),
('Cough Syrup', 75, 8, 'Supplier C, Contact: 345-678-9012', 3.50, 6.99);

-- Drop and create the patients table
DROP TABLE IF EXISTS `patients`;
CREATE TABLE `patients` (
  `Patient_id` VARCHAR(50) NOT NULL,
  `Patient_name` VARCHAR(100) NOT NULL,
  `Patient_email` VARCHAR(100) NOT NULL,
  `Patient_no` VARCHAR(20) NOT NULL,
  `Patient_since` DATE NOT NULL,
  `Patient_address` TEXT NOT NULL,
  PRIMARY KEY (`Patient_id`),
  UNIQUE KEY `Patient_email` (`Patient_email`)
);

-- Insert sample data into patients
INSERT INTO `patients` (`Patient_id`, `Patient_name`, `Patient_email`, `Patient_no`, `Patient_since`, `Patient_address`) VALUES
('P001', 'John Doe', 'john.doe@example.com', '1234567890', '2023-01-01', '123 Elm Street, Springfield'),
('P002', 'Jane Smith', 'jane.smith@example.com', '0987654321', '2023-02-15', '456 Oak Avenue, Metropolis'),
('P003', 'Doe Smith', 'doe.smith@example.com', '0969696921', '2023-12-25', '456 Base Street, Los Angeles');
