package ma.nokhbativi.ui.single

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ma.nokhbativi.model.database.DatabaseCategory
import ma.nokhbativi.util.imgur

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinglePackage(pack: DatabaseCategory, onClick: (name: String, code: String) -> Unit) {

    Card(
        modifier = Modifier.size(64.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.dp
        ),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { onClick(pack.name.en, pack.code.alpha) }
    ) {
        Image(
            modifier = Modifier
                .size(64.dp),
            data = if (pack.logo.length == 7) pack.logo.imgur() else pack.logo
        )
    }
}