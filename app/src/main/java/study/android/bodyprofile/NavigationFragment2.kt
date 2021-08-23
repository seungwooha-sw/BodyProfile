package study.android.bodyprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class NavigationFragment2 : Fragment() {

    val args: NavigationFragment2Args by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.nav2_text_view).apply {
            text = args.textArg
            setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_navigationFragment2_to_navigationFragment1)
            }
        }
    }

}
