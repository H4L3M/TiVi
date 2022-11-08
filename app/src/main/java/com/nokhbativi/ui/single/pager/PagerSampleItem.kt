package com.nokhbativi.ui.single.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nokhbativi.ui.single.Image

/**
 * Pager item which displays an image
 */
@Composable
internal fun PagerItem(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        // Our page content, displaying a random image
        Image(
            data = rememberRandomSampleImageUrl(width = 600),
            modifier = Modifier.clip(RoundedCornerShape(24.dp)).matchParentSize()
        )

        // Displays the page index
//        Text(
//            text = page.toString(),
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//                .background(MaterialTheme.colors.surface, RoundedCornerShape(4.dp))
//                .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
//                .padding(8.dp)
//                .wrapContentSize(Alignment.Center)
//        )
    }
}
