package com.top.shiftestask.userProfileFragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.top.shiftestask.R
import com.top.shiftestask.databinding.FragmentUserProfileBinding
import com.top.shiftestask.userProfileFragment.models.UserProfileUiState
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.UserProfileInfoRvAdapter
import com.top.shiftestask.userProfileFragment.userProfileInfoRv.UserProfileInfoRvItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("UserProfile Fragment Binding null!")

    private val viewModel: UserProfileViewModel by viewModels()

    private val args: UserProfileFragmentArgs by navArgs()

    private val infoAdapter = UserProfileInfoRvAdapter()

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
        binding.clAppBar.setOnApplyWindowInsetsListener { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top)
            return@setOnApplyWindowInsetsListener insets
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    state?.let { bindUiState(it) }
                }
            }
        }
    }

    private fun initRecyclerView() {
        val marginHorizontal =
            resources.getDimension(R.dimen.margin_user_info_item_horizontal).toInt()
        val marginVertical = resources.getDimension(R.dimen.margin_user_info_item_vertical).toInt()
        binding.rvUserDetailInfo.adapter = infoAdapter
        binding.rvUserDetailInfo.layoutManager = LinearLayoutManager(context)
        binding.rvUserDetailInfo.addItemDecoration(
            UserProfileInfoRvItemDecoration(
                marginHorizontal,
                marginVertical
            )
        )
    }

    private fun bindUiState(state: UserProfileUiState) {
        Picasso.get().load(state.picture.large).into(binding.imvUserImage)
        infoAdapter.updateInfo(state.infoItems)
        binding.tvUserName.text = state.name.toString()
        binding.tvUserLogin.text = state.username
    }
}