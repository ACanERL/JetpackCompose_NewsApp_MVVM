import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example6.ui.theme.AppColorScheme
import com.example.compose_example6.ui.theme.AppShape
import com.example.compose_example6.ui.theme.AppSize
import com.example.compose_example6.ui.theme.AppTypography
import com.example.jetpackcompose_newsmvvm.ui.theme.ButtonDark
import com.example.jetpackcompose_newsmvvm.ui.theme.ButtonLight
import com.example.compose_example6.ui.theme.LocalAppColorScheme
import com.example.compose_example6.ui.theme.LocalAppShape
import com.example.compose_example6.ui.theme.LocalAppSize
import com.example.compose_example6.ui.theme.LocalAppTypography
import com.example.jetpackcompose_newsmvvm.ui.theme.Pink40
import com.example.jetpackcompose_newsmvvm.ui.theme.Pink80
import com.example.jetpackcompose_newsmvvm.ui.theme.Purple40
import com.example.jetpackcompose_newsmvvm.ui.theme.Purple80
import com.example.jetpackcompose_newsmvvm.ui.theme.PurpleGrey40
import com.example.compose_example6.ui.theme.Roboto
import com.example.jetpackcompose_newsmvvm.ui.theme.cardItemColorDark
import com.example.jetpackcompose_newsmvvm.ui.theme.cardItemColorLight
import com.example.jetpackcompose_newsmvvm.ui.theme.textColorDark
import com.example.jetpackcompose_newsmvvm.ui.theme.textColorLight

private val darkColorScheme= AppColorScheme(
    backgroundColor = Color.Black,
    onBackgroundColor = Purple40,
    primaryColor = PurpleGrey40,
    onPrimaryColor = Purple80,
    secondaryColor = Pink40,
    onSecondaryColor = Pink80,
    buttonColor= ButtonDark, //blue
    textColor = textColorLight,//white
    cardItemColor = cardItemColorDark,

    )

private val lightColorScheme=AppColorScheme(
    backgroundColor = Color.White,
    onBackgroundColor = Purple40,
    primaryColor = PurpleGrey40,
    onPrimaryColor = Purple80,
    secondaryColor = Pink40,
    onSecondaryColor = Pink80,
    buttonColor = ButtonLight, //black
    textColor = textColorDark,
    cardItemColor = cardItemColorLight,
)

private val typography= AppTypography(
    titleLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
    ),

    body = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),

    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
    ),
)

private val shape= AppShape(
    container = RoundedCornerShape(12.dp),
    button = RoundedCornerShape(50.dp)
)

private val size= AppSize(
    large = 24.dp,
    medium = 18.dp,
    normal = 16.dp,
    small = 12.dp
)

@Composable
fun AppTheme(
    isDarkTheme:Boolean= isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorScheme= if(isDarkTheme) darkColorScheme else lightColorScheme
   // val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppSize provides  size,
        LocalAppShape provides shape,
       // LocalIndication provides rippleIndication,
        content=content
    )
}

object AppTheme{
    val colorScheme:AppColorScheme
        @Composable get()= LocalAppColorScheme.current
    val typography:AppTypography
        @Composable get() = LocalAppTypography.current
    val size:AppSize
        @Composable get() = LocalAppSize.current
    val shape:AppShape
        @Composable get() = LocalAppShape.current
}