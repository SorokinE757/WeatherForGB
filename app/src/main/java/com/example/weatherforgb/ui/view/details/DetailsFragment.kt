import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherforgb.databinding.FragmentDetailsBinding
import com.example.weatherforgb.domain.Weather

class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val weather=  arguments?.get(BUNDLE_WEATHER_EXTRA)
        arguments?.let { args-> args.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA)?.let{ weather ->
            renderData(weather) } }

    }

    private fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }

    companion object {
        const val BUNDLE_WEATHER_EXTRA = "sgrrdfge"
        fun newInstance(weather: Weather): DetailsFragment {
//            val bundle = Bundle()
//            bundle.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            val fr = DetailsFragment()
            fr.arguments = Bundle().apply { putParcelable(BUNDLE_WEATHER_EXTRA, weather) }
            return fr
        }
    }


}
