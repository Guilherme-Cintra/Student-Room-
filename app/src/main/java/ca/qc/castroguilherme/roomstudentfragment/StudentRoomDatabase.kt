package ca.qc.castroguilherme.roomstudentfragment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database
    (entities = arrayOf(Student::class), version = 1, exportSchema = false)
abstract class StudentRoomDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDAO

    companion object{
        //Annotation qui previent multiples instances de la BD ouvrant en même temps
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null

        fun getDatabase(context: Context): StudentRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StudentRoomDatabase::class.java,
                "Student_database"
            ).build()
            return INSTANCE as StudentRoomDatabase
        }
    }
}

