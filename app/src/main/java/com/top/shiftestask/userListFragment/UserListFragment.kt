package com.top.shiftestask.userListFragment

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.top.shiftestask.R
import com.top.shiftestask.databinding.FragmentUserListBinding
import com.top.shiftestask.userListFragment.models.UpdateErrorUiState
import com.top.shiftestask.userListFragment.models.UserListUiState
import com.top.shiftestask.userListFragment.userListRecyclerView.UserListRvAdapter
import com.top.shiftestask.userListFragment.userListRecyclerView.UserListRvItemDecorator
import com.top.shiftestask.userListFragment.userListRecyclerView.UserListRvListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment(), UserListRvListener {

    private var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("UserList Fragment Binding null!")

    private val viewModel: UserListViewModel by viewModels()

    private val usersAdapter = UserListRvAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { bindUiState(it) }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { error -> error?.let { showUpdateError(it) } }
            }
        }
        binding.srlUpdateUsers.setOnRefreshListener {
            viewModel.updateUserList()
        }
    }

    private fun initRecyclerView() {
        val margin = resources.getDimension(R.dimen.margin_user_item).toInt()
        with(binding.rvUserList) {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(UserListRvItemDecorator(margin))
            setOnApplyWindowInsetsListener { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.updatePadding(bottom = systemBars.bottom)
                return@setOnApplyWindowInsetsListener insets
            }
        }
    }

    private fun bindUiState(state: UserListUiState) {
        binding.srlUpdateUsers.isRefreshing = state.isUpdating
        usersAdapter.updateUserList(state.users)
    }

    private fun showUpdateError(error: UpdateErrorUiState) {
        val textRes = when (error) {
            UpdateErrorUiState.SERVER_ERROR -> R.string.error_server
            UpdateErrorUiState.NETWORK_ERROR -> R.string.error_network
            UpdateErrorUiState.UNKNOWN_ERROR -> R.string.error_unknown
        }
        Snackbar.make(binding.clMain, textRes, Snackbar.LENGTH_LONG).show()
    }

    override fun onClickUser(localId: Int) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserProfileFragment(localId)
        findNavController().navigate(action)
    }
}