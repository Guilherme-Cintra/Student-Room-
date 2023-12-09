package ca.qc.castroguilherme.roomstudentfragment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {
    //Prendre tous les étudiants
    @Query("Select * from student_table ORDER BY firstName ASC")
    fun getStudents() : LiveData<List<Student>>

    //Supprimer de la base de données
    @Query("SELECT * FROM student_table WHERE id=(:id)")
    fun getStudent(id: Int): LiveData<Student?>
    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("DELETE FROM student_table")
    fun deleteAll()


}