package com.nokhbativi.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nokhbativi.model.database.DatabaseCategory
import com.nokhbativi.ui.single.SingleCountryHorizontal

@ExperimentalMaterialApi
@Composable
fun TiViScreen(countries: List<DatabaseCategory>, onClick: (name: String, code: String) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(1)
    ) {
        items(countries) { country ->
            SingleCountryHorizontal(
                country = country
            ) { name, code ->
                onClick(name, code)
            }
        }
    }
}


//@ExperimentalMaterialApi
//@Composable
//fun TiViScreen(categories: List<DatabaseCountry>, channels: List<DatabaseChannel>) {
//
//    val context =LocalContext.current
//    val scope = rememberCoroutineScope()
//
//    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
//    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
//
//    val alpha2 = rememberSaveable {
//        mutableStateOf("")
//    }
//
//    BottomSheetScaffold(
//        modifier = Modifier.fillMaxSize(),
//        scaffoldState = scaffoldState,
//        sheetElevation = 16.dp,
//        sheetContent = {
//            Column(
//                modifier = Modifier
//                    .padding(top = 24.dp)
//                    .fillMaxSize(),
//            ) {
//                ConstraintLayout(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp)
//                        .padding(8.dp),
//                ) {
//
//                    val (close, label) = createRefs()
//
//                    Icon(
//                        modifier = Modifier
//                            .size(24.dp)
//                            .clickable {
//                                scope.launch {
//                                    sheetState.collapse()
//                                }
//                            }
//                            .constrainAs(close) {
//                                start.linkTo(parent.start)
//                                top.linkTo(parent.top)
//                                bottom.linkTo(parent.bottom)
//                            },
//                        imageVector = Icons.Rounded.Clear,
//                        contentDescription = null
//                    )
//                    Text(
//                        modifier = Modifier.constrainAs(label) {
//                            start.linkTo(parent.start)
//                            top.linkTo(parent.top)
//                            end.linkTo(parent.end)
//                            bottom.linkTo(parent.bottom)
//                        },
//                        text = alpha2.value,
//                        textAlign = TextAlign.Center,
//                        fontSize = 24.sp
//                    )
//                }
//                Divider()
//                LazyVerticalGrid(
//                    modifier = Modifier.fillMaxSize(),
//                    columns = GridCells.Fixed(4)
//                ) {
//                    items(channels) { channel ->
//                        SingleChannel(
//                            channel = channel,
//                            onClick = { stream ->
//                                val intent = Intent(Intent.ACTION_VIEW)
//                                intent.setDataAndType(Uri.parse(stream), "video/*")
//                                context.startActivity(intent)
//                            },
//                        )
//                    }
//                }
//            }
//        }
//    ) { paddingValues ->
//        LazyVerticalGrid(
//            modifier = Modifier.padding(paddingValues = paddingValues),
//            columns = GridCells.Fixed(4)
//        ) {
//            items(categories) { category ->
//                SingleCountry(
//                    country = category,
//                    onClick = {
//                        alpha2.value = it
//                        scope.launch {
//                            if (sheetState.isCollapsed) {
//                                sheetState.expand()
//                            } else {
//                                sheetState.collapse()
//                            }
//                        }
//                    },
//                )
//            }
//        }
//    }
//}
