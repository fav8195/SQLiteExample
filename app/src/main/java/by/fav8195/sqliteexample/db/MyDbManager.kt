package by.fav8195.sqliteexample.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager (val context: Context) {
    val myDbHelper = MyDbHelper(context)//Создаем хелпер
    var db: SQLiteDatabase? = null//Объявляем переменную БД

    fun openDb() {
        //Открытие БД на чтение
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String) {
        //Структура с данными для записи
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE,title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDbNameClass.TABLE_NAME,null,values)//Вставка строки в таблицу
    }

    @SuppressLint("Range")
    fun readDbData (): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME,null,null,null,null,null,null)

        //Обход результата запроса
        while (cursor?.moveToNext()!!) {
            //получение данных из строки по номеру колонки
            val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }

    fun closeDb(){
        myDbHelper.close()
    }

    fun clearDb(){
        db?.execSQL(MyDbNameClass.CLEAR_TABLE)
    }
}