package com.example.healt_app.dataBase

import androidx.room.*
import androidx.room.Dao

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


@Dao
interface Dao {

    /**
     *   User
     */
    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUserById(id: Int)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserByID(id : Int) : Flow<User>

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    fun getUser(login: String, password: String): Flow<User>

    @Query("SELECT id FROM users WHERE login = :login AND password = :password")
    fun getUserID(login: String, password: String): Int

    @Query("SELECT * FROM users WHERE login = :login ")
    fun getUser(login: String): Flow<User>

    @Query("SELECT * FROM users WHERE roll = :roll")
    fun getPatients(roll: Boolean =false): Flow<List<User>>

    /**
     * Medicine
     */
    @Insert
    fun insertMedicine(medicine: Medicine)

    @Query("DELETE FROM medicine WHERE id = :id")
    fun deleteMedicineById(id: Int)

    @Query("SELECT * FROM medicine WHERE id = :id")
    fun getMedicineByID(id : Int) : Flow<Medicine>
    @Query("SELECT * FROM medicine WHERE patientId = :patientId")
    fun getMedicineByPatientId(patientId: Int): Flow<List<Medicine>>

    @Update
    fun updateMedicine(medicine: Medicine)

    /**
     * Appointment
     */

    @Insert
    fun insertAppointment(appointment: Appointment)

    @Delete
    fun deleteAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments WHERE doctor_id = :doctorId")
    fun getAppointmentsByDoctorId(doctorId: Int): Flow<List<Appointment>>

    @Query("SELECT * FROM appointments WHERE patient_id = :patientId")
    fun getAppointmentsByPatientId(patientId: Int): Flow<List<Appointment>>


    /**
     * Diseases
     */
    @Insert
    fun insertDisease(disease: Disease)

    @Delete
    fun deleteDisease(disease: Disease)

    @Update
    fun updateDisease(disease: Disease)

    @Query("SELECT * FROM diseases WHERE patient_id = :patientId")
    fun getDiseasesByPatientId(patientId: Int): Flow<List<Disease>>







}