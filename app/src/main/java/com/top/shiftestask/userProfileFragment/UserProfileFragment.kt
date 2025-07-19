package com.top.shiftestask.userProfileFragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.top.shiftestask.R
import com.top.shiftestask.databinding.FragmentUserProfileBinding
import com.top.shiftestask.userProfileFragment.models.ImplicitIntentType
import com.top.shiftestask.userProfileFragment.models.LoadUserDataErrorUiState
import com.top.shiftestask.userProfileFragment.models.UserProfileUiState
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.UserProfileInfoRvAdapter
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.UserProfileInfoRvItemDecoration
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.UserProfileInfoRvListener
import com.top.shiftestask.utils.applyDefaultExitEnterTransition
import com.top.shiftestask.utils.applyDefaultSharedElementTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.core.net.toUri

@AndroidEntryPoint
class UserProfileFragment : Fragment(), UserProfileInfoRvListener {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("UserProfile Fragment Binding null!")

    private val viewModel: UserProfileViewModel by viewModels()

    private val args: UserProfileFragmentArgs by navArgs()

    private val infoAdapter = UserProfileInfoRvAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchUserData(args.localId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater)
        initRecyclerView()
        postponeEnterTransition()
        applyDefaultSharedElementTransition()
        applyDefaultExitEnterTransition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.flIconBack.setOnClickListener {
            findNavController().navigateUp()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state?.let { bindUiState(it) }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect {
                    val errorText = when (it) {
                        LoadUserDataErrorUiState.NOT_FOUND -> R.string.error_user_not_found
                        LoadUserDataErrorUiState.UNKNOWN -> R.string.error_user_unknown
                    }
                    Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun initRecyclerView() {
        val marginHorizontal =
            resources.getDimension(R.dimen.margin_user_info_item_horizontal).toInt()
        val marginVertical =
            resources.getDimension(R.dimen.margin_user_info_item_vertical).toInt()
        with(binding.rvUserDetailInfo) {
            adapter = infoAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                UserProfileInfoRvItemDecoration(
                    marginHorizontal,
                    marginVertical
                )
            )
            setOnApplyWindowInsetsListener { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.updatePadding(bottom = systemBars.bottom, top = systemBars.top)
                return@setOnApplyWindowInsetsListener insets
            }
        }
    }

    private fun bindUiState(state: UserProfileUiState) {
        infoAdapter.updateInfo(state.infoItems)
        startPostponedEnterTransition()
    }

    override fun onClickInfoItem(data: String, implicitIntentType: ImplicitIntentType) {
        when (implicitIntentType) {
            ImplicitIntentType.EMAIL -> openEmail(data)
            ImplicitIntentType.MAPS -> openMaps(data)
            ImplicitIntentType.TEL -> openTel(data)
            ImplicitIntentType.NONE -> return
        }
    }

    private fun openEmail(email: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto:$email".toUri()
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("UserProfileFragment", e.toString())
            Toast.makeText(context, R.string.error_open_email, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openMaps(address: String) {
        try {
            val geo = "geo:0,0?q=${Uri.encode(address)}".toUri()
            val intent = Intent(Intent.ACTION_VIEW, geo)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("UserProfileFragment", e.toString())
            Toast.makeText(context, R.string.error_open_maps, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openTel(phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:$phoneNumber".toUri()
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("UserProfileFragment", e.toString())
            Toast.makeText(context, R.string.error_open_dialer, Toast.LENGTH_SHORT).show()
        }
    }
}