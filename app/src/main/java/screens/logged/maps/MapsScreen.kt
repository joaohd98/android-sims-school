package screens.logged.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ScreenMapsBinding


class MapsFragment : Fragment() {
    private lateinit var binding: ScreenMapsBinding
    private val latLng = LatLng(-23.6037231, -46.7351884)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.screen_maps, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMaps(savedInstanceState)
    }

    private fun initMaps(savedInstanceState: Bundle?) {
        binding.screenMapsView.apply {
            onCreate(savedInstanceState)

            getMapAsync {
                it.apply {
                    moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f))
                    addMarker(MarkerOptions().position(latLng).title("Sims School"))
                }
            }

            binding.screenMapsView.onResume()
        }
    }

}