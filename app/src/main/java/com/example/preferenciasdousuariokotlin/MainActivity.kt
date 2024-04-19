package com.example.preferenciasdousuariokotlin

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.preferenciasdousuariokotlin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cor = ""
    private val COR_SALVA = "corSalva"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LayoutPrincipal)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btCorBlue.setOnClickListener{
            cor = "#2196F3"

            salvarCor(cor)
        }

        binding.btCorBlack.setOnClickListener {
            cor = "#FF000000"

            salvarCor(cor)
        }


        binding.btCorGreen.setOnClickListener{
            cor = "#4CAF50"

            salvarCor(cor)
        }

        binding.btCorRed.setOnClickListener{
            cor = "#F44336"

            salvarCor(cor)
        }

        binding.btCorPurple.setOnClickListener{
            cor = "#673AB7"

            salvarCor(cor)
        }


        binding.btCorWhite.setOnClickListener{
            cor = "#FFFFFFFF"

            salvarCor(cor)
        }
    }

    private fun salvarCor(cor: String){

        binding.LayoutPrincipal.setBackgroundColor(Color.parseColor(cor))

        binding.btTrocarCor.setOnClickListener{ view ->

            val preferencias = getSharedPreferences(COR_SALVA, MODE_PRIVATE)

            val editorCor = preferencias.edit()
            editorCor.putString("cor", cor)
            editorCor.apply()

            snackbar(view)
        }
    }


    private fun snackbar(view: View){

        val snackbar = Snackbar.make(view, "Cor de fundo alterada!", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK"){

        }
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setBackgroundTint(Color.WHITE)
        snackbar.setTextColor(Color.BLACK)
        snackbar.show()
    }

    override fun onResume() {
        super.onResume()

        val preferencias = getSharedPreferences(COR_SALVA, MODE_PRIVATE)
        val cor = preferencias.getString("cor", "")

        if (cor!!.isNotEmpty()){
            binding.LayoutPrincipal.setBackgroundColor(Color.parseColor(cor))

        }
    }
}