package study.android.bodyprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat

class MainActivity : AppCompatActivity() {
    val imageView: ImageView by lazy { findViewById<ImageView>(R.id.main_image_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnClickListener {
            val intent = Intent(this, TransitionTestActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this, it, it.transitionName)

            startActivity(intent, option.toBundle())
        }
    }
}
