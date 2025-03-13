package com.example.inventorymanager.ui.materials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorymanager.databinding.FragmentMaterialListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MaterialListFragment : Fragment() {
    private var _binding: FragmentMaterialListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MaterialViewModel by viewModels()
    private lateinit var adapter: MaterialAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeMaterials()
        setupFab()
    }

    private fun setupRecyclerView() {
        adapter = MaterialAdapter(
            onItemClick = { material ->
                // TODO: Navigate to edit material
            },
            onDeleteClick = { material ->
                viewModel.deleteMaterial(material)
            }
        )
        binding.materialsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MaterialListFragment.adapter
        }
    }

    private fun observeMaterials() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allMaterials.collectLatest { materials ->
                adapter.submitList(materials)
            }
        }
    }

    private fun setupFab() {
        binding.fabAddMaterial.setOnClickListener {
            // TODO: Show add material dialog
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
