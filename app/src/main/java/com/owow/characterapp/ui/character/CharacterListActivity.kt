package com.owow.characterapp.ui.character

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.owow.characterapp.BR
import com.owow.characterapp.R
import com.owow.characterapp.data.model.Result
import com.owow.characterapp.databinding.ActivityCharacterBinding
import com.owow.characterapp.databinding.CharacterItemBinding
import com.owow.characterapp.ui.adapter.BaseRecyclerViewAdapter
import com.owow.characterapp.ui.base.BaseActivity
import com.owow.characterapp.ui.base.BaseNavigator
import com.owow.characterapp.utills.CommonUtils.DARK_MODE_ENABLED
import com.owow.characterapp.utills.SharedPreference
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListActivity : BaseActivity<ActivityCharacterBinding, CharacterViewModel>(),
    KoinComponent, BaseNavigator {
    private val characterViewModel: CharacterViewModel by viewModel()
    private lateinit var characterAdapter: BaseRecyclerViewAdapter<Result, CharacterItemBinding>
    private val sharedPreference: SharedPreference by inject()

    override fun getBindingVariable(): Int {
        return BR.characterVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_character
    }

    override fun getViewModel(): CharacterViewModel {
        return characterViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setMode(sharedPreference.getBooleanValue(DARK_MODE_ENABLED))
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        characterViewModel.setNavigator(this)
        setUpAdapter()
        characterViewModel.characterListRequest()
        observeResponse()
    }

    /** Setting up list adapter*/
    private fun setUpAdapter() {
        characterAdapter = BaseRecyclerViewAdapter(
            R.layout.character_item,
            BR.characterItem, ArrayList(), null
        )
    }

    /** Observing the livedata from viewModel */
    private fun observeResponse() {
        characterViewModel.getCharacterListResponse().observe(this) {
            it?.let {
                when (it.responseType) {
                    Status.LOADING -> {
                        characterViewModel.progressLoading.set(true)
                    }
                    Status.SUCCESSFUL -> {
                        characterViewModel.progressLoading.set(false)
                        it.data?.results?.let { data ->
                            getViewDataBinding().rcvCharacter.adapter = characterAdapter
                            characterAdapter.addDataSet(data = data)
                        }
                    }
                    Status.ERROR -> {
                        characterViewModel.progressLoading.set(false)
                        putToast(it.error)
                    }
                }
            }
        }
    }

    /** @param darkEnabled-> Enabling the darkMode based on boolean value */
    private fun setMode(darkEnabled: Boolean) {
        if (darkEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onClickView(v: View?) {
        when (v?.id) {
            R.id.dark_mode_image -> {
                sharedPreference.setBooleanValue(
                    DARK_MODE_ENABLED,
                    !sharedPreference.getBooleanValue(DARK_MODE_ENABLED)
                )
                setMode(sharedPreference.getBooleanValue(DARK_MODE_ENABLED))
                recreate()
            }
        }
    }

}