package com.layon.duckroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.layon.duckroomdatabase.database.DuckDatabase
import com.layon.duckroomdatabase.database.DuckRepository
import com.layon.duckroomdatabase.databinding.ActivityMainBinding
import com.layon.duckroomdatabase.model.Duck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: DuckRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            saveDuckToDatabase()
        }

        binding.deleteButton.setOnClickListener{
            lifecycleScope.launch {
                Log.d("layon.f", "repository.removeDuck()")
                repository.removeDuck()
            }
        }

        binding.readButton.setOnClickListener{
            readDuckToDatabase()
        }

        val duckDao = DuckDatabase.getDatabase(this).duckDao()
        repository = DuckRepository(duckDao)
    }

    private fun saveDuckToDatabase(){
        //get the data from UI
        val name = binding.editTextName.text.toString()
        val age = binding.editTextAge.text.toString().toInt()

        lifecycleScope.launch {
            Log.d("layon.f", "repository.saveDuck(Duck($name, $age))")
            repository.removeDuck()
            repository.saveDuck(Duck(name, age))
        }
    }

    private fun readDuckToDatabase(){
        lifecycleScope.launch {
            Log.d("layon.f", "repository.read()")
            val duck = repository.read()
            if(duck.isNotEmpty()){
                binding.dbResultTextView.text = "name: ${duck[0].name} age: ${duck[0].age}"
            } else {
                binding.dbResultTextView.text = "table is empty"
            }

        }
    }
}