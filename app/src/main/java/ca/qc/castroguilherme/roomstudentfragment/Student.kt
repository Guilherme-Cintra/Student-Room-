package ca.qc.castroguilherme.roomstudentfragment

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "firstName")
    var firstName: String?,
    @ColumnInfo(name = "last_name")
    var lastName: String?,
    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String?,
    @ColumnInfo(name = "email")
    var email: String?
) : Serializable
