package screens.logged.tabs

import activities.logged.LoggedActivity
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.joao.simsschool.R
import com.joao.simsschool.databinding.FragmentTabsBinding
import utils.CubeTransformer

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

        setupTabs()
    }

    private fun setupTabs() {
        val adapter = TabsPageAdapter(requireContext() as FragmentActivity)

        val viewPager = binding.fragmentTabsViewPager.apply {
            this.adapter = adapter
            isUserInputEnabled = false
        }

        viewPager.setPageTransformer(CubeTransformer())


        val tabLayout = binding.fragmentTabsHeader.getTabLayout()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
            viewPager.setCurrentItem(tab.position, false)
        }.attach()
    }

}