package study.android.bodyprofile

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat

import android.util.Pair as UtilPair

class MainActivity : AppCompatActivity() {
    val mainImageView: ImageView by lazy { findViewById<ImageView>(R.id.main_image_view) }
    val subImageView: ImageView by lazy { findViewById<ImageView>(R.id.sub_image_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, TransitionTestActivity::class.java)
        mainImageView.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                UtilPair.create(mainImageView, mainImageView.transitionName),
                UtilPair.create(subImageView, subImageView.transitionName)
            )
            startActivity(intent, options.toBundle())
        }
        subImageView.setOnClickListener {
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this, it, it.transitionName)

            startActivity(intent, option.toBundle())
        }
    }

}
