package study.android.bodyprofile

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    /*
    Creating viewModel by 'by' shows a render error on the preview.
    When you want to see the preview of this activity, use following code:

    Code for preview
        private val viewModel: MainViewModel = MainViewModel()
     */

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainCompose() }

        viewModel.setMemo(
            resources.getStringArray(R.array.memo).toList()
        )

        viewModel.test()

        viewModel.newsResponse.observe(this, Observer {
            findViewById<TextView>(R.id.text_view).text = it.toString()
        })
    }

    @Preview
    @Composable
    fun MainCompose() {
        /*
        Column can be considered as Vertical LinearLayout in XML.
         */
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            val title by viewModel.title.observeAsState("")
            TextField(
                value = title, onValueChange = {
                    viewModel.setTitle(it)
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            )
            Text(text = title, modifier = Modifier.align(Alignment.CenterHorizontally))

            val description by viewModel.description.observeAsState("")
            TextField(
                value = description, onValueChange = {
                    viewModel.setDescription(it)
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            )
            Text(text = description, modifier = Modifier.align(Alignment.CenterHorizontally))

            /*
            LazyColumn can be considered as RecyclerView in XML.
             */
            val memo by viewModel.memoList.observeAsState(listOf())
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = memo,
                    itemContent = {
                        MemoCompose(memo = it)
                    }
                )
            }
        }
    }

    @Composable
    fun MemoCompose(memo: String) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Text(
                text = memo, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
