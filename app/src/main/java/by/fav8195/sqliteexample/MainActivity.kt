package by.fav8195.sqliteexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.fav8195.sqliteexample.databinding.ActivityMainBinding
import by.fav8195.sqliteexample.db.MyDbHelper
import by.fav8195.sqliteexample.db.MyDbManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding//adding Binding

    val myDbManager = MyDbManager(this)

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//adding Binding
        setContentView(binding.root)//setContentView(R.layout.activity_main)
        myDbManager.openDb()
        binding.textViewTest.text = ""

        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            binding.textViewTest.append(item+"\n")

        }

    }

    fun onClickSave(view: View) {
        //myDbManager.openDb()
        myDbManager.insertToDb(binding.editTitle.text.toString(),binding.editContent.text.toString())

        binding.textViewTest.text = ""

        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            binding.textViewTest.append(item+"\n")

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun onClickClear(view: View) {
        myDbManager.clearDb()

        binding.textViewTest.text = ""

        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            binding.textViewTest.append(item+"\n")

        }
    }

}