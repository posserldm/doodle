package posserldm.doodle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import posserldm.doodle.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
    }

}
