package screens.logged.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentTabsBinding

class TabsFragment: Fragment() {
    lateinit var binding: FragmentTabsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tabs, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeader()
        setupTabs()
    }

    private fun initHeader() {
        binding.fragmentTabsHeader.setNavController(findNavController())
    }

    private fun setupTabs() {
        val adapter = TabsPageAdapter(requireContext() as FragmentActivity)

        val viewPager = binding.fragmentTabsViewPager.apply {
            this.adapter = adapter
            isUserInputEnabled = false
            setPageTransformer(null)
        }

        val tabLayout = binding.fragmentTabsHeader.getTabLayout()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
            viewPager.setCurrentItem(tab.position, false)
        }.attach()
    }

}