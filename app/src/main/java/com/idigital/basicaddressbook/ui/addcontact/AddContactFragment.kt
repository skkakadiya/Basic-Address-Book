package com.idigital.basicaddressbook.ui.addcontact


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.idigital.basicaddressbook.MainActivity
import com.idigital.basicaddressbook.R
import com.idigital.basicaddressbook.data.Contact
import com.idigital.basicaddressbook.databinding.FragmentAddContactBinding
import com.idigital.basicaddressbook.di.ViewModelProviderFactory
import com.idigital.basicaddressbook.util.OPTIONS
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddContactFragment : DaggerFragment() {

    private val TAG = "AddContactFragment"

    val REQUEST_IMAGE = 100
    var profilePicturePath: String? = null

    private lateinit var  binding: FragmentAddContactBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_contact, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel = ViewModelProvider(this, factory)[AddContactViewModel::class.java]

        val args = AddContactFragmentArgs.fromBundle(arguments!!)

        if (args.id != -1){
            val contact = viewModel.getContactById(args.id)
            contact.observe(viewLifecycleOwner, Observer {
                binding.contact = it
            })
        }

        binding.nameEditText.doAfterTextChanged {
            viewModel.ContactName = it.toString()
        }

        binding.titleEditText.doAfterTextChanged {
            viewModel.ContactTitle = it.toString()
        }

        binding.CompanyNameEditText.doAfterTextChanged {
            viewModel.CompanyName = it.toString()
        }

        binding.AddressEditText.doAfterTextChanged {
            viewModel.Address = it.toString()
        }

        binding.CityEditText.doAfterTextChanged {
            viewModel.City = it.toString()
        }

        binding.PostalCodeEditText.doAfterTextChanged {
            viewModel.PostalCode = it.toString()
        }

        binding.CountryEditText.doAfterTextChanged {
            viewModel.Country = it.toString()
        }

        binding.FaxEditText.doAfterTextChanged {
            viewModel.Fax = it.toString()
        }

        binding.phoneNumberEditText.doAfterTextChanged {
            viewModel.Phone = it.toString()
        }

        binding.emailEditText.doAfterTextChanged {
            viewModel.Email = it.toString()
        }

        binding.saveButton.setOnClickListener {
            if (args.id == -1) {
                Log.d(TAG, "Contact name: ${viewModel.ContactName}")
                viewModel.saveContact(
                    Contact(
                        "0",
                    viewModel.CompanyName,
                    viewModel.ContactName,
                    viewModel.ContactTitle,
                    viewModel.Address,
                    viewModel.City,
                    viewModel.Email,
                    viewModel.PostalCode,
                    viewModel.Country,
                    viewModel.Phone,
                    viewModel.Fax
                        )
                )
                it.findNavController().navigate(AddContactFragmentDirections
                    .actionAddContactFragmentToContactsFragment())
            } else {
                Log.d(TAG, "Contact name: ${viewModel.ContactName}")
                viewModel.updateContact(Contact(
                    "0",
                    viewModel.CompanyName,
                    viewModel.ContactName,
                    viewModel.ContactTitle,
                    viewModel.Address,
                    viewModel.City,
                    viewModel.Email,
                    viewModel.PostalCode,
                    viewModel.Country,
                    viewModel.Phone,
                    viewModel.Fax,
                    args.id))

                it.findNavController().navigate(AddContactFragmentDirections
                    .actionAddContactFragmentToContactsFragment())
            }
        }

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_addContactFragment_to_contactsFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this)
        {
            // handling back button
            findNavController().navigate(AddContactFragmentDirections.actionAddContactFragmentToContactsFragment(), OPTIONS)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.add_new_contact)
    }
}
