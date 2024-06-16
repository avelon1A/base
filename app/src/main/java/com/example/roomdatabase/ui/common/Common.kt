package com.example.roomdatabase.ui.common

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.roomdatabase.util.Demins.IndicationSize

@Composable
fun NextButton(
    text:String,
    onClick:() -> Unit
){

    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
        ) {

        Text(
           text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}



@Composable
fun PageIndicator(
    modifier: Modifier,
    pageSelected:Int,
    pageSize:Int,
    selectedColor:Color = MaterialTheme.colorScheme.primary,
    unSelectedColor:Color = Color.Black

){

    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround''){
        repeat(pageSize){page ->
            Box(modifier = Modifier.size(IndicationSize).clip(CircleShape)
                .background(color = if (page == pageSelected)selectedColor else unSelectedColor ))

        }
    }

}