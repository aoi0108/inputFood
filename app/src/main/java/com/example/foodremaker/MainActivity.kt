package com.example.foodremaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodremaker.R
import com.example.foodremaker.ui.theme.FoodremakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodremakerTheme {
                // A surface container using the 'background' color from the theme
                //Surface(
                // modifier = Modifier.fillMaxSize(),
                // color = MaterialTheme.colors.background
                //)
                //{
                FoodremakerApp()

                //}
            }
        }
    }
}



@Preview
@Composable
fun FoodremakerApp() {
    var currentStep by remember { mutableStateOf(1) }
    when(currentStep){
        1 ->{
            TitleWithImage(message = stringResource(id = R.string.title_text),
            onImageClick = {
                currentStep = 2
            }
            )

        }
        2->{
            FoodremakerWithButton(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                //.fillMaxHeight()
                .wrapContentSize(Alignment.Center)
                .background(Color.White),
            onAddClick = {
                currentStep = 3
            }
        )

        }
        3->{
            SelectRecipe(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    //.fillMaxHeight()
                    .wrapContentSize(Alignment.Center)
                    .background(Color.White),
                        onAddClick = {
                    currentStep = 4
                }
            )
        }
        4->{
            SuggestionWithImage("Today's suggestion","Omelette Rice","Onion Soup")
        }
    }

}


@Composable
fun TitleWithText(message: String) {
    Column{

        Text(
            text=message,
            fontSize=45.sp,
            modifier= Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 20.dp, top = 100.dp)
        )


    }

}


@Composable
fun TitleWithImage(message:String,
                   onImageClick:()->Unit,) {
    val image = painterResource(R.drawable.cooking)
    Box{
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .clickable(
                    onClick = onImageClick
                )
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        TitleWithText(message=message)

    }
}

@Composable
fun FoodremakerWithButton(
    onAddClick:()->Unit,
    modifier:Modifier = Modifier){
    var result by remember { mutableStateOf(1) }
    var amountInput by remember { mutableStateOf("") }

    Column( modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(bottom = 200.dp)
        .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,




    ){ Text(stringResource(R.string.description_input))
        EditFoodField(
            value = amountInput,
            onValueChanged = { amountInput = it }
        )
        Button(onClick = onAddClick ){
            Text(stringResource(R.string.add))
        }

    }
}


@Composable
fun EditFoodField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(stringResource(R.string.food_in_refrigerator)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}


@Composable
fun SelectRecipe(
    onAddClick:()->Unit,
    modifier:Modifier = Modifier
){
    Column( modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(stringResource(id = R.string.what_cook))
        Button(onClick = onAddClick ){
            Text(stringResource(R.string.add))
        }
    }

}

fun SearchRecipe(
    value: String
){
    var menu : String
    if(value == "egg"){
        menu = "omulet"
    }else if(value == "onion"){
        menu = "onion soup"
    }

}

@Composable
fun SuggestionWithText(message: String,menu1:String,menu2:String) {
    Column {
        Text(
            text = message,
            fontSize = 45.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 0.dp, top = 100.dp)


        )

        Text(
            text = menu1,
            fontSize=30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 30.dp, top = 200.dp)
        )
        Text(
            text = menu2,
            fontSize=30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 30.dp, top = 200.dp)
        )
    }
}



@Composable
fun SuggestionWithImage(message:String,menu1:String,menu2:String){
    val image1 = painterResource(R.drawable.omlet)
    val image2 = painterResource(R.drawable.onion)

    Box{
        Image(
            painter = image1,
            contentDescription = null,
            modifier = Modifier .padding(start=150.dp,top=250.dp),
            contentScale = ContentScale.Crop



        )
        Image(
            painter = image2,
            contentDescription = null,
            modifier = Modifier .padding(start=150.dp,top=500.dp),
            contentScale = ContentScale.Crop


        )


        SuggestionWithText(message=message,menu1=menu1,menu2=menu2)

    }

}



