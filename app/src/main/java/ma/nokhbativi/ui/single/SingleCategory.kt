package ma.nokhbativi.ui.single

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import ma.nokhbativi.model.database.DatabaseCategory
import ma.nokhbativi.util.imgur

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleCategory(category: DatabaseCategory, onClick: (name: String, code: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 6.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick(category.name.en, category.code.alpha) }
    ) {
        ConstraintLayout {
            val (logo, name) = createRefs()
            Image(
                modifier = Modifier
                    .size(56.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(logo) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                data = if (category.logo.length == 7) category.logo.imgur() else category.logo
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .placeholder(
                        color = Color.Gray,
                        visible = false,
                        highlight = PlaceholderHighlight.shimmer(),
                    )
                    .constrainAs(name) {
                        start.linkTo(logo.end)
                        top.linkTo(logo.top)
                        bottom.linkTo(logo.bottom)
                    },
                textAlign = TextAlign.Center,
                text = category.name.en
            )
        }
    }
}