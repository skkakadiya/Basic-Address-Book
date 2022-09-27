package com.idigital.basicaddressbook.ui.contactslist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.idigital.basicaddressbook.MainActivity
import com.idigital.basicaddressbook.R
import com.idigital.basicaddressbook.databinding.FragmentContactsListBinding
import com.idigital.basicaddressbook.di.ViewModelProviderFactory
import com.idigital.basicaddressbook.util.OPTIONS
import dagger.android.support.DaggerFragment
import java.io.File
import java.net.URLConnection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [ContactsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactsListFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ContactsListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentContactsListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        )[ContactsListViewModel::class.java]

        val adapter = ContactsAdapter(context!!, ContactsClickListener {
            val id = it.id
            this.findNavController().navigate(ContactsListFragmentDirections
                .actionContactsListFragmentToContactDetailsFragment(id))
        })

        binding.contactList.adapter = adapter

        subscribeUI(adapter)

        binding.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_contactsListFragment_to_addContactFragment, null, OPTIONS)
        }

        return binding.root

    }

    private fun subscribeUI(adapter: ContactsAdapter) {
        viewModel.getAllContacts.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
            } else {
                viewModel.addContactsFromFile()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.exportJSON -> {
                val file = context?.let { viewModel.exportJSON(it) }
                if (file != null) {
                    shareFile(file)
                }
                return true
            }
            R.id.exportXML -> {
                val file = context?.let { viewModel.exportXML(it) }
                if (file != null) {
                    shareFile(file)
                }
                return true
            }
        }
        return false
    }

    private fun shareFile(file: File) {
        val uri = FileProvider.getUriForFile(
            context!!,
            "com.idigital.basicaddressbook.provider",
            file)
        val intentShareFile = Intent(Intent.ACTION_SEND)
        intentShareFile.type = URLConnection.guessContentTypeFromName(file.name)
        intentShareFile.putExtra(
            Intent.EXTRA_STREAM,
            uri
        )
        startActivity(Intent.createChooser(intentShareFile, "Share File"))
    }

}