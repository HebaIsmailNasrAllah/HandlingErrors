package com.example.handlingbackenderrors.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.handlingbackenderrors.data.DataSource
import com.example.handlingbackenderrors.data.RepoImpl
import com.example.handlingbackenderrors.databinding.ActivityMainBinding
import com.example.handlingbackenderrors.domain.UseCase
import com.example.handlingbackenderrors.presentation.model.*
import com.example.handlingbackenderrors.presentation.presentation.MyViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel : MyViewModel by viewModels {
        MyViewModel.MyFactory(UseCase(RepoImpl(DataSource())))
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            viewModel.getData()
        }

        lifecycleScope.launch {
            viewModel.state.collect{
            }
        }

        lifecycleScope.launch {
            viewModel.errorState.collect{
                when(it){
                    is ShowUpdateInfoSnackbar -> showSnackBar(it.str)
                    is ShowLoginScreenPopup -> MyDialog(getString(it.str)).show(supportFragmentManager,"TAG" )
                    is ShowSignupScreen -> startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
                    is ShowCustomerNotVerifiedPopup ->MyDialog(getString(it.str)).show(supportFragmentManager,"TAG" )
                    is ShowUnKnownErrorPopup -> MyDialog(getString(it.str)).show(supportFragmentManager,"TAG" )
                }
            }
        }
    }

    private fun showSnackBar(id: Int){
        Snackbar.make(binding.root, getString(id), Snackbar.LENGTH_SHORT).show()
    }
}