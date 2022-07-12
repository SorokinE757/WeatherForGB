import com.example.weatherforgb.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}